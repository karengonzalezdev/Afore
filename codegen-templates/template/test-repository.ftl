<#--
 Plantilla para la generacion de los test JUnit de los Repository
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
<#--base package es [base].repository-->
<#assign basepackage = fmkUtilities("javaPackage", destination.filename) />
<#assign entitypackage = fmkUtilities("javaFatherPackage", basepackage) + ".model" />
<#assign testclassname = fmkUtilities("javaClass", destination.filename) />
<#--Quita el "Test"-->
<#assign daoclassname = fmkUtilities("rightCut", testclassname, 4) />
<#assign daoname = daoclassname?uncap_first />
<#--Quita el "RepositoryTest"-->
<#--<#assign entityclassname = fmkUtilities("rightCut", testclassname, 14) + "DTO" />-->
<#assign entityclassname = fmkUtilities("rightCut", testclassname, 14) />

<#if params.entitySuffix?? >
	<#assign entityclassname =  entityclassname + params.entitySuffix />
</#if>

<#assign entityqueryclassname = entityclassname + "Query" />
<#--Clase de la entidad sin generics, para los import y constantes-->
<#assign entityqueryclass = entityqueryclassname />
<#assign superClassTestName = "AbstractConfiguredEntityRepositoryTest" />
<#assign superClassTest = superClassTestName + "<" + entityclassname + ",Serializable," + entityqueryclassname + ">"/>
<#assign classPrefix = "" />
<#assign classSuffix = "" />
<#if entity.isAbstract >
  <#assign classPrefix = "abstract " />
  <#assign entityqueryclassname = entityqueryclassname + "<E>" />
  <#assign classSuffix = "<E extends " + entityclassname + ",Q extends " + entityqueryclassname + ">" />
  <#assign superClassTest = superClassTestName + "<E,Serializable,Q>" />
</#if>
<#if entity.isSubclass >
  <#assign superClassTest = fmkUtilities("classNameFromPackage", model.getSuperClass().getValue()) />
  <#--<#assign superClassQuery = fmkUtilities("rightCut", superClassTest, 3) + "DTOQuery" />-->
  <#assign superClassQuery = superClassTest + "Query" />
  <#--<#assign superClassTest = fmkUtilities("rightCut", superClassTest, 3) + "RepositoryTest<" + entityclassname + "," + entityqueryclassname + ">" />-->
  <#assign superClassTest = superClassTest + "RepositoryTest<" + entityclassname + "," + entityqueryclassname + ">" />
</#if>
package ${fmkUtilities("javaPackage", destination.filename)};

<#if !entity.isSubclass || entity.isAbstract >
import java.io.Serializable;
</#if>
import java.util.Set;
<#if entity.isSubclass || !entity.isAbstract >

import javax.annotation.Resource;
</#if>

import javax.sql.DataSource;
import org.junit.Test;
import com.jeveris.core.persistence.extractor.MetadataExtractor;

<#if !entity.isSubclass >
import ${params.basePackage}.junit.${superClassTestName};
</#if>
import ${common_corePackage}.persistence.entity.OrderType;
<#if entity.hasStrings >
import ${common_corePackage}.persistence.entity.TextComparator;
</#if>
<#if !entity.isAbstract >
import ${common_corePackage}.persistence.entity.SearchResult;
</#if>
import ${entitypackage}.${entityclassname};
import ${entitypackage}.${entityqueryclass};
<#if entity.isSubclass >
import ${entitypackage}.${superClassQuery};
</#if>

<#--Import de los campos padre-->
<#assign autImports = "" >
<#list model.getFields() as field >
  <#assign type = field.getType().getValue() >
  <#if !type?starts_with(basepackage) >
    <#assign info = fieldInfo(field) />
    <#if info.isCriterionUsable>
      <#if info.fieldIsEnum>
import ${type};
      </#if>
      <#if type != "java.util.Collection" && type != "java.util.List" && type != "java.util.Set" >
        <#if type?index_of(".") &gt; 0 && autImports?index_of(type) &lt; 0 >        
        <#--no obligar a utilizar la terminación DTO en las clases de entidad 
            Usar en su lugar el entityPackage-->        
          <#--<#if type?ends_with("DTO") >-->
          <#if type?starts_with(entitypackage) >
import ${type};
import ${type}Query;        
          </#if>
          <#assign autImports = autImports + "|" + type >
        </#if>    
      </#if>
    </#if>
  </#if>
</#list>

/**
 * Test JUnit para el Repository
 */
public ${classPrefix}class ${testclassname}${classSuffix} extends ${superClassTest} {

<#if !entity.isAbstract >
    @Resource
    protected ${daoclassname} ${daoname};
</#if>
    
    @Override
    protected String getTableName() {
<#if entity.isSubclass >
        return super.getTableName();
<#else>
        return "${entity.tableName}";
</#if>
    }

    @Override
    protected String getEntityName() {
        return "${entityclassname}";
    }
    
    /**
     * Test that the bean persistent fields (@Column) are coherent with the database table columns
     */
    public void addColumnNames(Set<String> beanColumnNames) {
<#if entity.isSubclass >
        super.addColumnNames(beanColumnNames);
</#if>
<#--Agregando el campo discriminador-->
<#if entity.isAbstract >
        beanColumnNames.add("${entity.discColumnName?upper_case}");
</#if>
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent>
    <#if elemInfo.isComposite>
      <#assign compInfo = compositeInfo(element) />
      <#list compInfo.annotationCompFields as annotationCompField>
        <#assign compField = compositeField(annotationCompField, elemInfo.info.fieldJavaType, compInfo.annotationRefAttOvers) />
        beanColumnNames.add("${compField.compositeColumnName?upper_case}");
      </#list>
    <#else>
        beanColumnNames.add("${elemInfo.columnName?upper_case}");
    </#if>
  </#if>
</#list>
    }

    /**
     * Agrega los ordenamientos
     */
    protected void addOrders(${entityqueryclassname} query) {
<#if entity.isSubclass >
        super.addOrders((${superClassQuery}<${entityclassname}>)query);
</#if>
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.info.isCriterionUsable  &&
       !elemInfo.isVersion >
        query.addOrder(${entityqueryclass}.${elemInfo.info.fieldName?upper_case}, (Math.random() < 0.5) ? OrderType.ASC : OrderType.DESC);
  </#if>
</#list>
    }
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.isManyToOne>

    /**
     * Test search join to parent '${elemInfo.info.fieldName}' without criteria.
     */
    @Test
    public void testSearchJoin${elemInfo.info.fieldName?cap_first}() {
        joinSearch(${entityqueryclass}.${elemInfo.info.fieldName?upper_case});
    }
    
    /**
     * Test search join to parent '${elemInfo.info.fieldName}' with pagination.
     */
    @Test
    public void testSearchJoinPaginated${elemInfo.info.fieldName?cap_first}() {
        joinSearchPaginated(${entityqueryclass}.${elemInfo.info.fieldName?upper_case});
    }
  </#if>
</#list>

    /**
     * Hace join con todos los padres
     */
    protected void addAllJoins(${entityqueryclassname} query) {
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.isManyToOne>
        query.setInnerJoin${elemInfo.info.fieldName?cap_first}(true);
  </#if>
</#list>
    }

    protected void addJoin(${entityqueryclassname} query, String parentName) {
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.isManyToOne>
        if (parentName.equals(${entityqueryclass}.${elemInfo.info.fieldName?upper_case})) {
            query.setInnerJoin${elemInfo.info.fieldName?cap_first}(true);
        }
  </#if>
</#list>
    }
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.isManyToOne>

    /**
     * Test buscar utilizando criterios del padre '${elemInfo.info.fieldName}'
     */
    @Test
    public void testSearchForParent${elemInfo.info.fieldName?cap_first}() {
        searchForParent(${entityqueryclass}.${elemInfo.info.fieldName?upper_case});
    }
  </#if>
</#list>

    /**
     * Agrega criterios del padre dado su nombre
     * @param String parentName nombre del campo padre
     */    
    protected void addParentCriteria(${entityqueryclassname} query, String parentName) {
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.isManyToOne>
        if (parentName.equals(${entityqueryclass}.${elemInfo.info.fieldName?upper_case})) {
            addParentCriteria${elemInfo.info.fieldName?cap_first}(query);
        }
  </#if>
</#list>
    }
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.isManyToOne>
    <#assign parInfo = parentInfo(element) />

    /**
     * Agrega criterios del padre '${elemInfo.info.fieldName}'
     */    
    private void addParentCriteria${elemInfo.info.fieldName?cap_first}(${entityqueryclassname} query) {
        ${elemInfo.info.fieldType}Query ${elemInfo.info.fieldName} = new ${elemInfo.info.fieldType}Query();
    <#list parInfo.parentModelElements as parentElement>
      <#assign parentElemInfo = elementInfo(parentElement) />
      <#-- No se considera el identificador, porque ya está en la condición para la FK -->
      <#if !parentElemInfo.isIdentifier && !parentElemInfo.isVersion>
        <#if parentElemInfo.info.fieldType == "Date"> 
        ${elemInfo.info.fieldName}.set${parentElemInfo.info.fieldName?cap_first}Min(new java.util.Date());
        ${elemInfo.info.fieldName}.set${parentElemInfo.info.fieldName?cap_first}Max(new java.util.Date());
        <#elseif parentElemInfo.info.fieldType == "String">
        ${elemInfo.info.fieldName}.set${parentElemInfo.info.fieldName?cap_first}("a");
        <#elseif parentElemInfo.info.fieldType == "Long">
        ${elemInfo.info.fieldName}.set${parentElemInfo.info.fieldName?cap_first}(Long.valueOf(5));
        <#elseif parentElemInfo.info.fieldType == "Integer">
        ${elemInfo.info.fieldName}.set${parentElemInfo.info.fieldName?cap_first}(Integer.valueOf(5));
        </#if>
      </#if>
    </#list>
        query.set${elemInfo.info.fieldName?cap_first}(${elemInfo.info.fieldName});
    }
  </#if>
</#list>

    /**
     * Agrega criterios aleatorios
     */
    protected void addRandomCriteria(${entityqueryclassname} query) {
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.info.isCriterionUsable  &&
       !elemInfo.isVersion>
    <#if elemInfo.info.fieldType == "Date"> 
        query.set${elemInfo.info.fieldName?cap_first}Min((Math.random() < 0.8) ? new java.util.Date() : null);
        query.set${elemInfo.info.fieldName?cap_first}Max((Math.random() < 0.8) ? new java.util.Date() : null);
    <#elseif elemInfo.info.fieldType == "String">
        query.set${elemInfo.info.fieldName?cap_first}((Math.random() < 0.8) ? "a" : null);
    <#elseif elemInfo.info.fieldType == "Long">
        query.set${elemInfo.info.fieldName?cap_first}((Math.random() < 0.8) ? Long.valueOf(5) : null);
    <#elseif elemInfo.info.fieldType == "Integer">
        query.set${elemInfo.info.fieldName?cap_first}((Math.random() < 0.8) ? Integer.valueOf(5) : null);
    </#if>
    <#if elemInfo.info.fieldType == "String">
      <#if elemInfo.searchTypes == "" || 
             (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.LIKE")) >
        if (Math.random() < 0.5) {
            query.set${elemInfo.info.fieldName?cap_first}Comparator(TextComparator.STARTS_WITH);
        }
      </#if>
    </#if>
    <#if elemInfo.info.fieldTypeWrapper == "Integer" ||
         elemInfo.info.fieldTypeWrapper == "Long" ||
         elemInfo.info.fieldType == "String" ||
         elemInfo.info.fieldIsEnum>
      <#if elemInfo.searchTypes == "" || 
           (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.IN")) >
        if (Math.random() < 0.3) {
        <#if elemInfo.info.fieldType == "String">
          <#list ["a","b","c"] as value>
            query.add${elemInfo.info.fieldName?cap_first}In("${value}");
          </#list>
        <#elseif elemInfo.info.fieldType == "Long">
          <#list [1,2,3] as value>
            query.add${elemInfo.info.fieldName?cap_first}In(Long.valueOf(${value}));
          </#list>
        <#elseif elemInfo.info.fieldType == "Integer">
          <#list [1,2,3] as value>
            query.add${elemInfo.info.fieldName?cap_first}In(Integer.valueOf(${value}));
          </#list>
        <#elseif elemInfo.info.fieldIsEnum>
          <#list [0,1,2] as value>
            if (${elemInfo.info.fieldType}.values().length > ${value}) {
                query.add${elemInfo.info.fieldName?cap_first}In(${elemInfo.info.fieldType}.values()[${value}]);
            }
          </#list>
        </#if>
        }
      </#if>
    </#if>
    <#if elemInfo.isManyToOne >
        if (Math.random() < 0.3) {
      <#list [1,2,3] as value>
            ${elemInfo.info.fieldType} ${elemInfo.info.fieldName}${value} = new ${elemInfo.info.fieldType}();
        <#if elemInfo.info.parentIdType == "String">
            ${elemInfo.info.fieldName}${value}.set${elemInfo.info.parentIdName?cap_first}("${value}");
        <#elseif elemInfo.info.parentIdType == "Long">
            ${elemInfo.info.fieldName}${value}.set${elemInfo.info.parentIdName?cap_first}(Long.valueOf(${value}));
        <#elseif elemInfo.info.parentIdType == "Integer">
            ${elemInfo.info.fieldName}${value}.set${elemInfo.info.parentIdName?cap_first}(Integer.valueOf(${value}));
        </#if>
            query.add${elemInfo.info.fieldName?cap_first}IdIn(${elemInfo.info.fieldName}${value});
      </#list>
        }
    </#if>
    <#if !elemInfo.isIdentifier >
        if (Math.random() < 0.3) {
            query.set${elemInfo.info.fieldName?cap_first}IsNull(true);
        }
        if (Math.random() < 0.3) {
            query.set${elemInfo.info.fieldName?cap_first}IsNotNull(true);
        }
    </#if>
  </#if>
</#list>
    }

    /**
     * Agrega criterios en todos los campos. Sólo sirve para validar sintaxis SQL.
     */
    protected void addAllCriteria(${entityqueryclassname} query) {
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.info.isCriterionUsable  &&
       !elemInfo.isVersion>
    <#if elemInfo.info.fieldType == "Date"> 
        query.set${elemInfo.info.fieldName?cap_first}Min(new java.util.Date());
        query.set${elemInfo.info.fieldName?cap_first}Max(new java.util.Date());
    <#elseif elemInfo.info.fieldType == "String">
        query.set${elemInfo.info.fieldName?cap_first}("a");
    <#elseif elemInfo.info.fieldType == "Long">
        query.set${elemInfo.info.fieldName?cap_first}(Long.valueOf(5));
    <#elseif elemInfo.info.fieldType == "Integer">
        query.set${elemInfo.info.fieldName?cap_first}(Integer.valueOf(5));
    </#if>
    <#if elemInfo.info.fieldType == "String">
      <#if elemInfo.searchTypes == "" || 
             (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.LIKE")) >
        query.set${elemInfo.info.fieldName?cap_first}Comparator(TextComparator.STARTS_WITH);
      </#if>
    </#if>
    <#if elemInfo.info.fieldTypeWrapper == "Integer" ||
         elemInfo.info.fieldTypeWrapper == "Long" ||
         elemInfo.info.fieldType == "String" ||
         elemInfo.info.fieldIsEnum>
      <#if elemInfo.searchTypes == "" || 
           (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.IN")) >
        <#if elemInfo.info.fieldType == "String">
          <#list ["a","b","c"] as value>
        query.add${elemInfo.info.fieldName?cap_first}In("${value}");
          </#list>
        <#elseif elemInfo.info.fieldType == "Long">
          <#list [1,2,3] as value>
        query.add${elemInfo.info.fieldName?cap_first}In(Long.valueOf(${value}));
          </#list>
        <#elseif elemInfo.info.fieldType == "Integer">
          <#list [1,2,3] as value>
        query.add${elemInfo.info.fieldName?cap_first}In(Integer.valueOf(${value}));
          </#list>
        <#elseif elemInfo.info.fieldIsEnum>
          <#list [0,1,2] as value>
        if (${elemInfo.info.fieldType}.values().length > ${value}) {
            query.add${elemInfo.info.fieldName?cap_first}In(${elemInfo.info.fieldType}.values()[${value}]);
        }
          </#list>
        </#if>
      </#if>
    </#if>
    <#if elemInfo.isManyToOne >
      <#list [1,2,3] as value>
        ${elemInfo.info.fieldType} ${elemInfo.info.fieldName}${value} = new ${elemInfo.info.fieldType}();
        <#if elemInfo.info.parentIdType == "String">
        ${elemInfo.info.fieldName}${value}.set${elemInfo.info.parentIdName?cap_first}("${value}");
        <#elseif elemInfo.info.parentIdType == "Long">
        ${elemInfo.info.fieldName}${value}.set${elemInfo.info.parentIdName?cap_first}(Long.valueOf(${value}));
        <#elseif elemInfo.info.parentIdType == "Integer">
        ${elemInfo.info.fieldName}${value}.set${elemInfo.info.parentIdName?cap_first}(Integer.valueOf(${value}));
        </#if>
        query.add${elemInfo.info.fieldName?cap_first}IdIn(${elemInfo.info.fieldName}${value});
      </#list>
    </#if>
    <#if !elemInfo.isIdentifier >
        query.set${elemInfo.info.fieldName?cap_first}IsNull(true);
        query.set${elemInfo.info.fieldName?cap_first}IsNotNull(true);
    </#if>
  </#if>
</#list>
    }
<#if !entity.isAbstract >

    /**
     * Crea el objeto de criterios de búsqueda
     */
    protected ${entityqueryclassname} createQuery() {
        return new ${entityqueryclassname}();
    }

    /**
     * Realiza la búsqueda del total count
     */
    protected int doTotalCount() {
        ${entityqueryclassname} query = createQuery();
        return ${daoname}.count(query);
    }
    
    /**
     * Realiza la búsqueda con el Repository específico de la subclase
     */
    protected SearchResult<${entityclassname}> doSearch(${entityqueryclassname} query) {
        return ${daoname}.search(query);
    }
</#if>

	/**
	 * Database metadata extractor, to compare entity respect to table
	 */
	@Resource(name="metadataExtractor${params.module?cap_first}")
	private MetadataExtractor metadataExtractor;
	
	public MetadataExtractor getMetadataExtractor() {
		return metadataExtractor;
	}
	
	/**
	 * override the autowired datasource. Needed when more than one are 
	 * defined in the application
	 */
	@Override
	@Resource(name="dataSource${params.module?cap_first}")
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
}
