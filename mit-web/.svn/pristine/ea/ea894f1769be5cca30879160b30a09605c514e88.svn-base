$(document).ready(function() {
	
});	
function validarFormPermisos(_buttonId) {
	if ($("select[name$='idSelectOnePO_input'] option:selected").val() === '') {
		$("#mensaje_validacion-permiso").text("Debe seleccionar el Proceso");
		$('#indicadores-error-message-permiso').show();
		return;
	}
	if ($("select[name$='idSelectOneSubPO_input'] option:selected").val() === '') {
		$("#mensaje_validacion-permiso").text("Debe seleccionar el Subproceso");
		$('#indicadores-error-message-permiso').show();
		return;
	}
	if ($("input[name$='permisoModal']:checked").val() == null) {
		$("#mensaje_validacion-permiso").text("Debe seleccionar el Permiso");
		$('#indicadores-error-message-permiso').show();
		return;
	}
	PF(_buttonId).jq.click();
}
function validarFormIndicadores(_buttonId) {

	$('#indicadores-error-message').hide();
	if ($("input[id$='nombreIndicador']").val() === '') {
		$("#mensaje_validacion").text("Debe ingresar el nombre del indicador");
		$('#indicadores-error-message').show();
		$("input[id$='nombreIndicador']").focus();
		return;
	}
	if ($("#idFormAgregarIndicadores\\:descripcion").val() === '') {
		$("#mensaje_validacion").text(
				"Debe ingresar la descripción del indicador");
		$('#indicadores-error-message').show();
		$("#idFormAgregarIndicadores\\:descripcion").focus();
		return;
	}
	if ($("select[name$='idSelectOneTipoInd_input'] option:selected").val() === '') {
		$("#mensaje_validacion").text("Debe seleccionar el tipo de indicador");
		$('#indicadores-error-message').show();
	}
	if ($("#idFormAgregarIndicadores\\:idReglaNegocio").val() === '') {
		$("#mensaje_validacion").text("Debe ingresar la regla de negocio");
		$('#indicadores-error-message').show();
		$("#idFormAgregarIndicadores\\:idReglaNegocio").focus();
		return;
	}
	if ($("#idFormAgregarIndicadores\\:tablaPermisos tbody tr td").length < 4) {
		$("#mensaje_validacion").text("Debe ingresar al menos un Permiso");
		$('#indicadores-error-message').show();
		return;
	}
	PF(_buttonId).jq.click();
}
function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode
	if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;

	return true;
}
function validarFormEditarIndicadores(_buttonId) {
	$('#indicadores-error-message').hide();
	if ($("#idFormEditarIndicadores\\:descripcion").val() === '') {
		$("#mensaje_validacion").text(
				"Debe ingresar la descripción del indicador");
		$('#indicadores-error-message').show();
		$("#idFormAgregarIndicadores\\:descripcion").focus();
		return;
	}

	if ($("#idFormEditarIndicadores\\:regla").val() === '') {
		$("#mensaje_validacion").text("Debe ingresar la regla de negocio");
		$('#indicadores-error-message').show();
		$("#idFormAgregarIndicadores\\:regla").focus();
		return;
	}
	PF(_buttonId).jq.click();
}