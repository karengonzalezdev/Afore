<#--
 Plantilla para la generacion de la implementación de los Repository de entidad con Hibernate
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
  <#assign superClass = "AbstractHibernateRepository<" + entityclassname + "," + entity.idClassName + "," + queryclassname + ">" />
</#if>
<#if entity.isAbstract>
  <#assign classPrefix = "abstract " />
  <#assign daoclassname = daoclassname + "<E, Q>" />
  <#assign daoimplclassname = daoimplclassname + "<E extends " + entityclassname + ", Q extends " + queryclassname + "<E>>" />
  <#assign superClass = "AbstractHibernateRepository<E," + entity.idClassName + ",Q>" />
</#if>
<#if entity.isSubclass>
  <#assign superClass = fmkUtilities("classNameFromPackage", model.getSuperClass().getValue()) />
  <#assign superClass = fmkUtilities("rightCut", superClass, 13) + "RepositoryImpl" />
  <#assign superClass = superClass + "<" + entityclassname + ", " + queryclassname + ">" />
</#if>
<#-- -->
package ${fmkUtilities("javaPackage", destination.filename)};

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

<#if !entity.isSubclass || entity.isAbstract  >
import ${common_persistence_hibernatePackage}.repository.impl.AbstractHibernateRepository;
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
 * Implementación de Repository básico con Hibernate que extiende de super entidad
  <#else>
 * Implementación de Repository básico con Hibernate
  </#if>
<#else>
  <#if entity.isSubclass>
 * Basic Hibernate Repository implementation that extends interface of super entity
  <#else>
 * Basic Hibernate Repository implementation
  </#if>
</#if>
<#if !entity.isSubclass || entity.isAbstract  >
 * @see ${common_persistence_hibernatePackage}.repository.impl.AbstractHibernateRepository;
</#if>
 */
@Repository
public ${classPrefix}class ${daoimplclassname} extends ${superClass}
        implements ${daoclassname} {

<#if !entity.isSubclass>

    @Resource
    public void setSessionFactory${params.module?cap_first}(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
</#if>

    /**
     * @see ${common_persistence_hibernatePackage}.repository.impl.AbstractHibernateRepository#isReadOnly()
     */
    protected boolean isReadOnly() {
<#if entity.isReadOnly>
        return true;
<#else>
        return false;
</#if>
    }
}