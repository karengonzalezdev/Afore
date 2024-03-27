package mx.profuturo.nci.business.util;

import static mx.profuturo.nci.business.util.Constantes.DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO;
import static mx.profuturo.nci.business.util.Constantes.ID_MAS_DE_UNA_PARCIALIDAD;
import static mx.profuturo.nci.business.util.Constantes.ID_UNICA_PARCIALIDAD;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ESTATUS_CARGO_GENERADO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.profuturo.nci.business.service.IConsultasBaseService;
import mx.profuturo.nci.business.service.impl.ConsultasBaseServiceImpl;
import mx.profuturo.nci.business.service.impl.GeneracionArchivoDomiServiceImpl;
import mx.profuturo.nci.business.vo.CargoVO;
import mx.profuturo.nci.business.vo.CatPrioridadesDiversificacionesVO;
import mx.profuturo.nci.business.vo.DiversificacionVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.SolicitudVO;
import mx.profuturo.nci.persistence.CargoPersistence;
import mx.profuturo.nci.persistence.DiversificacionPersistence;


@Component
public class Hilo{
	
	
	private static final Logger LOGGER = Logger.getLogger( Hilo.class );
	
	
	@Autowired
	IConsultasBaseService consultasBaseService;
	
	@Autowired 
	CargoPersistence cargoPersistence;
	
	@Autowired 
	DiversificacionPersistence diversificacionPersistence;
	
	public void execute(String idHilo 
			, SolicitudVO solicitud
			, Long idArchivo
			, String usuario
			, List<CatPrioridadesDiversificacionesVO> catDivPrioridades) {
		
		new Hilos( idHilo 
				,  solicitud
				,  idArchivo
				,  usuario
				,  catDivPrioridades).start();		
	}

	private class Hilos extends Thread{
				
		private SolicitudVO solicitud;
		private String idHilo;
		private Long idArchivo;
		private String usuario;
		private List<CatPrioridadesDiversificacionesVO> catDivPrioridades;
		
		public Hilos(  String idHilo 
					, SolicitudVO solicitud
					, Long idArchivo
					, String usuario
					, List<CatPrioridadesDiversificacionesVO> catDivPrioridades) {
			this.solicitud			= solicitud;
			this.idHilo 			= idHilo;
			this.idArchivo			= idArchivo;
			this.usuario 			= usuario;
			this.catDivPrioridades	= catDivPrioridades;
		}
		
		@Override
		public void run() {
			
			List<BigDecimal> montosParciales 				= null;
			CargoVO cargo 									= null;
			DiversificacionVO divF 					    	= null;
			List<DiversificacionVO> ltsDiversificaciones 	= null;
			int divercifica									= 0;
			long nlinea 									= 1;
			int detCargo									= 0;
			short numParcialidad  							= 0;
			
			try {
					System.out.println("PROCESANDO HILO : " + this.idHilo);
					System.out.println("SOLICITUD IMPORTE :" + solicitud.getImporte());
					montosParciales = parcializaMontoF4(solicitud.getImporte(),DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO);
					System.out.println("MONTO PARCIAL OK");
					//System.out.println( "LOTES GENERADOS .... " + montosParciales.size() );
					cargo = convertSolicitudToCargoF4(solicitud, generaFolioIdArchivoF4(), this.idArchivo, this.usuario);
					System.out.println("CONVERT SOLICITUD F4");
					if(montosParciales.size() > 1) {
						cargo.setParcialidad(ID_MAS_DE_UNA_PARCIALIDAD);
					}else {
						cargo.setParcialidad(ID_UNICA_PARCIALIDAD);
					}
					
					System.out.println( "INSERTAMOS CARGO ......................." );
					if( cargoPersistence.insertCargo(cargo).intValue() > 0) {
							numParcialidad  = 0;
							divF = new DiversificacionVO();
							divF.setClaveSolicitud(solicitud.getClaveSolicitud());
							//System.out.println( "CONSULTAMOS DIVERSIFICACIONES DE SOLICITUD ......................." );
							ltsDiversificaciones = diversificacionPersistence.getDiversificacionesByCveSolicitud(divF);
							//System.out.println( "CONSULTAMOS DIVERSIFICACIONES DE SOLICITUD ....................... [OK] ["+ltsDiversificaciones.size()+"] " );
							//System.out.println( "INSERTAMOS DIVERSIFICACIONES DE SOLICITUD ......................");
							divercifica = 0;
							for(DiversificacionVO d: ltsDiversificaciones) {
								
								if(d.getPorcentaje() != null) {
									d.setIdArchivoDomi(idArchivo);
									d.setIdCargoStr(cargo.getIdCargo());
									short idDiversif = d.getIdArchivo().shortValue();
									d.setIdPrioridad(new Integer( getIdPrioridad(catDivPrioridades, idDiversif)));
									d.setUsuarioCreacion(usuario);
									d.setFondoAportacionVoluntaria(new GenericCatalogoVO(idDiversif));
									if( diversificacionPersistence.insertarDiversificacionSolicitudDomiciliacionPar(d) == 0 ) {
										//System.out.println( "INSERTAMOS DIVERSIFICACIONES DE SOLICITUD ......................[ERROR]");
										//System.out.println( "DIVERSIFICACIÓN  DETALLE ... "+ d.toString() );
										ControlHilos.errorHilo = Boolean.TRUE;
										ControlHilos.msgError  = "Fallo en la operacion de insertarDiversificacionSolicitudDomiciliacionPar" + d.toString();
										ControlHilos.hilosTerminados[ Integer.parseInt( this.idHilo ) ] = "1";
									}
									//divercifica = divercifica +1;
									//if( divercifica % 100 == 0 ) {
										//System.out.println("PROCESADAS .....  ["+ divercifica +"]" );
									//}
								}
							}
							//System.out.println( "INSERTAMOS DIVERSIFICACIONES DE SOLICITUD             ..................[OK]["+ divercifica +"]" );
							//System.out.println( "INSERTAMOS DETALLES DE CARGO ......................");
							divF = null;
							
							for(BigDecimal monto: montosParciales) {
								cargo.setParcialidad(++ numParcialidad);
								cargo.setImporte(monto);
								cargo.setnLinea(++ nlinea);
								cargo.setIdArchivo(null);
								cargo.setIdArchivoDomi(new Long(idArchivo));
								if(cargoPersistence.insertDetalleCargo(cargo).intValue() == 0) {
									//System.out.println( "INSERTAMOS DETALLES DE CARGO ......................[ERROR]");
									//System.out.println("DETALLE DEL CARGO ::" + cargo.toString());
									ControlHilos.errorHilo = Boolean.TRUE;
									ControlHilos.msgError  = "Fallo en la operacion de insertDetalleCargo " + cargo.toString();
									ControlHilos.hilosTerminados[ Integer.parseInt( this.idHilo ) ] = "1";
								}
								//detCargo = detCargo +1;
								//if( detCargo % 100 == 0 ) {
									//System.out.println("PROCESADAS .....  ["+ detCargo +"]" );
								//}
							}
							//System.out.println( "INSERTAMOS DETALLES DE CARGO ......................[OK]["+ detCargo +"]");
							System.out.println( "INSERTAMOS CARGO ....................... [OK]" );
							ControlHilos.hilosTerminados[ Integer.parseInt( this.idHilo ) ] = "1";
						}else {
							//System.out.println( "Fallo en la operacion de insertCargo" );
							ControlHilos.errorHilo = Boolean.TRUE;
							ControlHilos.msgError  = "Fallo en la operacion de insertCargo " + cargo.toString();
							ControlHilos.hilosTerminados[ Integer.parseInt( this.idHilo ) ] = "1";
						}
					
			}catch( Exception e ) {
				ControlHilos.errorHilo = Boolean.TRUE;
				ControlHilos.msgError  = "Error al insertar cargos ";
				ControlHilos.hilosTerminados[ Integer.parseInt( this.idHilo ) ] = "1";
				System.out.println( "ERROR EN HILO   :" + this.idHilo  );
				System.out.println( "ERROR UBICACION :" + Hilo.class.getCanonicalName() );
				System.out.println( "ERROR METODO    :" + "run" );
				System.out.println( "ERROR MENSAJE   :" + e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		private Short getIdPrioridad(List<CatPrioridadesDiversificacionesVO> catDivPrioridades, Short idAportacion) {
			Short rgs = 0;
			for (CatPrioridadesDiversificacionesVO cat : catDivPrioridades) {
				if(cat.getIdCatCatalogo().equals(idAportacion)) {
					rgs = cat.getIdPrioridad();
					break;
				}
			}
			return rgs;
		}
		
		private String generaFolioIdArchivoF4() {
			String folio	= null;
			try {
				folio = Hilo.this.consultasBaseService.generaFolioDB();
				return folio;
			} catch (Exception e) {
				System.out.println("ERROR UBICACIÓN  :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName());
				System.out.println("ERROR METODO     :" + "generaFolioIdArchivoF4");
				System.out.println("ERROR MENSAJE    :" + e.getMessage());
				e.printStackTrace();
				return null;
			}finally {
				folio = null;
			}
		}
		
		private List<BigDecimal> parcializaMontoF4(BigDecimal monto, BigDecimal maxMontoParcial){
			
			List<BigDecimal> parcialidades 	= null;
			BigDecimal[] divYRemanente 		= null;
			Integer registrosCompletos 		= null;
			BigDecimal restante 			= null;
			
			try {
				
				parcialidades = new ArrayList<BigDecimal>();
				divYRemanente = monto.divideAndRemainder(maxMontoParcial);
				
				if(divYRemanente!=null && divYRemanente.length > 1) {
					registrosCompletos 	= divYRemanente[0].intValue();
					restante 			= divYRemanente[1];
					for(int i = 1; i <= registrosCompletos; i++) {
						parcialidades.add(maxMontoParcial);
					}
					if(BigDecimal.ZERO.compareTo(restante) < 0) {
						parcialidades.add(restante);
					}
				}
				return parcialidades;
				
			}catch( Exception e ) {
				LOGGER.error( "ERROR UBICACIÓN   :" + Hilo.class.getCanonicalName() );
				LOGGER.error( "ERROR METODO      :" + "parcializaMontoF4" );
				LOGGER.error( "ERROR MENSAJE     :" + e.getMessage() );
				LOGGER.error( "ERROR DESCRIPCIÓN :" , e );
				return null;
			}finally {
				parcialidades 			= null;
				divYRemanente 			= null;
				registrosCompletos 		= null;
				restante 				= null;
			}
		}
		
		private CargoVO convertSolicitudToCargoF4(SolicitudVO s, String  idCargo, Long idArchivo, String usuario) {
			
			CargoVO c = null;
			
			try {
				//System.out.println("Convirtiendo objeto .... ");
				if(s != null) {
					c = new CargoVO();
					c.setIdCargo(idCargo);
					c.setClaveSolicitud(s.getClaveSolicitud());
					c.setNumCuentaIndividual(s.getNumCuentaIndividual());
					c.setImporte(s.getImporte());
					c.setImporteRecaudado(new BigDecimal(0));
					c.setFolioArchivo(null);
					c.setEstatusCargo(new GenericCatalogoVO(ID_ESTATUS_CARGO_GENERADO));
					c.setFechaCargoBanco(null);
					c.setMotivoRechazo(null);
					//Hay que identificar si es 1 cuando es parcialidad
					//c.setParcialidad((short)(DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO.compareTo(s.getImporte()) < 0?1:0));
					c.setIdArchivoDomi(new Long(idArchivo));
					c.setFechaGeneracionCargo(null);
					c.setReIntentos(null);
					c.setFechaProximoCargo(s.getFechaProximoCargo());
					c.setFechaCreacion(new Date());
					c.setUsuarioCreacion(usuario);
					c.setFechaActualizacion(null);
					c.setUsuarioActualizacion(null);
				}
				
				//System.out.println("Convirtiendo objeto .... [OK]");
				return c;
				
			}catch( Exception e ) {
				System.out.println("Convirtiendo objeto .... [ERROR]");
				System.out.println( "ERROR UBICACIÓN     :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName() );
				System.out.println( "ERROR METODO        :" + "convertSolicitudToCargoF4" );
				System.out.println( "ERROR MENSAJE       :" + e.getMessage() );
				System.out.println(  e );
				return null;
			}finally {
				c = null;
			}
		}
	
	}

}
