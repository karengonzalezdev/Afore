<#--
 Plantilla para la generacion de la suite para los test de los Repository
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

package ${params.basePackage};

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

<#list models as item>
  <#assign isAbstract = false />
  <#list item.model.getModifiers() as modifier>
    <#if modifier == "abstract" >
      <#assign isAbstract = true />
    </#if>
  </#list>
  <#if !isAbstract >
    <#assign entitypackage = fmkUtilities("javaPackage", item.filename) />
    <#if entitypackage != params.basePackage >
      <#assign entityclassname = fmkUtilities("javaClass", item.filename) />
      <#assign testDAOpackage = fmkUtilities("javaFatherPackage", entitypackage) + ".repository" />
      <#-- se esta asumiendo el sufijo DTO. En este generador no se obliga a ello -->
      <#--<#assign testDAOClassName = fmkUtilities("rightCut", entityclassname, 3) + "RepositoryTest" />-->
      <#if params.entitySuffix?? >
		<#assign testDAOClassName = fmkUtilities("rightCut", entityclassname, params.entitySuffix?length) + "RepositoryTest" />
	  <#else>
	  	<#assign testDAOClassName = entityclassname + "RepositoryTest" />	
	  </#if>
      
import ${testDAOpackage}.${testDAOClassName};
    </#if>  
  </#if>
</#list>

/**
 * Test suite for all generated Repository tests
 */
@RunWith(Suite.class)
@SuiteClasses({
<#assign sep = "" />
<#list models as item>
  <#assign isAbstract = false />
  <#list item.model.getModifiers() as modifier>
    <#if modifier == "abstract" >
      <#assign isAbstract = true />
    </#if>
  </#list>
  <#if !isAbstract >
    <#assign entityclassname = fmkUtilities("javaClass", item.filename) />
    <#-- se esta asumiendo el sufijo DTO. En este generador no se obliga a ello -->
    <#--<#assign testDAOClassName = fmkUtilities("rightCut", entityclassname, 3) + "RepositoryTest" />-->
    <#if params.entitySuffix?? >
		<#assign testDAOClassName = fmkUtilities("rightCut", entityclassname, params.entitySuffix?length) + "RepositoryTest" />
	  <#else>
	  	<#assign testDAOClassName = entityclassname + "RepositoryTest" />	
	  </#if>
        ${sep}${testDAOClassName}.class
    <#assign sep = ", " />
  </#if>
</#list>
})

<#if params.testSuiteClassName?? >
<#-- the class name parameter is present. Use it as testSuitteClassName value -->
	<#assign testSuiteClassName = params.testSuiteClassName />
<#else>
	<#-- the class name parameter is not present. Use the default name-->	
	<#assign testSuiteClassName = "RepositoryTestSuite" />
</#if>
public class ${testSuiteClassName} {
        
}