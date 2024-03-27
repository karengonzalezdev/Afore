<#--
 Plantilla para la generacion de la implementación del servicio con CXF
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
<#assign wsImplPackage = fmkUtilities("javaPackage", destination.filename) />
<#assign wsInterfacePackage = fmkUtilities("javaFatherPackage", wsImplPackage) />
<#--Quita el "Impl"-->
<#assign wsInterfaceName = fmkUtilities("rightCut", classname, 4) />
<#assign servClassName = fmkUtilities("javaClass", source.filename) />
<#assign servName = servClassName?uncap_first />
<#assign servPackage = fmkUtilities("javaPackage", source.filename) />
<#-- --> 
package ${wsImplPackage};

<#-- problemas usando javax.annotation.Resource--> 
import org.springframework.beans.factory.annotation.Autowired;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.jeveris.core.ws.service.exception.WebServiceException;

import mx.profuturo.nci.ws.service.AbstractWebServiceInvoker;

import ${wsInterfacePackage}.${wsInterfaceName};
import ${servPackage}.${servClassName};

<#list model.getMethods() as method>
  <#if method.isPublic()>
    <#-- must be consistent with javamethodsmapper pattern used in responses generation -->
    <#assign methodReturns = wsInterfaceName + method.getName()?cap_first + "Response" />
import ${wsInterfacePackage}.${methodReturns};
  </#if>
</#list>

<#include "parse/common-services.ftl" parse="true"/>
<@serviceImports wsImplPackage/>

/**
<#if locale == "es" >
 * Implementación de web service
<#else>
 * Web service implementation
</#if>
 */
@WebService(portName="${wsInterfaceName}Port", serviceName="${wsInterfaceName}Service",
        endpointInterface="${wsInterfacePackage}.${wsInterfaceName}")
@Service
public class ${classname} implements ${wsInterfaceName} {

    @Autowired
    private ${servClassName} ${servName};
<#list model.getMethods() as method>
  <#if method.isPublic()>
    <#assign parameters = "" />
    <#assign servParams = "" />
    <#assign paramTypes = "" />
    <#list method.getParameters() as param>
      <#if servParams != "">
        <#assign servParams = servParams + ", " />
      </#if>
      <#if parameters != "">
        <#assign parameters = parameters + ", " />
      </#if>
      <#if paramTypes != "">
        <#assign paramTypes = paramTypes + ", " />
      </#if>
      <#assign paramType = fmkUtilities("classNameFromPackage", param.getType()) />
      <#assign parameters = parameters + "final " + paramType + " " + param.getName() />
      <#assign servParams = servParams + param.getName() />
      <#assign paramTypes = paramTypes + paramType />
    </#list>

    /**
    <#if locale == "es" >
     * Método de negocio publicado con Web Service
    <#else>
     * Business method published with Web Service
    </#if>
     * @see ${servPackage}.${servClassName}#${method.getName()}(${paramTypes})
     */
    <#-- must be consistent with javamethodsmapper pattern used in responses generation -->
    <#assign wsMethodReturns = wsInterfaceName + method.getName()?cap_first + "Response" />
    <#assign methodReturns = method.getReturns().getValue() />    
    <#if methodReturns != "void">
      <#assign methodReturns = fmkUtilities("classNameFromPackage", methodReturns) />
    </#if>
    <#assign methodReturnsGeneric = genericType(method.getReturns()) />
    <#if methodReturnsGeneric != "">
    	<#assign methodReturns = methodReturns+"<"+methodReturnsGeneric+">" />
    </#if>
    public ${wsMethodReturns} ${method.getName()}(${parameters}) {
        return new AbstractWebServiceInvoker<${wsMethodReturns}>() {

            @Override
            public void invokeServiceMethod(${wsMethodReturns} wsResponse)
                    throws WebServiceException {

    <#if methodReturns != "void">
                ${methodReturns} resp = ${servName}.${method.getName()}(${servParams});
                wsResponse.setResponse(resp);
    <#else>
                ${servName}.${method.getName()}(${servParams});
    </#if>
            };

        }.invokeService();
            
    }
  </#if>
</#list>
}