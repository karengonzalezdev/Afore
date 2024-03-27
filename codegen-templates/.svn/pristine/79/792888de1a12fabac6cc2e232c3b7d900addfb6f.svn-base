<#--
 Imports en clases que son wrapper de servicios
-->
<#macro serviceImports basePackage >
<#assign autImports = "" >
<#list model.getMethods() as method>
  <#if method.isPublic()>
    <#list method.getParameters() as param>
      <#assign type = param.getType().getValue() >
      <#if !type?starts_with(basePackage) && !type?starts_with("java.lang") && type?index_of(".") &gt; 0 && autImports?index_of(type) &lt; 0 >
import ${type};
        <#assign autImports = autImports + "|" + type >
      </#if>
      <#assign subFullType = genericType(param.getType()) />
      <#if subFullType != "" && !subFullType?starts_with(basePackage) && !subFullType?starts_with("java.lang") 
          && subFullType?index_of(".") &gt; 0 && autImports?index_of(subFullType) &lt; 0 >
import ${subFullType};
        <#assign autImports = autImports + "|" + subFullType >
      </#if>
    </#list>
    <#list method.getExceptions() as exception>
      <#assign type = exception.getType().getValue() >
      <#if !type?starts_with(basePackage) && !type?starts_with("java.lang") && type?index_of(".") &gt; 0 && autImports?index_of(type) &lt; 0 >
import ${type};
        <#assign autImports = autImports + "|" + type >
      </#if>
    </#list>
    <#if method.getReturns().getValue() != "void">
      <#assign type = method.getReturns().getValue() >
      <#if !type?starts_with(basePackage) && !type?starts_with("java.lang") && type?index_of(".") &gt; 0 && autImports?index_of(type) &lt; 0 >
import ${type};
        <#assign autImports = autImports + "|" + type >
      </#if>
    </#if>
    <#assign subFullType = genericType(method.getReturns()) />
    <#if subFullType != "" && !subFullType?starts_with(basePackage) && !subFullType?starts_with("java.lang") 
        && subFullType?index_of(".") &gt; 0 && autImports?index_of(subFullType) &lt; 0 >
import ${subFullType};
      <#assign autImports = autImports + "|" + subFullType >
    </#if>
  </#if>
</#list>
</#macro>
<#--
 Functions to manage arrays and generics associated to a type
-->
<#function getFullType arg_type>
  <#assign fullType = fmkUtilities("classNameFromPackage", arg_type.getValue()) />
  <#if arg_type.isArray()>
    <#assign fullType = fullType + "[]" />
  </#if>
  <#assign subFullType = genericType(arg_type) />
  <#if subFullType != "">
    <#assign subType = fmkUtilities("classNameFromPackage", subFullType) />
    <#assign fullType = fullType + "<" + subType + ">" />
  </#if>
  <#return fullType />
</#function>
<#function genericType arg_type>
  <#assign subFullType = "" />
  <#if arg_type.getGenericValue() != "" && arg_type.getGenericValue()?index_of('<') != -1>
    <#assign gen = arg_type.getGenericValue() />
    <#assign beginIdx = gen?index_of('<') + 1 />
    <#assign endIdx = gen?index_of('>', beginIdx) />
    <#assign subFullType = gen?substring(beginIdx, endIdx) />
  </#if>
  <#return subFullType />
</#function>
