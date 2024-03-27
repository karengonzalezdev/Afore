<#--
 Plantilla para la generacion de la interfaz del web service con CXF
 Copyright everis
 Template Freemarker 2.3
-->
/*
<#if locale == "es" >
 * Archivo: ${fmkUtilities("javaClass", destination.filename)}.java
 *
 * Proyecto: ${params.title}
<#else>
 * File: ${fmkUtilities("javaClass", destination.filename)}.java
 *
 * Project: ${params.title}
</#if>
 *
 * Generated file! Do not modify.
 *
 * Developed by:
 *     everis
 *     www.everis.com
 */
<#assign classname = fmkUtilities("javaClass", destination.filename) />
<#assign wsInterfacePackage = fmkUtilities("javaPackage", destination.filename) />
<#assign wsName = fmkUtilities("javaClass", destination.filename) />
<#-- --> 
package ${wsInterfacePackage};

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

<#include "parse/common-services.ftl" parse="true"/>
<@serviceImports wsInterfacePackage/>

/**
<#if locale == "es" >
 * Interfaz de web service
<#else>
 * Web service interface
</#if>
 */
@WebService(name="${wsName}")
public interface ${classname} {
<#list model.getMethods() as method>
  <#if method.isPublic()>
    <#assign parameters = "" />
    <#list method.getParameters() as param>
      <#if parameters != "">
        <#assign parameters = parameters + ", " />
      </#if>
      <#assign paramType = fmkUtilities("classNameFromPackage", param.getType()) />
      <#assign parameters = parameters + "@WebParam(name=\"" + param.getName() + "\") " + paramType + " " + param.getName() />
    </#list>

    /**
    <#if locale == "es" >
     * Método de negocio publicado con Web Service
    <#else>
     * Business method published with Web Service
    </#if>
     */
    <#-- must be consistent with javamethodsmapper pattern used in responses generation -->
    <#assign methodResult = method.getName()?cap_first + "Response" />
    <#assign methodReturns = wsName + methodResult />
    @WebResult(name="${methodResult}")
    ${methodReturns} ${method.getName()}(
            ${parameters});
  </#if>
</#list>
}