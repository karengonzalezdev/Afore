<#--
 Plantilla para la generacion de la implementación de los Repository de entidad con JPA 2.0
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
<#--base package es [base].repository.impl-->
<#assign basepackage = fmkUtilities("javaPackage", destination.filename) />
<#--Quita dos paquetes y agrega ".model" para el paquete del entity-->
<#assign daopackage = fmkUtilities("javaFatherPackage", basepackage) />
<#assign entitypackage = fmkUtilities("javaFatherPackage", daopackage) + ".model" />
<#assign daoimplclassname = fmkUtilities("javaClass", destination.filename) />
<#--Quita el "Impl"-->
<#assign daoclassname = fmkUtilities("rightCut", daoimplclassname, 4) />
<#assign daoclassnamesimple = daoclassname />
<#--Quita el "Repository" y agrega "DTO"-->
<#--<#assign entityclassname = fmkUtilities("rightCut", daoclassname, 10) + "DTO" />-->
<#assign entityclassname = fmkUtilities("rightCut", daoclassname, 10) />
<#if params.entitySuffix?? >
	<#assign entityclassname =  entityclassname + params.entitySuffix />
</#if>
<#assign queryclassname = entityclassname + "Query" />
<#assign classPrefix = "" />
<#if !entity.isSubclass>
  <#assign superClass = "AbstractJPARepository<" + entityclassname + "," + entity.idClassName + "," + queryclassname + ">" />
</#if>
<#if entity.isAbstract>
  <#assign classPrefix = "abstract " />
  <#assign daoclassname = daoclassname + "<E, Q>" />
  <#assign daoimplclassname = daoimplclassname + "<E extends " + entityclassname + ", Q extends " + queryclassname + "<E>>" />
  <#assign superClass = "AbstractJPARepository<E, " + entity.idClassName + ", Q>" />
</#if>
<#if entity.isSubclass>
  <#assign superClass = fmkUtilities("classNameFromPackage", model.getSuperClass().getValue()) />
  <#assign superClass = fmkUtilities("rightCut", superClass, 13) + "RepositoryImpl" />
  <#assign superClass = superClass + "<" + entityclassname + ", " + queryclassname + ">" />
</#if>
<#-- -->
package ${fmkUtilities("javaPackage", destination.filename)};

import javax.annotation.Resource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

<#if !entity.isSubclass || entity.isAbstract  >
import ${common_persistence_jpaPackage}.repository.impl.AbstractJPARepository;
</#if>

import ${daopackage}.${daoclassnamesimple};
import ${entitypackage}.${entityclassname};
import ${entitypackage}.${queryclassname};
<#if entity.hasEmbeddedId >
import ${entity.idFullClassName};
</#if>
<#-- 
<#include "parse/common-imports.ftl" parse="true"/>
<@daoImplImports fmkUtilities("javaPackage", destination.filename)/>
-->

/**
<#if locale == "es" >
  <#if entity.isSubclass>
 * Implementación de Repository básico con JPA 2.0 que extiende de super entidad
  <#else>
 * Implementación de Repository básico con JPA 2.0
  </#if>
<#else>
  <#if entity.isSubclass>
 * Basic JPA 2.0 DAO implementation that extends interface of super entity
  <#else>
 * Basic JPA 2.0 DAO implementation
  </#if>
</#if>
<#if !entity.isSubclass || entity.isAbstract  >
 * @see ${common_persistence_jpaPackage}.repository.impl.AbstractJPARepository;
</#if>
 */
@Repository
public ${classPrefix}class ${daoimplclassname} extends ${superClass}
        implements ${daoclassname} {
<#if !entity.isSubclass>

    @PersistenceContext(unitName="${params.module}")
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }    
</#if>
<#if !entity.isSubclass>

    /**
     * @see ${common_persistence_jpaPackage}.repository.impl.AbstractJPARepository#getIdentifierName()
     */
    @Override
    protected String getIdentifierName() {
        return "${entity.idName}";
    }

    /**
     * @see ${common_persistence_jpaPackage}.repository.impl.AbstractJPARepository#getIdentifier()
     */
    @Override
    protected ${entity.idClassName} getIdentifier(${entityclassname} entity) {
        return entity.get${entity.idName?cap_first}();
    }
</#if>

    /**
     * @see ${common_persistence_jpaPackage}.repository.impl.AbstractJPARepository#isReadOnly()
     */
    protected boolean isReadOnly() {
<#if entity.isReadOnly>
        return true;
<#else>
        return false;
</#if>
    }
}