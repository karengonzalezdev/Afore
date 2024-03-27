package mx.profuturo.nci.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.web.beans.ConfigSubprocesoOrigenBean;
import mx.profuturo.nci.web.beans.DepositoArchivoBean;
import mx.profuturo.nci.web.beans.DepositoArchivoWrapperBean;
import mx.profuturo.nci.web.service.IDepositoArchivoWebService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value="session")
@ManagedBean(name="depositoArchivoView")
@ViewScoped
public class DepositoArchivoView implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final Logger LOGGER = LoggerFactory.getLogger(DepositoArchivoView.class);
	
	private static int LIMITE_ARCHIVOS=10;
	private static String REGEX_ARCHIVO="(txt|TXT|csv|CSV|dom|DOM|exp|EXP)";
	
	private static String MENSAJE_LIMITE_ARCHIVO="Solo se es permitido seleccionar "+LIMITE_ARCHIVOS+" archivos";
	private static String MENSAJE_EXTENSION_ARCHIVO_NO_VALDA="Extensión de archivo no válida";
	private static String MENSAJE_ARCHIVO_NO_VALIDO="El archivo seleccionado no corresponde a un archivo de aportaciones voluntarias";
	private static String MENSAJE_ARCHIVO_NO_VALIDO_POR_FECHA="De acuerdo a la fecha del archivo seleccionado aun no esta permitido subirlo.";
	
	private static String MENSAJE_SIN_PERMISOS_ESCRITURA="No se cuentan con permisos de escritura";
	private static String MENSAJE_EXISTE_RUTA_DEPOSITO="No existe la ruta de deposito";
	private static String MENSAJE_ERROR_DEPOSITO="No se depositó por completo el archivo";
	
	//FOP || 2415 || 07/05/2018
	Boolean ishoy = Boolean.FALSE;
	

	@Autowired
	IDepositoArchivoWebService depositoArchivoWebService;
	
	private DepositoArchivoWrapperBean depositoArchivoWrapperBean;
	
	private List<ConfigSubprocesoOrigenBean>  listConfigSubprocesoOrigen;
	
	@PostConstruct
	public boolean init()
	{
		this.inicializar();
		
		return true;
	}
			
	
	
	public void inicializar()
	{
		try
		{
				
			this.depositoArchivoWrapperBean= new DepositoArchivoWrapperBean();				
			
			this.depositoArchivoWrapperBean.setDepositosArchivos(new ArrayList<DepositoArchivoBean>());
			this.depositoArchivoWrapperBean.setShowBotonExaminar(Boolean.TRUE);		
			this.depositoArchivoWrapperBean.setShowBotonDepositar(Boolean.FALSE);		
			this.depositoArchivoWrapperBean.setShowBotonLimpiar(Boolean.FALSE);
			this.depositoArchivoWrapperBean.setReintentoDeposito(Boolean.FALSE);
			this.depositoArchivoWrapperBean.setShowColumnRemover(Boolean.TRUE);
		
			//Obtenemos Mascaras de BD
			this.listConfigSubprocesoOrigen = this.depositoArchivoWebService.obtenerConfigSubprocesoOrigen();
			
		}	
		catch(MitBusinessException ex)
		{
			
		}		
		
	}
	
	
	public void cargar(FileUploadEvent event)
	{
		
		if(event != null && event.getFile() != null)
		{
			 String  extencion = event.getFile().getFileName().substring(event.getFile().getFileName().length()-3, event.getFile().getFileName().length());
			 Pattern pattern = Pattern.compile(REGEX_ARCHIVO);
		     Matcher matcher = pattern.matcher(extencion);

			if(matcher.matches())
			{
				if(this.depositoArchivoWrapperBean.getDepositosArchivos().size() < LIMITE_ARCHIVOS)
				{
					
					DepositoArchivoBean depositoArchivoBean = new DepositoArchivoBean();
					
					depositoArchivoBean.setIdentificador(event.getFile().getFileName().toUpperCase());
					depositoArchivoBean.setArchivo(event.getFile());
					
				
					this.validar(depositoArchivoBean);					
					
				}
				else
				{
					this.setMensajeError(MENSAJE_LIMITE_ARCHIVO);
				}
				
			}
			else
			{
				this.setMensajeError(MENSAJE_EXTENSION_ARCHIVO_NO_VALDA);
				
			}
		
			
						
		}
		
		Collections.sort(this.depositoArchivoWrapperBean.getDepositosArchivos());
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			LOGGER.error("ERROR UBICACION :",e);
		}
	}
	
	
	public void depositar()
	{
		// Ocultamos el boton de "Examinar" 
		this.depositoArchivoWrapperBean.setShowBotonExaminar(Boolean.FALSE);
		
		// Obtenemos los archivos con status PENDIENTE_DEPOSITO y ERROR_DEPOSITO
		// y seteamos a la lista principal de archivos 
		this.depositoArchivoWrapperBean.setDepositosArchivos(this.obtenerArchivosDepositar());

		 // Ocultamos el boton "Depositar/Reintentar"
		 this.depositoArchivoWrapperBean.setShowBotonDepositar(Boolean.FALSE);
		
		 // Ocultamos Columna Remover Del DataTable
		 this.depositoArchivoWrapperBean.setShowColumnRemover(Boolean.FALSE);
		
		for(DepositoArchivoBean depositoArchivoBean : this.depositoArchivoWrapperBean.getDepositosArchivos())
		{			 
			
			 this.copiarArchivo(depositoArchivoBean);			
			
		}
	}
	
	
	
	public List<DepositoArchivoBean> obtenerArchivosDepositar()
	{
		List<DepositoArchivoBean> archivosDepositar=new ArrayList<DepositoArchivoBean>();
		
		for(DepositoArchivoBean depositoArchivoBean : this.getDepositoArchivoWrapperBean().getDepositosArchivos())
		{
			if(depositoArchivoBean.getStatus().equals(Constantes.STATUS_DEPOSITO_ARCHIVO_PENDIENTE_DEPOSITO) ||
					depositoArchivoBean.getStatus().equals(Constantes.STATUS_DEPOSITO_ARCHIVO_ERROR_DEPOSITAR))
			{
				archivosDepositar.add(depositoArchivoBean);
			}	
			
		}
		
		return archivosDepositar;
		
	}
	
	

	public void eliminar(int index)
	{		
		// Eliminamos archivo de la lista 
		this.depositoArchivoWrapperBean.getDepositosArchivos().remove(index);				
		
		// Si la lista de archivos no esta vacia: Deshabilitamos boton "Depositar/Reintentar"
		//										  Verificamos si en la lista hay archivos con status PENDIENTE_DEPOSITO y si existe Habilitamos boton "Depositar/Reintentar"
		// Si la lista esta vacia: Inicializamos atribustos										  
		if(CollectionUtils.isNotEmpty(this.depositoArchivoWrapperBean.getDepositosArchivos()))
		{
			this.depositoArchivoWrapperBean.setShowBotonDepositar(Boolean.FALSE);
			this.depositoArchivoWrapperBean.setShowBotonLimpiar(Boolean.FALSE);
			
			for(DepositoArchivoBean depositoArchivoBean : this.depositoArchivoWrapperBean.getDepositosArchivos())
			{
				if(depositoArchivoBean.getStatus().equals(Constantes.STATUS_DEPOSITO_ARCHIVO_PENDIENTE_DEPOSITO))
				{
					this.depositoArchivoWrapperBean.setShowBotonDepositar(Boolean.TRUE);		
					this.depositoArchivoWrapperBean.setShowBotonLimpiar(Boolean.TRUE);
				}
			}
		}
		else
		{
			this.inicializar();
		}
		
	}
	


	
	/************** METODOS DE OPERACION  *************/	

	
	private void validar(DepositoArchivoBean depositoArchivoBean)
	{
	
		if(!this.validarExisteEnListado(depositoArchivoBean))
		{
			this.validarExisteMascaras(depositoArchivoBean);
			
			this.depositoArchivoWrapperBean.getDepositosArchivos().add(depositoArchivoBean);
			
		}
					
				
	}
	
	private Boolean validarExisteEnListado(DepositoArchivoBean depositoArchivoBean)
	{

		if(this.depositoArchivoWrapperBean.getDepositosArchivos().contains(depositoArchivoBean))
		{
			this.setMensajeError("Archivo "+ depositoArchivoBean.getArchivo().getFileName() +" ya fue agregado");			
			
			return Boolean.TRUE;
		}
		else
		{
			return Boolean.FALSE;
		}
	}	
	
	
	private void validarExisteMascaras(DepositoArchivoBean depositoArchivoBean)
	{
			
			// Si no existen mascara con el nombre del archivo. se poneun status no valido
			// Si existen. se pasa al validar el nombre del archivo
			if(CollectionUtils.isEmpty(this.listConfigSubprocesoOrigen))
			{ 
				this.setDescripcionAndStatus(depositoArchivoBean,MENSAJE_ARCHIVO_NO_VALIDO,Constantes.STATUS_DEPOSITO_ARCHIVO_NO_VALIDO);
			}
			else
			{
				this.validaNombre(depositoArchivoBean,this.listConfigSubprocesoOrigen);
			}
		

		   
	}	
	
	private void validaNombre(DepositoArchivoBean depositoArchivoBean,List<ConfigSubprocesoOrigenBean>  listConfigSubOrige)
	{
				
		forListConfigSubOrigen:
		for(ConfigSubprocesoOrigenBean configSubprocesoOrigenBean :listConfigSubOrige)
		{
			
			this.validaNombreByExpresioRegular(depositoArchivoBean,configSubprocesoOrigenBean);
			
			//FOP || 2415 || 07/05/2018
			if(ishoy) {
				this.setDescripcionAndStatus(depositoArchivoBean,MENSAJE_ARCHIVO_NO_VALIDO_POR_FECHA, Constantes.STATUS_DEPOSITO_ARCHIVO_NO_VALIDO);
				ishoy = Boolean.FALSE;
				break forListConfigSubOrigen;
			}			

			
			// Si estatus fue modificado a Pendinte Deposito. Rompemos el ciclo
			if(depositoArchivoBean.getStatus().equals(Constantes.STATUS_DEPOSITO_ARCHIVO_PENDIENTE_DEPOSITO))
			{				
				break forListConfigSubOrigen;				
			}			
										
		}
		
	} 
	
	private void validaNombreByExpresioRegular(DepositoArchivoBean depositoArchivoBean, ConfigSubprocesoOrigenBean configSubprocesoOrigenBean)
	{
		Boolean isArchivoCorrecto=Boolean.FALSE;
		Date    fechaArchivo=null;
		
		
		String nombreArchivo  = depositoArchivoBean.getArchivo().getFileName();
		String regexArchivo   = configSubprocesoOrigenBean.getMascaraArchivo();
		Short  indiceFecha 	  = configSubprocesoOrigenBean.getIndiceFecha();
		String formatoFecha   = configSubprocesoOrigenBean.getFormatoFecha();
				 						
		String anoMesDia=null;
		
		// Si se tiene indiceFecha indica que el archivo contiene en su nombre una fecha. 
		if(indiceFecha != null && formatoFecha != null)
		{	
			// Si valida que le nombre del archivo no menos tenga caracteres que los que se necesiata para obtener al añoMesDia
			if(nombreArchivo.length() < (indiceFecha + formatoFecha.length()))
			{
				anoMesDia="";	
			}
			else
			{			
				// Obtenemos fecha del nombre del archivo
				anoMesDia = nombreArchivo.substring(indiceFecha, indiceFecha + formatoFecha.length() );
			}	
		}		

		    Boolean isFechaCorrecta=Boolean.FALSE;
		    // Si se tiene indiceFecha y fecha en el archivo. Nos indica que se tiene que validar la fecha del archivo
			if(indiceFecha != null && anoMesDia != null)
			{
				
				SimpleDateFormat formato=new SimpleDateFormat(formatoFecha);
				formato.setLenient(false);
				
					try 
					{	
						// Si Ocurre una ParseException, indica que el formato de fecha no es correcto
						fechaArchivo = formato.parse(anoMesDia);				
						
						depositoArchivoBean.setFecha(fechaArchivo);
						
						isFechaCorrecta=Boolean.TRUE;
						
						//FOP || 2415 || 07/05/2018
						
						if(nombreArchivo.substring(0, 2).equals("DA")) {
							String strDate = formato.format(new Date());
							Date f = formato.parse(strDate);
						
							if(fechaArchivo.equals(f)) {
								ishoy = Boolean.TRUE;
							}else {
								if(fechaArchivo.compareTo(f) > 0) {
									ishoy = Boolean.TRUE;
								}
							}
						}
												
					} 
					catch (ParseException e) 
					{
						isFechaCorrecta=Boolean.FALSE;
					}								
			}
			else
			{
				isFechaCorrecta=Boolean.TRUE;
			}

		
			Pattern pattern= Pattern.compile(regexArchivo);		
			Matcher matcher = pattern.matcher(nombreArchivo);

			// Validamos el nombre del archivo con la ER
			if(isFechaCorrecta && matcher.matches())
			{
				isArchivoCorrecto=Boolean.TRUE;
			}
			else
			{
				isArchivoCorrecto=Boolean.FALSE;
			}
			
									
						
		
		if(isArchivoCorrecto)
		{
			
			String descripcion =  configSubprocesoOrigenBean.getSubproceso() != null  
								  ? (configSubprocesoOrigenBean.getSubproceso().getSubproceso() !=null  ? configSubprocesoOrigenBean.getSubproceso().getSubproceso().getDescSubproceso() : "" ) 
								  : "";
								  
			descripcion +=  configSubprocesoOrigenBean.getOrigenArchivo() != null ? " de "+configSubprocesoOrigenBean.getOrigenArchivo().getValor() : "";
			
			descripcion +=  configSubprocesoOrigenBean.getDetalleOrigen() != null ? " "+configSubprocesoOrigenBean.getDetalleOrigen().getValor() : "";
		
			DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
			descripcion +=  fechaArchivo != null ? " Fecha "+dateFormat.format(fechaArchivo) : "";	
			
			this.setDescripcionAndStatus(depositoArchivoBean, descripcion, Constantes.STATUS_DEPOSITO_ARCHIVO_PENDIENTE_DEPOSITO);						
			
			
			depositoArchivoBean.setConfigSubprocesoOrigen(configSubprocesoOrigenBean);
			
		}
		else
		{
			this.setDescripcionAndStatus(depositoArchivoBean,MENSAJE_ARCHIVO_NO_VALIDO, Constantes.STATUS_DEPOSITO_ARCHIVO_NO_VALIDO);
		}		
		
	}
	
	
	private void setMensajeError(String mensaje) 
	{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje));
    }
	
   
	private void setDescripcionAndStatus(DepositoArchivoBean depositoArchivoBean,String descripcion,Short status)
	{
	   depositoArchivoBean.setDescripcion(descripcion);
	   
	   this.setStatus(depositoArchivoBean, status);	   	   
	}
   
   private void setStatus(DepositoArchivoBean depositoArchivoBean,Short status)
   {
	   depositoArchivoBean.setStatus(status);
	   depositoArchivoBean.setDescStatus(Constantes.DES_STATUS_DEPOSITO_ARCHIVO.get(status));
	   depositoArchivoBean.setImgStatus(status.equals(Constantes.STATUS_DEPOSITO_ARCHIVO_PENDIENTE_DEPOSITO) 
			   							? "pendiente.png"
			                            : (status.equals(Constantes.STATUS_DEPOSITO_ARCHIVO_DEPOSITADO) 
			                               ? "depositado.png" 
			                               : "error.png"
			                              )
			   							);
	   
	   if(status.equals(Constantes.STATUS_DEPOSITO_ARCHIVO_NO_VALIDO) || status.equals(Constantes.STATUS_DEPOSITO_ARCHIVO_ERROR_DEPOSITAR))
	   {
		   depositoArchivoBean.setErroneo(Boolean.TRUE);
	   }
	   else
	   {
		   depositoArchivoBean.setErroneo(Boolean.FALSE);
	   }
	   	   	   
	   
	   if(status.equals(Constantes.STATUS_DEPOSITO_ARCHIVO_PENDIENTE_DEPOSITO))
	   {
		   this.depositoArchivoWrapperBean.setShowBotonDepositar(Boolean.TRUE);
		   this.depositoArchivoWrapperBean.setShowBotonLimpiar(Boolean.TRUE);
	   }
	   
   }



   private void copiarArchivo(DepositoArchivoBean depositoArchivoBean)  
   {
	    Boolean isDepositado=Boolean.FALSE;
	    String  detalleError=null;
	    String fileName = FilenameUtils.getName(depositoArchivoBean.getArchivo().getFileName());
	    String ruta = depositoArchivoBean.getConfigSubprocesoOrigen().getSubproceso().getRutaFilesystem();
	    InputStream  inputStream=null;
	    OutputStream outputStream=null;
		try 
		{			
			
			File rutaOut=new File(ruta);
			 			
			if(rutaOut.exists())
			{
				if(rutaOut.canWrite())
				{
					inputStream = depositoArchivoBean.getArchivo().getInputstream();

					File fileOut=new File(ruta,fileName);					
					
					outputStream = new FileOutputStream(fileOut);		
										
				
					int bytesCopy =-10;
					
					bytesCopy = IOUtils.copy(inputStream, outputStream);
					
					
					 
					 if(bytesCopy != -10)
					 {
						 isDepositado=Boolean.TRUE;
					 }
					 else
					 {
						 detalleError = MENSAJE_ERROR_DEPOSITO;
						 isDepositado=Boolean.FALSE;
					 }
					 					
				}
				else
				{
					detalleError = MENSAJE_SIN_PERMISOS_ESCRITURA;
					isDepositado=Boolean.FALSE;
				}
				
			}
			else
			{
				detalleError = MENSAJE_EXISTE_RUTA_DEPOSITO; 
				isDepositado=Boolean.FALSE;
			}
			
			
			
			if(isDepositado)
			{
				this.setStatus(depositoArchivoBean, Constantes.STATUS_DEPOSITO_ARCHIVO_DEPOSITADO);				
			}
			else
			{
				this.setStatus(depositoArchivoBean, Constantes.STATUS_DEPOSITO_ARCHIVO_ERROR_DEPOSITAR);
				
				if(detalleError != null)
				{
					depositoArchivoBean.setDescStatus(depositoArchivoBean.getDescStatus()+". Error: "+detalleError);
				}	
				
				this.depositoArchivoWrapperBean.setReintentoDeposito(Boolean.TRUE);
				this.depositoArchivoWrapperBean.setShowBotonDepositar(Boolean.TRUE);
				this.depositoArchivoWrapperBean.setShowBotonLimpiar(Boolean.TRUE);
			}
			
			
		} 
		catch (IOException ex) 
		{
			detalleError= ex.getMessage() != null ? ex.getMessage() : "";
			
			this.setStatus(depositoArchivoBean, Constantes.STATUS_DEPOSITO_ARCHIVO_ERROR_DEPOSITAR);
			
			if(detalleError != null)
			{
				depositoArchivoBean.setDescStatus(depositoArchivoBean.getDescStatus()+". Excepción: "+detalleError);
			}	
			
			this.depositoArchivoWrapperBean.setReintentoDeposito(Boolean.TRUE);
			this.depositoArchivoWrapperBean.setShowBotonDepositar(Boolean.TRUE);
			this.depositoArchivoWrapperBean.setShowBotonLimpiar(Boolean.TRUE);
			
			LOGGER.error(ex.getMessage(),ex);							

			
		}
		catch(Exception ex)
		{
			detalleError= ex.getMessage() != null ? ex.getMessage() : "";
			
			this.setStatus(depositoArchivoBean, Constantes.STATUS_DEPOSITO_ARCHIVO_ERROR_DEPOSITAR);
			
			if(detalleError != null)
			{
				depositoArchivoBean.setDescStatus(depositoArchivoBean.getDescStatus()+". Excepción: "+detalleError);
			}	
			
			this.depositoArchivoWrapperBean.setReintentoDeposito(Boolean.TRUE);
			this.depositoArchivoWrapperBean.setShowBotonDepositar(Boolean.TRUE);
			this.depositoArchivoWrapperBean.setShowBotonLimpiar(Boolean.TRUE);
			
			LOGGER.error(ex.getMessage(),ex);
			
		}		
	   finally 
	   {
		   if(inputStream != null)
		   {  
			   IOUtils.closeQuietly(inputStream);
		   } 
	       
		   if(outputStream != null)
		   {
			   IOUtils.closeQuietly(outputStream);
		   }
	    }
	}   
	
	
	/************** GET AND SET *************/
	
	public DepositoArchivoWrapperBean getDepositoArchivoWrapperBean() 
	{
		return depositoArchivoWrapperBean;
	}


	public void setDepositoArchivoWrapperBean(
			DepositoArchivoWrapperBean depositoArchivoWrapperBean) {
		this.depositoArchivoWrapperBean = depositoArchivoWrapperBean;
	}
	
	

}