<#--
 Plantilla para la generacion de la interfaz de los Repository de entidad
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
<#include "parse/common-macros.ftl" parse="true"/>
<#assign entity = entityInfo(model) />
<#assign entitypackage = fmkUtilities("javaPackage", destination.filename) />
<#--Quita el ".Repository" y agrega ".model" para deducir el paquete del entity-->
<#assign entitypackage = fmkUtilities("javaFatherPackage", entitypackage) />
<#assign entitypackage = entitypackage + ".model" />
<#assign daoclassname = fmkUtilities("javaClass", destination.filename) />

<#assign entityclassname = fmkUtilities("rightCut", daoclassname, 10) />

<#if params.entitySuffix?? >
	<#assign entityclassname =  entityclassname + params.entitySuffix />
</#if>

<#assign queryclassname = entityclassname + "Query" />
<#if !entity.isSubclass>
  <#assign superClass = "EntityRepository<" + entityclassname + "," + entity.idClassName + "," + queryclassname + ">" />
</#if>
<#if entity.isAbstract>
  <#assign daoclassname = daoclassname + "<E extends " + entityclassname + ", Q extends " + queryclassname + "<E>>" />
  <#assign superClass = "EntityRepository<E," + entity.idClassName + ",Q>" />
</#if>
<#if entity.isSubclass>
  <#assign superClass = fmkUtilities("classNameFromPackage", model.getSuperClass().getValue()) />
  <#assign superClass = fmkUtilities("rightCut", superClass, 3) + "Repository" />
  <#assign superClass = superClass + "<" + entityclassname + ", " + queryclassname + ">" />
</#if>
<#-- -->
package ${fmkUtilities("javaPackage", destination.filename)};

<#if !entity.isSubclass || entity.isAbstract  >
import ${common_corePackage}.persistence.repository.EntityRepository;
</#if>

import ${entitypackage}.${entityclassname};
import ${entitypackage}.${queryclassname};
<#if entity.hasEmbeddedId >
import ${entity.idFullClassName};
</#if>

/**
<#if locale == "es" >
  <#if entity.isSubclass>
 * Interfaz Repository que extiende de interfaz de super entidad
  <#else>
 * Interfaz Repository que extiende de interfaz genérica
  </#if>
<#else>
  <#if entity.isSubclass>
 * Repository interface that extends interface of super entity
  <#else>
 * Repository interface that extends generic interface
  </#if>
</#if>
<#if !entity.isSubclass || entity.isAbstract  >
 * @see ${common_corePackage}.persistence.repository.EntityRepository
</#if>
 */
public interface ${daoclassname} extends ${superClass} {

}