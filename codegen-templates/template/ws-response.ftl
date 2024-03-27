<#--
 Plantilla para la generacion de la clase de respuesta de un método de web service
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
 <#include "parse/common-services.ftl" parse="true"/>
<#assign classname = fmkUtilities("javaClass", destination.filename) />
<#assign idx = 0 />
<#assign returnType = "" />
<#assign methodReturnsGeneric = ""/>
<#list model.getMethods() as method>
  <#if method.isPublic()>
    <#if methodNumber == idx >
      <#assign returnType = method.getReturns().getValue() />
      <#assign methodReturnsGeneric = genericType(method.getReturns()) />
    </#if>
    <#assign idx = idx + 1 />
  </#if>
</#list>
<#assign returnSimpleType = fmkUtilities("classNameFromPackage", returnType) />

<#assign responseObject = "response" />
<#-- --> 
package ${fmkUtilities("javaPackage", destination.filename)};

import com.jeveris.core.ws.service.response.wrapper.AbstractWebServiceResponse;
<#-- Avoids to import void or primitives -->
<#if returnType != "void" && returnSimpleType != returnType && !returnType?starts_with("java.lang") >
import ${returnType};
</#if>

/**
<#if locale == "es" >
 * Clase wrapper para retorno de web service
<#else>
 * Wrapper class used for return of web service method
</#if>
 */
public class ${classname} extends AbstractWebServiceResponse {

<#if returnType != "void" >

    <#if methodReturnsGeneric != "">
    	<#assign returnSimpleType = returnSimpleType+"<"+methodReturnsGeneric+">" />
    </#if>

    private ${returnSimpleType} ${responseObject};
    
    public ${returnSimpleType} get${responseObject?cap_first}() {
        return ${responseObject};
    }

    public void set${responseObject?cap_first}(${returnSimpleType} ${responseObject}) {
        this.${responseObject} = ${responseObject};
    }
</#if>
}