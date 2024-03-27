<#--
 Imports en DTOQuery que son comunes a todos los ORM (no iBatis)
-->
<#macro queryImports package >
<#assign autImports = "" >
<#list model.getFields() as field >
  <#assign type = field.getType().getValue() >
  <#if !type?starts_with(package) >
    <#assign info = fieldInfo(field) />
    <#if info.isCriterionUsable>
      <#if type != "java.util.Collection" && type != "java.util.List" && type != "java.util.Set" >
        <#if type?index_of(".") &gt; 0 && autImports?index_of(type) &lt; 0 >
import ${type};
          <#if type?ends_with("DTO") >
import ${type}Query;
          </#if>
          <#assign autImports = autImports + "|" + type >
        </#if>    
      </#if>
    </#if>
  </#if>
</#list>
</#macro>
<#--
 Imports en DAOImpl que son comunes a todos los ORM (no iBatis)
-->
<#macro daoImplImports package >
<#assign autImports = "" >
<#list model.getFields() as field >
  <#assign type = field.getType().getValue() >
  <#if !type?starts_with(package) >
    <#assign info = fieldInfo(field) />
    <#if info.isCriterionUsable>
      <#if type != "java.util.Collection" && type != "java.util.List" && type != "java.util.Set" >
        <#if type?index_of(".") &gt; 0 && autImports?index_of(type) &lt; 0 >
          <#if type?ends_with("DTO") >
import ${type};        
          </#if>
          <#assign autImports = autImports + "|" + type >
        </#if>    
      </#if>
    </#if>
  </#if>
</#list>
</#macro>
