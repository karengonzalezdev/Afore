<#--
 Plantilla para la generacion del controller que publica el servicio REST
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
<#assign controllerPackage = fmkUtilities("javaPackage", destination.filename) />
<#assign wsClassName = fmkUtilities("javaClass", source.filename) />
<#assign wsName = wsClassName?uncap_first />
<#assign wsPackage = fmkUtilities("javaPackage", source.filename) />
<#-- --> 
package ${controllerPackage};

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeveris.core.rest.service.response.wrapper.RestResponse;

<#if controllerPackage != wsPackage >
import ${wsPackage}.${wsClassName};
</#if>

<#include "parse/common-services.ftl" parse="true"/>
<@serviceImports controllerPackage/>

/**
<#if locale == "es" >
 * Implementación de Controller para servicio REST
<#else>
 * Controller implementation of REST service
</#if>
 */
@Controller
@RequestMapping(value = "/${wsName}")
public class ${classname} {

    @Resource
    private ${wsClassName} ${wsName};
    
<#--
 Every method of business interface is declared,
 adding Spring MVC annotations
-->
<#list model.getMethods() as method>
  <#if method.isPublic()>
    <#assign parameters = "" />
    <#assign wsParams = "" />
    <#assign numParam = method.getParameters()?size />
    <#if numParam gt 1>
    
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    ERROR!!: method ${method.getName()} must have zero or one parameter, because uses @RequestBody with JSON
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    </#if>
    <#list method.getParameters() as param>
      <#if wsParams != "">
        <#assign wsParams = wsParams + ", " />
      </#if>
      <#if parameters != "">
        <#assign parameters = parameters + ", " />
      </#if>
      <#assign paramType = getFullType(param.getType()) />
      <#assign parameters = parameters + "@RequestBody " + paramType + " " + param.getName() />
      <#assign wsParams = wsParams + param.getName() />
    </#list>

    /**
    <#if locale == "es" >
     * Método de negocio publicado con REST
    <#else>
     * Business method published using REST
    </#if>
     */
    <#assign methodReturns = method.getReturns().getValue() />
    <#if methodReturns != "void">
      <#assign methodReturns = getFullType(method.getReturns()) />
    <#else>
      <#assign methodReturns = "Void" />
    </#if>
    @RequestMapping(value = "/${method.getName()}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse<${methodReturns}> ${method.getName()}(${parameters}) {
        
        RestResponse<${methodReturns}> resp = new RestResponse<${methodReturns}>();
        
        try {
    <#if methodReturns != "Void">
            resp.setResponse(${wsName}.${method.getName()}(${wsParams}));
    <#else>
            ${wsName}.${method.getName()}(${wsParams});
    </#if>
            resp.setSuccess(true);
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setErrorMessage(e.getMessage());
        }
        
        return resp;
    }
  </#if>
</#list>
}