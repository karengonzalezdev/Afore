<#--
 Plantilla para la generación del objeto de criterios de búsqueda con Hibernate
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
<#assign classname = fmkUtilities("javaClass", destination.filename) />
<#assign entityclassname = fmkUtilities("rightCut", classname, 5) />
<#if !entity.isSubclass>
  <#assign superClass = "AbstractHibernateQueryEntity<" + entityclassname + ">" />
</#if>
<#if entity.isAbstract >
  <#assign classname = classname + "<T extends " + entityclassname + ">" />
  <#assign superClass = "AbstractHibernateQueryEntity<T>" />
</#if>
<#if entity.isSubclass >
  <#assign superClass = fmkUtilities("classNameFromPackage", model.getSuperClass().getValue()) + "Query<" + entityclassname + ">" />
</#if>
<#assign classPrefix = "" />
<#if entity.isAbstract >
  <#assign classPrefix = "abstract " />
</#if>
package ${fmkUtilities("javaPackage", destination.filename)};

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
<#if entity.hasParents>
import org.hibernate.sql.JoinType;
</#if>
<#if entity.hasRelations || entity.hasChildren>
import org.hibernate.FetchMode;
</#if>
<#if entity.hasStrings>
import org.hibernate.criterion.SimpleExpression;
</#if>
<#if entity.hasDates>
import ${common_corePackage}.utils.DateUtil;
</#if>
import ${common_corePackage}.utils.BeanFormatter;
<#if entity.hasStrings>
import ${common_corePackage}.persistence.entity.TextComparator;
</#if>
import ${common_corePackage}.persistence.exception.PersistenceException;
<#if !entity.isSubclass>
import ${common_persistence_hibernatePackage}.entity.AbstractHibernateQueryEntity;
</#if>

<#include "parse/common-imports.ftl" parse="true"/>
<@queryImports fmkUtilities("javaPackage", destination.filename)/>

/**
<#if locale == "es">
 * Clase con criterios de búsqueda para la entidad ${entityclassname}
<#else>
 * Class with search criteria to the entity ${entityclassname}
</#if>
 */
public ${classPrefix}class ${classname} extends ${superClass} {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
<#include "parse/dto-query-common.ftl" parse="true"/>
<@queryCommon/>

    /**
<#if locale == "es">
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilización en búsquedas
<#else>
     * Adds recursively criteria to Hibernate for use in search
</#if>
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {
<#if entity.isSubclass>

        super.addCriteria(criteria, useOrder);
</#if>
<#--
 Campos de tabla
-->
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.info.isCriterionUsable  &&
       !elemInfo.isVersion>
    <#if !elemInfo.isManyToOne >
      <#if elemInfo.info.fieldType == "Date">

        if (get${elemInfo.info.fieldName?cap_first}Min() != null) {
            criteria.add(Restrictions.ge(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}Min()));
        }

        if (get${elemInfo.info.fieldName?cap_first}Max() != null) {
            criteria.add(Restrictions.le(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}Max()));
        }
      <#else>

        if (get${elemInfo.info.fieldName?cap_first}() != null) {
        <#if elemInfo.info.fieldType == "String">
          <#if elemInfo.searchTypes == "" || 
               (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.LIKE") && elemInfo.searchTypes?contains("SearchType.EQ")) >
            SimpleExpression exp = null;
            if (get${elemInfo.info.fieldName?cap_first}Comparator() == TextComparator.EQUALS) {
                exp = Restrictions.eq(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}());
            } else {
                exp = Restrictions.like(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}(), 
                        getMatchMode(get${elemInfo.info.fieldName?cap_first}Comparator()));
            }
          <#elseif elemInfo.searchTypes?contains("SearchType.LIKE") && !elemInfo.searchTypes?contains("SearchType.EQ") >
            SimpleExpression exp = Restrictions.like(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}(), 
                        getMatchMode(get${elemInfo.info.fieldName?cap_first}Comparator()));
          <#else>
            SimpleExpression exp = Restrictions.eq(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}());
          </#if>        
          <#if elemInfo.searchIgnoreCase == "true">
            if (is${elemInfo.info.fieldName?cap_first}IgnoreCase()) {
                criteria.add(exp.ignoreCase());
            } else {
                criteria.add(exp);
            }
          <#else>
            criteria.add(exp);
          </#if>        
        <#else>
            criteria.add(Restrictions.eq(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}()));
        </#if>        
        }
        <#if elemInfo.info.fieldTypeWrapper == "Integer" ||
             elemInfo.info.fieldTypeWrapper == "Long" ||
             elemInfo.info.fieldType == "String" ||
             elemInfo.info.fieldIsEnum>
          <#if elemInfo.searchTypes == "" || 
               (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.IN")) >

        if (get${elemInfo.info.fieldName?cap_first}In().size() > 0) {
            criteria.add(Restrictions.in(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}In()));
        }

          </#if>
        </#if>
        <#if elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.RANGE") >

        if (get${elemInfo.info.fieldName?cap_first}Min() != null) {
            criteria.add(Restrictions.ge(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}Min()));
        }

        if (get${elemInfo.info.fieldName?cap_first}Max() != null) {
            criteria.add(Restrictions.le(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}Max()));
        }
        </#if>
      </#if>
    <#else>

      <#if locale == "es">
        // Campo entidad padre '${elemInfo.info.fieldName}'
        
        // Si se hace join fetch con el padre
      <#else>
        // Parent entity field '${elemInfo.info.fieldName}'
        
        // If parent is fetched
      </#if>
        Criteria ${elemInfo.info.fieldName}Criteria = null;
        if (isInnerJoin${elemInfo.info.fieldName?cap_first}()) {
            if (hasSelectFields()) {
                throw new PersistenceException("cannot_make_inner_join_selected_fields");
            }
            ${elemInfo.info.fieldName}Criteria = criteria.createCriteria(${elemInfo.info.fieldName?upper_case}, "a_" + ${elemInfo.info.fieldName?upper_case}, JoinType.INNER_JOIN);
        } else if (isLeftJoin${elemInfo.info.fieldName?cap_first}()) {
            if (hasSelectFields()) {
                throw new PersistenceException("cannot_make_left_join_selected_fields");
            }
            ${elemInfo.info.fieldName}Criteria = criteria.createCriteria(${elemInfo.info.fieldName?upper_case}, "a_" + ${elemInfo.info.fieldName?upper_case}, JoinType.LEFT_OUTER_JOIN);
        }
        
        if (get${elemInfo.info.fieldName?cap_first}() != null) {
            if (get${elemInfo.info.fieldName?cap_first}().get${elemInfo.info.parentIdName?cap_first}() == null) {
                if (${elemInfo.info.fieldName}Criteria == null) {
                    ${elemInfo.info.fieldName}Criteria = criteria.createCriteria(${elemInfo.info.fieldName?upper_case}, "a_" + ${elemInfo.info.fieldName?upper_case});
                }
                get${elemInfo.info.fieldName?cap_first}().addCriteria(${elemInfo.info.fieldName}Criteria, useOrder);
            } else {
                ${elemInfo.info.fieldType} parent = new ${elemInfo.info.fieldType}();
                parent.set${elemInfo.info.parentIdName?cap_first}(get${elemInfo.info.fieldName?cap_first}().get${elemInfo.info.parentIdName?cap_first}());
                criteria.add(Restrictions.eq(${elemInfo.info.fieldName?upper_case}, parent));
            }
        }

        if (get${elemInfo.info.fieldName?cap_first}IdIn().size() > 0) {
            criteria.add(Restrictions.in(${elemInfo.info.fieldName?upper_case}, get${elemInfo.info.fieldName?cap_first}IdIn()));
        }
    </#if>
    <#if !elemInfo.isIdentifier>

        if (is${elemInfo.info.fieldName?cap_first}IsNull()) {
            criteria.add(Restrictions.isNull(${elemInfo.info.fieldName?upper_case}));
        }

        if (is${elemInfo.info.fieldName?cap_first}IsNotNull()) {
            criteria.add(Restrictions.isNotNull(${elemInfo.info.fieldName?upper_case}));
        }
    </#if>
  </#if>
</#list>
<#--
 Flag de join a relaciones o hijos
-->
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isManyToMany>

        if (isJoin${elemInfo.info.fieldName?cap_first}()) {
            criteria.setFetchMode(${elemInfo.info.fieldName?upper_case}, FetchMode.JOIN);
        }
  </#if>
  <#if elemInfo.isOneToMany>

    <#if locale == "es">
        // Campo entidad hija '${elemInfo.info.fieldName}'
    <#else>
        // Children entity field '${elemInfo.info.fieldName}'
    </#if>

        if (isJoin${elemInfo.info.fieldName?cap_first}()) {
            criteria.setFetchMode(${elemInfo.info.fieldName?upper_case}, FetchMode.JOIN);
        }
  </#if>
</#list>
<#if !entity.isAbstract >

  <#if locale == "es">
        //Aplica ordenamiento sólo si corresponde. En count y searchUnique no se utiliza.
  <#else>
        //Applies ordering if corresponds. It's not used in count and searchUnique.
  </#if>
        if (useOrder) {
            applyOrder(criteria);
        }
</#if>

    }
    
    /**
  <#if locale == "es">
     * Convierte el bean query a String, en representación tipo árbol
  <#else>
     * Converts bean query to a tree representation
  </#if>
     */
    public String toString() {
        return new BeanFormatter().format(this);
    }
}
