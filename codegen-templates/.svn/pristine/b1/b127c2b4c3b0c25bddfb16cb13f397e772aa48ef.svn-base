<#--
 Plantilla para la generación del objeto static metamodel utilizado por Criteria API de JPA 2.0
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
 *     everis S.A.
 *     www.everis.com
 */
<#include "parse/common-macros.ftl" parse="true"/>
<#assign entity = entityInfo(model) />
<#assign classname = fmkUtilities("javaClass", destination.filename) />
<#assign entityclassname = fmkUtilities("rightCut", classname, 1) />
<#assign classSuffix = "" />
<#if entity.isSubclass >
  <#assign classSuffix = " extends " + fmkUtilities("classNameFromPackage", model.getSuperClass().getValue()) + "_" />
</#if>
<#assign classPrefix = "" />
<#if entity.isAbstract >
  <#assign classPrefix = "abstract " />
</#if>
package ${fmkUtilities("javaPackage", destination.filename)};

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<#include "parse/common-imports.ftl" parse="true"/>
<@queryImports fmkUtilities("javaPackage", destination.filename)/>

/**
<#if locale == "es">
 * Static metamodel para la entidad ${entityclassname}
<#else>
 * Static metamodel of entity ${entityclassname}
</#if>
 */
@StaticMetamodel(${entityclassname}.class)
public ${classPrefix}class ${classname}${classSuffix} {
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.info.isCriterionUsable  &&
       !elemInfo.isVersion>
    <#if !elemInfo.isOneToMany && !elemInfo.isManyToMany >
    public static volatile SingularAttribute<${entityclassname}, ${elemInfo.info.fieldType}> ${elemInfo.info.fieldName};    
    </#if>
  </#if>
</#list>
}
