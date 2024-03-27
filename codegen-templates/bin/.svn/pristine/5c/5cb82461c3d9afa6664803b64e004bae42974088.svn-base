<#--
 Plantilla para la generación del objeto de criterios de búsqueda con Criteria API de JPA 2.0
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
<#assign entityclassname = fmkUtilities("rightCut", classname, 5) />
<#if !entity.isSubclass>
  <#assign superClass = "AbstractJPAQueryEntity<" + entityclassname + ">" />
</#if>
<#if entity.isAbstract >
  <#assign classname = classname + "<T extends " + entityclassname + ">" />
  <#assign superClass = "AbstractJPAQueryEntity<T>" />
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
<#if entity.hasParents>
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
</#if>
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
<#if entity.hasDates>
import ${common_corePackage}.utils.DateUtil;
</#if>
import ${common_corePackage}.utils.BeanFormatter;
<#if entity.hasStrings>
import ${common_corePackage}.persistence.entity.TextComparator;
</#if>
import ${common_corePackage}.persistence.exception.PersistenceException;
<#if !entity.isSubclass>
import ${common_persistence_jpaPackage}.entity.AbstractJPAQueryEntity;
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
     * Agrega recursivamente criterios a los objetos de Criteria API de JPA 2.0 
     * para la utilización en búsquedas
<#else>
     * Adds recursively criteria to JPA 2.0 Criteria API objects for using in search
</#if>
     */ 
<#if entity.isAbstract>
    public void addCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, 
            From<?,T> root, List<Predicate> predicateList, boolean useFetch, boolean useOrder) {
<#else>
    public void addCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, 
            From<?,${entityclassname}> root, List<Predicate> predicateList, boolean useFetch, boolean useOrder) {
</#if>
        boolean createPredicate = predicateList == null;
<#if entity.isSubclass>

        super.addCriteria(criteriaBuilder, criteriaQuery, root, predicateList, fetch);
</#if>

        if (createPredicate) {
            predicateList = new ArrayList<Predicate>();
        }
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
            Predicate condition = criteriaBuilder.greaterThanOrEqualTo(root.get(${entityclassname}_.${elemInfo.info.fieldName}), get${elemInfo.info.fieldName?cap_first}Min());
            predicateList.add(condition);
        }

        if (get${elemInfo.info.fieldName?cap_first}Max() != null) {
            Predicate condition = criteriaBuilder.lessThanOrEqualTo(root.get(${entityclassname}_.${elemInfo.info.fieldName}), get${elemInfo.info.fieldName?cap_first}Max());
            predicateList.add(condition);
        }
      <#else>

        if (get${elemInfo.info.fieldName?cap_first}() != null) {
            Expression<${elemInfo.info.fieldTypeWrapper}> path = root.get(${entityclassname}_.${elemInfo.info.fieldName});
        <#if elemInfo.info.fieldType == "String">
            String criterionField = get${elemInfo.info.fieldName?cap_first}();
          <#if elemInfo.searchIgnoreCase == "true">
            if (is${elemInfo.info.fieldName?cap_first}IgnoreCase()) {
                path = criteriaBuilder.lower(path);
                criterionField = criterionField.toLowerCase();
            }
          </#if>        
          <#if elemInfo.searchTypes == "" || 
               (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.LIKE") && elemInfo.searchTypes?contains("SearchType.EQ")) >
            Predicate condition = null;
            if (get${elemInfo.info.fieldName?cap_first}Comparator() == TextComparator.EQUALS) {
                condition = criteriaBuilder.equal(path, criterionField);
            } else {
                condition = criteriaBuilder.like(path, getMatchedMode(criterionField, 
                        get${elemInfo.info.fieldName?cap_first}Comparator()));
            }
          <#elseif elemInfo.searchTypes?contains("SearchType.LIKE") && !elemInfo.searchTypes?contains("SearchType.EQ") >
            Predicate condition = criteriaBuilder.like(path, getMatchedMode(criterionField, 
                        get${elemInfo.info.fieldName?cap_first}Comparator()));
          <#else>
            Predicate condition = criteriaBuilder.equal(path, criterionField);
          </#if>        
        <#else>
            Predicate condition = criteriaBuilder.equal(path, get${elemInfo.info.fieldName?cap_first}());
        </#if>        
            predicateList.add(condition);
        }
        <#if elemInfo.info.fieldTypeWrapper == "Integer" ||
             elemInfo.info.fieldTypeWrapper == "Long" ||
             elemInfo.info.fieldType == "String" ||
             elemInfo.info.fieldIsEnum>
          <#if elemInfo.searchTypes == "" || 
               (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.IN")) >

        if (get${elemInfo.info.fieldName?cap_first}In().size() > 0) {
            CriteriaBuilder.In<${elemInfo.info.fieldTypeWrapper}> condition = criteriaBuilder.in(root.get(${entityclassname}_.${elemInfo.info.fieldName}));
            for (${elemInfo.info.fieldType} ${elemInfo.info.fieldName} : get${elemInfo.info.fieldName?cap_first}In()) {
                condition = condition.value(${elemInfo.info.fieldName});
            }
            predicateList.add(condition);
        }

          </#if>
        </#if>
        <#if elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.RANGE") >

        if (get${elemInfo.info.fieldName?cap_first}Min() != null) {
            Predicate condition = criteriaBuilder.ge(root.get(${entityclassname}_.${elemInfo.info.fieldName}), get${elemInfo.info.fieldName?cap_first}Min());
            predicateList.add(condition);
        }

        if (get${elemInfo.info.fieldName?cap_first}Max() != null) {
            Predicate condition = criteriaBuilder.le(root.get(${entityclassname}_.${elemInfo.info.fieldName}), get${elemInfo.info.fieldName?cap_first}Max());
            predicateList.add(condition);
        }
        </#if>
      </#if>
    <#else>

      <#if locale == "es">
        // Campo entidad padre '${elemInfo.info.fieldName}'
      <#else>
        // Parent entity field '${elemInfo.info.fieldName}'
      </#if>
        if (get${elemInfo.info.fieldName?cap_first}() != null) {
            if (get${elemInfo.info.fieldName?cap_first}().get${elemInfo.info.parentIdName?cap_first}() == null) {
                Join<${entityclassname}, ${elemInfo.info.fieldType}> ${elemInfo.info.fieldName}Join = root.join(${entityclassname}_.${elemInfo.info.fieldName}, JoinType.INNER);
                get${elemInfo.info.fieldName?cap_first}().addCriteria(criteriaBuilder, criteriaQuery, ${elemInfo.info.fieldName}Join, predicateList, false, useOrder);
            } else {
                ${elemInfo.info.fieldType} parent = new ${elemInfo.info.fieldType}();
                parent.set${elemInfo.info.parentIdName?cap_first}(get${elemInfo.info.fieldName?cap_first}().get${elemInfo.info.parentIdName?cap_first}());
                Path<${elemInfo.info.fieldType}> parentPath = root.get(${entityclassname}_.${elemInfo.info.fieldName});
                Predicate parentCondition = criteriaBuilder.equal(parentPath, parent);
                predicateList.add(parentCondition);
            }
        }

        if (get${elemInfo.info.fieldName?cap_first}IdIn().size() > 0) {
            CriteriaBuilder.In<${elemInfo.info.fieldTypeWrapper}> condition = criteriaBuilder.in(root.get(${entityclassname}_.${elemInfo.info.fieldName}));
            for (${elemInfo.info.fieldType} ${elemInfo.info.fieldName} : get${elemInfo.info.fieldName?cap_first}IdIn()) {
                condition = condition.value(${elemInfo.info.fieldName});
            }
            predicateList.add(condition);
        }
    </#if>
    <#if !elemInfo.isIdentifier>

        if (is${elemInfo.info.fieldName?cap_first}IsNull()) {
            Predicate condition = criteriaBuilder.isNull(root.get(${entityclassname}_.${elemInfo.info.fieldName}));
            predicateList.add(condition);
        }

        if (is${elemInfo.info.fieldName?cap_first}IsNotNull()) {
            Predicate condition = criteriaBuilder.isNotNull(root.get(${entityclassname}_.${elemInfo.info.fieldName}));
            predicateList.add(condition);
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
//            criteria.setFetchMode(${elemInfo.info.fieldName?upper_case}, FetchMode.JOIN);
        }
  </#if>
  <#if elemInfo.isOneToMany>

    <#if locale == "es">
        // Campo entidad hija '${elemInfo.info.fieldName}'
    <#else>
        // Children entity field '${elemInfo.info.fieldName}'
    </#if>

        if (isJoin${elemInfo.info.fieldName?cap_first}()) {
//            criteria.setFetchMode(${elemInfo.info.fieldName?upper_case}, FetchMode.JOIN);
        }
  </#if>
</#list>

        addFetch(root, useFetch);

        //Agrega todas las condiciones aplicadas
        if (createPredicate && predicateList.size() > 0) {
            criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
        }
<#if !entity.isAbstract >

  <#if locale == "es">
        //Aplica ordenamiento sólo si corresponde. En count y searchUnique no se utiliza.
  <#else>
        //Applies ordering if corresponds. It's not used in count and searchUnique.
  </#if>
        if (useOrder) {
            applyOrder(criteriaBuilder, criteriaQuery, root);
        }
</#if>
    }
    
<#if entity.isAbstract>
    public void addFetch(FetchParent<?,T> fetch, boolean fetch) {
<#else>
    public void addFetch(FetchParent<?,${entityclassname}> fetch, boolean useFetch) {
</#if>
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.info.isCriterionUsable  &&
       elemInfo.isManyToOne>

        if (useFetch && isInnerJoin${elemInfo.info.fieldName?cap_first}()) {
            if (hasSelectFields()) {
                throw new PersistenceException("cannot_make_inner_join_selected_fields");
            }
            Fetch<${entityclassname}, ${elemInfo.info.fieldType}> ${elemInfo.info.fieldName}fetch = fetch.fetch(${entityclassname}_.${elemInfo.info.fieldName}, JoinType.INNER);
            if (get${elemInfo.info.fieldName?cap_first}() != null) {
                get${elemInfo.info.fieldName?cap_first}().addFetch(${elemInfo.info.fieldName}fetch, useFetch);
            }
        } else if (useFetch && isLeftJoin${elemInfo.info.fieldName?cap_first}()) {
            if (hasSelectFields()) {
                throw new PersistenceException("cannot_make_left_join_selected_fields");
            }
            Fetch<${entityclassname}, ${elemInfo.info.fieldType}> ${elemInfo.info.fieldName}fetch = fetch.fetch(${entityclassname}_.${elemInfo.info.fieldName}, JoinType.LEFT);
            if (get${elemInfo.info.fieldName?cap_first}() != null) {
                get${elemInfo.info.fieldName?cap_first}().addFetch(${elemInfo.info.fieldName}fetch, useFetch);
            }
        }
  </#if>
</#list>
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
