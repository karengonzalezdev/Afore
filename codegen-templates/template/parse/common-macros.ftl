<#--
 Commonly used functions
 To improve maintenance, the following code conventions are used:
 arg_ : macro argument
 return type: macros return "hash" objects (name of maps used by freeMarker)
 _variable: local variables starts with underscore
-->
<#assign common_corePackage="com.jeveris.core" />
<#assign common_persistence_hibernatePackage="com.jeveris.persistence.hibernate" />
<#assign common_persistence_jpaPackage="com.jeveris.persistence.jpa" />
<#-- ##########################################################################
 Extracts general data of an entity
 Arguments:
   arg_entity: QDox JavaClass of entity, equivalent to $model in template
 Returns: object with the following fields:
   isAbstract: Si la entidad es abstracta o tiene discriminador. Es extendida por otras entidades.
   isSubclass: Si la entidad es subclase. Su identificador está en la superclase.
   isReadOnly: Si la entidad es de sólo lectura. Se usa para vistas y entidades externas.
   tableName: Nombre de la tabla asociada a la la entidad
   superClass: Si es subclase, tipo (QDox Type) de la superclase.
   superIdFullClassName: Si es subclase, full class name del identificador de la superclase
   superIdClassName: Si es subclase, simple class name del identificador de la superclase
   superIdName: Si es subclase, nombre del identificador de la superclase
   discColumnName: Si es abstracta, columna de base de datos del discriminador
   superDiscColumnName: Si es subclase, columna de base de datos del discriminador de la superclase
   hasEmbeddedId: Si la entidad tiene clave primaria compuesta
   idFullClassName: Full class name de la clave primaria
   idClassName: Simple class name de la clave primaria 
   idName: Nombre del campo identificador
   hasVersion: Si tiene un campo de versión, para el uso de optimistic-lock
   versionName: Nombre del campos de versión, si lo tiene 
   hasParents: Si tiene campos padre, es decir, del tipo many-to-one
   hasRelations: Si tiene relaciones con otras entidades, del tipo many-to-many
   useFields: Si las anotaciones de persistencia están en los campos
   useMethods:Si las anotaciones de persistencia están en los métodos
   modelElements: Lista de elementos con las anotaciones persistentes, que pueden ser los campos o métodos
   hasDates: Indica si dentro de los campos hay alguno de tipo Date
   hasStrings: Indica si dentro de los campos hay alguno de tipo String
 -->
<#function entityInfo arg_entity>
<#assign debug = "" />
<#assign debug = "entity: " + arg_entity.getName() />
<#-- Viendo si es abstracta -->
<#assign isAbstract = false />
<#list arg_entity.getModifiers() as _modifier >
  <#if _modifier == "abstract" >
    <#assign isAbstract = true />
  </#if>
</#list>
<#-- Viendo si es readOnly -->
<#assign isReadOnly=false />
<#list arg_entity.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == common_corePackage + ".persistence.annotation.ReadOnly" >
    <#assign isReadOnly=true />
  </#if>
</#list>
<#-- Viendo si es subclass -->
<#assign isSubclass=false />
<#assign superClass="" />
<#assign discValue="" />
<#assign superIdFullClassName="" />
<#assign superIdClassName="" />
<#assign superIdName="" />
<#list arg_entity.getAnnotations() as _annotation >
  <#assign debug = debug + ", annotation: " + _annotation.getType().getValue() />
  <#if _annotation.getType().getValue() == "javax.persistence.DiscriminatorValue" >
    <#assign isSubclass=true />
    <#assign superClass=arg_entity.getSuperJavaClass() />
    <#assign discValue=annotationNamedParameter(_annotation, "value") />
    <#-- Obteniendo identificador de la superclase -->
    <#assign superClassInfo = superEntityInfo(superClass)/>
    <#assign superIdFullClassName=superClassInfo.idFullClassName />
    <#assign superIdClassName=superClassInfo.idClassName />
    <#assign superIdName=superClassInfo.idName />
    <#assign superDiscColumnName=superClassInfo.discColumnName />
  </#if>
</#list>
<#assign discColumnName = "Unknown" />
<#list arg_entity.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.DiscriminatorColumn" >
    <#assign discColumnName=annotationNamedParameter(_annotation, "name") />
  </#if>
</#list>
<#assign tableName = arg_entity.getName()?upper_case />
<#list arg_entity.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.Table" >
    <#assign tableName=annotationNamedParameter(_annotation, "name") />
  </#if>
</#list>
<#assign persistenceName = "" />
<#if !isSubclass >
  <#list arg_entity.getAnnotations() as _annotation >
    <#if _annotation.getType().getValue() == common_corePackage + ".persistence.annotation.PersistenceName" >
      <#assign persistenceName=annotationNamedParameter(_annotation, "name") />
    </#if>
  </#list>
</#if>
<#assign useMethods = false />
<#assign useFields = false />
<#assign hasEmbeddedId = false />
<#assign idFullClassName = "Unknown" />
<#assign idClassName = "Unknown" />
<#assign idName = "Unknown" />
<#assign hasParents = false />
<#assign hasRelations = false />
<#assign hasChildren = false />
<#assign hasDates = false />
<#assign hasStrings = false />
<#list arg_entity.getMethods() as _method >
  <#-- excluye constructores -->
  <#if !_method.isConstructor() >
  <#list _method.getAnnotations() as _annotation >
    <#-- Si algún método está marcado con una anotación de javax.persistence, 
         las anotaciones se buscan en los métodos -->
    <#if _annotation.getType().getValue()?starts_with("javax.persistence.") >
      <#assign useMethods = true />
    </#if>
    <#if !isSubclass >
      <#if _annotation.getType().getValue() == "javax.persistence.Id" || 
           _annotation.getType().getValue() == "javax.persistence.EmbeddedId" >
        <#assign idFullClassName = _method.getReturns().getValue() />
        <#assign idClassName = fmkUtilities("classNameFromPackage", idFullClassName) />
        <#assign idName = fmkUtilities("leftCut", _method.getName(), 3)?uncap_first />
      </#if>
      <#if _annotation.getType().getValue() == "javax.persistence.EmbeddedId" >
        <#assign hasEmbeddedId = true />
      </#if>
    </#if>
  </#list>
  </#if>
</#list>
<#list arg_entity.getFields() as _field >
  <#list _field.getAnnotations() as _annotation >
    <#-- Si algún campo está marcado con una anotación de javax.persistence, 
         las anotaciones se buscan en los campos -->
    <#if _annotation.getType().getValue()?starts_with("javax.persistence.") >
      <#assign useFields = true />
    </#if>
    <#if !isSubclass >
      <#if _annotation.getType().getValue() == "javax.persistence.Id" || 
           _annotation.getType().getValue() == "javax.persistence.EmbeddedId" >
        <#assign idFullClassName = _field.getType().getValue() />
        <#assign idClassName = fmkUtilities("classNameFromPackage", idFullClassName) />
        <#assign idName = _field.getName() />
      </#if>
      <#if _annotation.getType().getValue() == "javax.persistence.EmbeddedId" >
        <#assign hasEmbeddedId = true />
      </#if>
    </#if>
  </#list>
  <#if _field.getType().getValue() == "java.util.Date" >
    <#assign hasDates = true />
  </#if>
  <#if _field.getType().getValue() == "String" >
    <#assign hasStrings = true />
  </#if>
</#list>
<#if useMethods >
  <#assign modelElements = arg_entity.getMethods() />
<#elseif useFields >
  <#assign modelElements = arg_entity.getFields() />
<#else>
  <#assign modelElements = [] />
</#if>
<#list modelElements as element >
  <#list element.getAnnotations() as _annotation >
    <#if _annotation.getType().getValue() == "javax.persistence.JoinColumn" >
      <#assign hasParents = true />
    </#if>
    <#if _annotation.getType().getValue() == "javax.persistence.ManyToMany" >
      <#assign hasRelations = true />
    </#if>
    <#if _annotation.getType().getValue() == "javax.persistence.OneToMany" >
      <#assign hasChildren = true />
    </#if>
  </#list>
</#list>
<#assign entity={
   "debug":debug
  ,"isReadOnly":isReadOnly
  ,"isAbstract":isAbstract
  ,"superClass":superClass
  ,"tableName":tableName
  ,"discValue":discValue
  ,"discColumnName":discColumnName
  ,"superIdFullClassName":superIdFullClassName
  ,"superIdClassName":superIdClassName
  ,"superIdName":superIdName
  ,"isSubclass":isSubclass
  ,"useMethods":useMethods
  ,"useFields":useFields
  ,"hasEmbeddedId":hasEmbeddedId
  ,"idFullClassName":idFullClassName
  ,"idClassName":idClassName
  ,"idName":idName
  ,"hasDates":hasDates
  ,"hasStrings":hasStrings
  ,"modelElements":modelElements
  ,"hasParents":hasParents
  ,"hasRelations":hasRelations
  ,"hasChildren":hasChildren
  } />
<#return entity />
</#function>
<#-- ##########################################################################
 Función para extraer los datos generales de la super entidad (superclase)
 Argumentos:
   arg_entity: QDox JavaClass de la entidad, equivalente al $model en la plantilla
 Información obtenida es un objeto con los siguentes campos:
   idFullClassName: Full class name de la clave primaria
   idClassName: Simple class name de la clave primaria 
   idName: Nombre del campo identificador
   discColumnName: Si es abstracta, columna de base de datos del discriminador
 -->
<#function superEntityInfo arg_entity>
<#assign idFullClassName = "Unknown" />
<#assign idClassName = "Unknown" />
<#assign idName = "Unknown" />
<#assign discColumnName = "Unknown" />
<#list arg_entity.getMethods() as _method >
  <#-- excluye constructores -->
  <#if !_method.isConstructor() >
  <#list _method.getAnnotations() as _annotation >
    <#if _annotation.getType().getValue() == "javax.persistence.Id" || 
         _annotation.getType().getValue() == "javax.persistence.EmbeddedId" >
      <#assign idFullClassName = _method.getReturns().getValue() />
      <#assign idClassName = fmkUtilities("classNameFromPackage", idFullClassName) />
      <#assign idName = fmkUtilities("leftCut", _method.getName(), 3)?uncap_first />
    </#if>
    <#if _annotation.getType().getValue() == "javax.persistence.EmbeddedId" >
      <#assign hasEmbeddedId = true />
    </#if>
  </#list>
  </#if>
</#list>
<#list arg_entity.getFields() as _field >
  <#list _field.getAnnotations() as _annotation >
    <#if _annotation.getType().getValue() == "javax.persistence.Id" || 
         _annotation.getType().getValue() == "javax.persistence.EmbeddedId" >
      <#assign idFullClassName = _field.getType().getValue() />
      <#assign idClassName = fmkUtilities("classNameFromPackage", idFullClassName) />
      <#assign idName = _field.getName() />
    </#if>
  </#list>
</#list>
<#list arg_entity.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.DiscriminatorColumn" >
    <#assign discColumnName=annotationNamedParameter(_annotation, "name") />
  </#if>
</#list>
<#assign superEntity={
   "debug":debug
  ,"idFullClassName":idFullClassName
  ,"idClassName":idClassName
  ,"idName":idName
  ,"discColumnName":discColumnName
  } />
<#return superEntity />
</#function>
<#-- ##########################################################################
 Función para información del identificador adicional a la de entityInfo
 Argumentos:
   arg_entity: QDox JavaClass de la entidad, equivalente al $model en la plantilla
 Información obtenida:
   generatedValueType: Tipo de generación de la clave primaria
   generatedValueName: Cuando es por secuencia, nombre de la secuencia
   idName: Nombre del campo identificador
   idColumnName: Nombre de columna de base de datos del identificador
-->
<#function identifierInfo arg_entity>


</#function>
<#-- ##########################################################################
 Macro para obtener informacion de un campo correspondiente a una clave
 compuesta, ya sea primaria o foránea
 Argumentos:
   arg_elem: QDox JavaMethod o JavaField del campo persistente, obtenido de la
             iteración de common_modelElements
 Información obtenida:
   annotationCompFields: Colección genérica de campos de la clave compuesta
   annotationRefAttOvers: Colección de las anotaciones AttributeOverride 
                                 de una clave primaria. Para uso interno.
-->
<#function compositeInfo arg_elem>
<#assign annotationCompFields = [] />
<#assign annotationRefAttOvers = [] />
<#list arg_elem.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.AttributeOverrides" >
    <#assign annotationCompFields = _annotation.getNamedParameter("value") />
  <#elseif _annotation.getType().getValue() == "javax.persistence.JoinColumns" >
    <#assign annotationCompFields = _annotation.getNamedParameter("value") />
    <#-- Buscando los campos en las anotaciones de la clave primaria -->
    <#list arg_elem.getType().getJavaClass().getMethods() as _method >
      <#list _method.getAnnotations() as _methodAnnotation >
        <#if _methodAnnotation.getType().getValue() == "javax.persistence.AttributeOverrides" >
          <#assign annotationRefAttOvers = _methodAnnotation.getNamedParameter("value") />
        </#if>
      </#list>
    </#list>
    <#list arg_elem.getType().getJavaClass().getFields() as _field >
      <#list _field.getAnnotations() as _fieldAnnotation >
        <#if _fieldAnnotation.getType().getValue() == "javax.persistence.AttributeOverrides" >
          <#assign annotationRefAttOvers = _fieldAnnotation.getNamedParameter("value") />
        </#if>
      </#list>
    </#list>
  </#if>
</#list>
<#assign info={
   "annotationCompFields":annotationCompFields
  ,"annotationRefAttOvers":annotationRefAttOvers
  } />
<#return info />
</#function>
<#-- ##########################################################################
Macro que extrae la información de un campo de una clave primaria compuesta
 Argumentos:
   arg_annotationAttOver: QDox Annotation del campo compuesto de la clave primaria
       correspondiente a una anotación AttributeOverride
   arg_type: QDox Type del campo compuesto de la clave primaria
 Información obtenida:
   compositeFieldName: Nombre del campo java dentro del campo compuesto
   compositeColumnName: Nombre de la columna de base de datos del campo
   compositeFieldType: Tipo (QDox Type) del campo dentro del campo compuesto
-->
<#function compositeField arg_annotationCompField arg_type arg_annotationRefAttOvers>
<#if arg_annotationCompField.getType().getValue() == "javax.persistence.AttributeOverride" >
    <#assign compField = compositePrimaryField(arg_annotationCompField, arg_type) />
<#elseif arg_annotationCompField.getType().getValue() == "javax.persistence.JoinColumn" >
    <#assign compField = compositeForeignField(arg_annotationCompField, arg_type, arg_annotationRefAttOvers) />
</#if>
<#return compField />
</#function>
<#-- ##########################################################################
Macro que extrae la información de un campo de una clave primaria compuesta
 Argumentos:
   arg_annotationAttOver: QDox Annotation del campo compuesto de la clave primaria
       correspondiente a una anotación AttributeOverride
   arg_type: QDox Type del campo compuesto de la clave primaria
 Información obtenida:
   compositeFieldName: Nombre del campo java dentro del campo compuesto
   compositeColumnName: Nombre de la columna de base de datos del campo
   compositeFieldType: Tipo (QDox Type) del campo dentro del campo compuesto
-->
<#function compositePrimaryField arg_annotationAttOver arg_type>
<#assign compositeFieldName = annotationNamedParameter(arg_annotationAttOver, "name") />
<#assign columnAnnotation = arg_annotationAttOver.getNamedParameter("column") />
<#assign compositeColumnName = annotationNamedParameter(columnAnnotation, "name") />
<#list arg_type.getJavaClass().getFields() as _field >
  <#if _field.getName() == compositeFieldName>
    <#assign compositeFieldType = _field.getType() />
  </#if>
</#list>
<#assign compField={
   "compositeFieldName":compositeFieldName
  ,"compositeColumnName":compositeColumnName
  ,"compositeFieldType":compositeFieldType
  } />
<#return compField />
</#function>
<#-- ##########################################################################
Macro que extrae la información de un campo de una clave foranea compuesta
Los nombres de los campos java los extrae de la respectiva clave primaria
 Argumentos:
   arg_annotationJoinCol: QDox Annotation del campo compuesto de la clave foránea
       correspondiente a una anotación JoinColumn
   arg_type: QDox Type del campo compuesto de la clave primaria
 Información obtenida:
   compositeFieldName: Nombre del campo java dentro del campo compuesto
   compositeColumnName: Nombre de la columna de base de datos del campo
   compositeFieldType: Tipo (QDox Type) del campo dentro del campo compuesto
-->
<#function compositeForeignField arg_annotationJoinCol arg_type arg_annotationRefAttOvers>
<#assign compositeColumnName = annotationNamedParameter(arg_annotationJoinCol, "name") />
<#if arg_annotationJoinCol.getNamedParameter("referencedColumnName")?? >
  <#assign _refColumnName = annotationNamedParameter(arg_annotationJoinCol, "referencedColumnName") />
<#else>
  <#assign _refColumnName = compositeColumnName />
</#if>
<#--
 common_annotationRefAttOvers se calcula previamente
 Si el nombre del campo de bbdd es igual al referenciado, se utiliza el campo java
-->
<#assign compositeFieldName = "unknown" />
<#list arg_annotationRefAttOvers as _annotationAttOver >
  <#assign _columnAnnotation = _annotationAttOver.getNamedParameter("column") />
  <#if _refColumnName == annotationNamedParameter(_columnAnnotation, "name") >
    <#assign compositeFieldName = annotationNamedParameter(_annotationAttOver, "name") />
  </#if>
</#list>
<#list arg_type.getJavaClass().getFields() as _field >
  <#list _field.getAnnotations() as _annotation >
    <#if _annotation.getType().getValue() == "javax.persistence.EmbeddedId">
      <#list _field.getType().getJavaClass().getFields() as _idField >
        <#if _idField.getName() == compositeFieldName>
          <#assign compositeFieldType = _idField.getType() />
        </#if>
      </#list>
      <#--
       Se agrega el nombre del campo identificador
      -->
      <#assign compositeFieldName = _field.getName() + "." + compositeFieldName />
    </#if>
  </#list>
</#list>
<#assign compField={
   "compositeFieldName":compositeFieldName
  ,"compositeColumnName":compositeColumnName
  ,"compositeFieldType":compositeFieldType
  } />
<#return compField />
</#function>
<#-- ##########################################################################
 Macro que delega en methodInfo o fieldInfo en función del tipo de argumento
 y contiene la implementación común de la extracción de información de anotaciones
 Argumentos:
   arg_elem: QDox JavaMethod o JavaField del campo persistente, obtenido de la
             iteración de common_modelElements
 Información obtenida:
   isPersistent: Si el campo es persistente, es decir, se asocia a un campo de base de datos
   isEnum: Si el campo es un enum
   isIdentifier: Si es campo es el identificador de la entidad
   columnName: Columna de base de datos asociada al campo
   isManyToOne: Si el campo es padre de la entidad
   isManyToMany: Si el campo es una relación con la entidad
   isOneToMany: Si el campo es hijo de la entidad
   isComposite: Si el campo es compuesto (clave primaria o foránea compuesta)
   temporalValue: Para los campos de fecha, indica si es DATE o TIMESTAMP
   columnNullable: Indica si el campo es nullable (true) o not-null (false)
   columnLength: Si el campo es de texto, indica el largo en base de datos
   cascadeType: Indica el tipo de cascade del campo. Si no tiene, indica "none".
   searchTypes: Tipos de búsqueda del campo, especificados en @Search
   searchIgnoreCase: Indica si el campo tiene búsqueda del tipo "ignore-case"
-->
<#function elementInfo arg_elem>
<#if arg_elem.getClass().getSimpleName() == "JavaField" >
  <#assign info = fieldInfo(arg_elem) />
<#elseif arg_elem.getClass().getSimpleName() == "JavaMethod" >
  <#assign info = methodInfo(arg_elem) />
<#else>
<#-- TODO -->
</#if>
<#assign isPersistent = false />
<#assign isEnumerated = false />
<#assign isIdentifier = false />
<#assign isVersion = false />
<#assign isComposite = false />
<#assign isManyToOne = false />
<#assign isManyToMany = false />
<#assign isOneToMany = false />
<#assign temporalValue = "" />
<#assign columnName = "" />
<#assign columnNullable = "true" />
<#assign columnLength = "" />
<#assign cascadeType = "none" />
<#assign searchTypes = "" />
<#assign searchIgnoreCase = "false" />
<#list arg_elem.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.Column" ||
       _annotation.getType().getValue() == "javax.persistence.JoinColumn" ||
       _annotation.getType().getValue() == "javax.persistence.EmbeddedId" ||
       _annotation.getType().getValue() == "javax.persistence.JoinColumns">
    <#assign isPersistent = true />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.Enumerated" >
    <#assign isEnumerated = true />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.Id" ||
       _annotation.getType().getValue() == "javax.persistence.EmbeddedId">
    <#assign isIdentifier = true />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.Version" >
    <#assign isVersion = true />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.EmbeddedId" ||
       _annotation.getType().getValue() == "javax.persistence.JoinColumns">
    <#assign isComposite = true />
  </#if>
  <#-- Atributos de anotaciones estándar -->
  <#if _annotation.getType().getValue() == "javax.persistence.Column" >
    <#assign columnName = annotationNamedParameter(_annotation, "name") />
    <#assign columnNullable = annotationNamedParameterDefault(_annotation, "nullable", "true") />
    <#assign columnLength = annotationNamedParameterDefault(_annotation, "length", "") />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.Temporal" >
    <#assign temporalValue = annotationNamedParameter(_annotation, "value") />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.ManyToOne" >
    <#assign cascadeType = annotationNamedParameter(_annotation, "cascade") />
    <#assign isManyToOne = true />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.JoinColumn" >
    <#assign columnName = annotationNamedParameter(_annotation, "name") />    
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.ManyToMany" >
    <#assign isManyToMany = true />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.OneToMany" >
    <#assign isOneToMany = true />
  </#if>
  <#if _annotation.getType().getValue() == common_corePackage + ".common.entity.annotation.Search" >
    <#if _annotation.getNamedParameter("type")??>
      <#assign searchTypesList = _annotation.getNamedParameter("type") />
      <#if searchTypesList?is_sequence>
        <#list searchTypesList as searchType>
          <#assign searchTypes = searchTypes + "," + searchType?string />
        </#list>
      </#if>
      <#if searchTypesList?is_string>
        <#assign searchTypes = searchTypesList />
      </#if>
    </#if>
    <#assign searchIgnoreCase = annotationNamedParameterDefault(_annotation, "ignoreCase", "false") />
  </#if>
</#list>
<#assign elem={
   "info":info
  ,"isPersistent":isPersistent
  ,"isEnumerated":isEnumerated
  ,"isIdentifier":isIdentifier
  ,"isVersion":isVersion
  ,"isComposite":isComposite
  ,"isManyToOne":isManyToOne
  ,"isManyToMany":isManyToMany
  ,"isOneToMany":isOneToMany
  ,"columnName":columnName
  ,"columnNullable":columnNullable
  ,"columnLength":columnLength
  ,"cascadeType":cascadeType
  ,"searchTypes":searchTypes
  ,"searchIgnoreCase":searchIgnoreCase
  } />
<#return elem />
</#function>
<#-- ##########################################################################
-->
<#function methodInfo arg_method>
<#assign debug = "" />
<#assign fieldName = fmkUtilities("leftCut", arg_method.getName(), 3)?uncap_first />
<#assign fieldJavaType = arg_method.getReturns() />
<#assign fieldFullClassName = arg_method.getReturns().getValue() />
<#assign fieldType = fmkUtilities("classNameFromPackage", fieldFullClassName) />
<#assign fieldTypeWrapper = wrapperType(fieldType) />
<#assign fieldIsArray = fieldJavaType.isArray()/>
<#assign parentName = "Unknown"/>
<#assign parentField = "Unknown"/>
<#assign parentIdType = "Unknown"/>
<#assign parentIdName = "Unknown"/>
<#if fieldIsArray >
  <#assign fieldType = fieldType + "[]"/>
  <#assign fieldFullClassName = fieldFullClassName + "[]"/>
</#if>
<#assign fieldIsEnum = fieldJavaType.getJavaClass().getSuperClass().getValue() == "java.lang.Enum"/>
<#assign isCriterionUsable = true/>
<#if fieldType == "byte[]" || 
     fieldType == "FileBufferOutputStream" || 
     fieldType == "FileBufferWriter">
  <#assign isCriterionUsable = false/>
</#if>
<#list arg_method.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.JoinColumn">
    <#assign parent = arg_method.getReturns().getJavaClass() />
    <#list parent.getMethods() as _parentMethod >
      <#-- excluye constructores -->
      <#if !_parentMethod.isConstructor() >    
      <#list _parentMethod.getAnnotations() as _methodAnnotation >
        <#if _methodAnnotation.getType().getValue() == "javax.persistence.Id" || 
             _methodAnnotation.getType().getValue() == "javax.persistence.EmbeddedId">
          <#assign parentName = fmkUtilities("leftCut", arg_method.getName(), 3)?uncap_first/>
          <#assign parentIdName = fmkUtilities("leftCut", _parentMethod.getName(), 3)?uncap_first/>
          <#assign parentField = parentName + "." + parentIdName/>
          <#assign parentIdType = _parentMethod.getReturns().getValue()/>
        </#if>
      </#list>
      </#if>
    </#list>
  </#if>
</#list>
<#assign info={
   "debug":debug
  ,"fieldName":fieldName
  ,"fieldJavaType":fieldJavaType
  ,"fieldFullClassName":fieldFullClassName
  ,"fieldType":fieldType
  ,"fieldTypeWrapper":fieldTypeWrapper
  ,"fieldIsArray":fieldIsArray
  ,"fieldIsEnum":fieldIsEnum
  ,"isCriterionUsable":isCriterionUsable
  ,"parentName":parentName
  ,"parentField":parentField
  ,"parentIdType":parentIdType
  ,"parentIdName":parentIdName
  } />
<#return info />
</#function>
<#-- ##########################################################################
 Macro para extraer los datos desde un campo anotado
 No se utiliza directamente, sino desde la macro elementInfo
 Argumentos:
   arg_field: QDox JavaField del campo persistente
 Información obtenida:
   fieldName: Nombre del campo
   fieldJavaType: Tipo (QDox Type) del campo
   fieldFullClassName: String con el full class name del campo (ej: java.util.Date)
   fieldType: String con el simple class name del campo (ej: Date)
   fieldTypeWrapper: Si common_fieldType es primitivo, obtiene su wrapper. Si no, es la misma
   fieldIsArray: Indica si el campo es del tipo array
   fieldIsEnum: Indica si el campo es un enum
   isCriterionUsable: Indica si el campo se puede usar como criterios de búsqueda
 Si el campo es padre, obtiene además:
   parentName: Nombre del campo padre (igual al nombre del campo)
   parentField: Nombre del campo padre con nombre de su identificador ("campo.identificador")
   parentIdType: Tipo (QDox Type) del identificador del padre
   parentIdName: Nombre del identificador del campo padre
-->
<#function fieldInfo arg_field>
<#assign debug = "" />
<#assign fieldName = arg_field.getName() />
<#assign fieldJavaType = arg_field.getType() />
<#assign fieldFullClassName = arg_field.getType().getValue() />
<#assign fieldType = fmkUtilities("classNameFromPackage", fieldFullClassName) />
<#assign fieldTypeWrapper = wrapperType(fieldType) />
<#assign fieldIsArray = fieldJavaType.isArray()/>
<#assign parentName = "Unknown"/>
<#assign parentField = "Unknown"/>
<#assign parentIdType = "Unknown"/>
<#assign parentIdName = "Unknown"/>
<#if fieldIsArray >
  <#assign fieldType = fieldType + "[]"/>
  <#assign fieldFullClassName = fieldFullClassName + "[]"/>
</#if>
<#assign fieldIsEnum = fieldJavaType.getJavaClass().getSuperClass().getValue() == "java.lang.Enum"/>
<#assign isCriterionUsable = true/>
<#if fieldType == "byte[]" || 
     fieldType == "FileBufferOutputStream" || 
     fieldType == "FileBufferWriter">
  <#assign isCriterionUsable = false/>
</#if>
<#list arg_field.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.JoinColumn">
    <#assign parent = arg_field.getType().getJavaClass() />
    <#list parent.getFields() as _parentField >
      <#list _parentField.getAnnotations() as _fieldAnnotation >
        <#if _fieldAnnotation.getType().getValue() == "javax.persistence.Id" || 
             _fieldAnnotation.getType().getValue() == "javax.persistence.EmbeddedId">
          <#assign parentName = arg_field.getName()/>
          <#assign parentField = arg_field.getName() + "." + _parentField.getName()/>
          <#assign parentIdType = _parentField.getType().getValue()/>
          <#assign parentIdName = _parentField.getName()/>
        </#if>
      </#list>
    </#list>
  </#if>
</#list>
<#assign info={
   "debug":debug
  ,"fieldName":fieldName
  ,"fieldJavaType":fieldJavaType
  ,"fieldFullClassName":fieldFullClassName
  ,"fieldType":fieldType
  ,"fieldTypeWrapper":fieldTypeWrapper
  ,"fieldIsArray":fieldIsArray
  ,"fieldIsEnum":fieldIsEnum
  ,"isCriterionUsable":isCriterionUsable
  ,"parentName":parentName
  ,"parentField":parentField
  ,"parentIdType":parentIdType
  ,"parentIdName":parentIdName
  } />
<#return info />
</#function>
<#-- ##########################################################################
 Macro para extraer los datos desde un campo padre.
 Argumentos:
   arg_parentElem: QDox JavaMethod o JavaField del campo padre persistente, 
       obtenido de la iteración de common_modelElements
 Información obtenida:
   columnName: Columna de base de datos asociada al campo padre
   parentUseMethods: true si las anotaciones de la clase del campo padre
       están en los métodos
   parentUseFields: true si las anotaciones de la clase del campo padre
       están en los campos
   parentIsAbstract: true si la superclase es abstracta
   parentTableName: Nombre de la tabla del campo padre
   columnNullable: Indica si el campo padre es nullable, según anotación
   grandParentIdName: Nombre del campo identificador del campo padre
   grandParentIdType: Full class name del campo identificador del campo padre
   parentModelElements: Colección de métodos o campos anotados del padre,
       para ser iterada y utilizar elementos del padre
-->
<#function parentInfo arg_parentElem>
<#assign columnName = ""/>
<#assign parentUseMethods = false/>
<#assign parentUseFields = false/>
<#assign parentIsAbstract = false/>
<#assign grandParentIdName = "" />
<#assign grandParentIdType = "" />
<#if arg_parentElem.getClass().getSimpleName() == "JavaField">
  <#assign _parentEntity = arg_parentElem.getType().getJavaClass()/>
  <#assign _parentName = arg_parentElem.getName()/>
<#elseif arg_parentElem.getClass().getSimpleName() == "JavaMethod">
  <#assign _parentEntity = arg_parentElem.getReturns().getJavaClass()>
  <#assign _parentName = fmkUtilities("leftCut", arg_parentElem.getName(), 3)?uncap_first/>     
</#if>
<#list _parentEntity.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.Table" >
    <#assign parentTableName=annotationNamedParameter(_annotation, "name") />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.DiscriminatorColumn" >
    <#assign parentIsAbstract=true />
  </#if>
</#list>
<#list arg_parentElem.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.JoinColumn" >
    <#assign columnName=annotationNamedParameter(_annotation, "name") />
    <#assign columnNullable=annotationNamedParameterDefault(_annotation, "nullable", true) />
  </#if>
</#list>
<#list _parentEntity.getMethods() as _grandParentMethod >
  <#-- excluye constructores -->
  <#if !_grandParentMethod.isConstructor() >
  <#list _grandParentMethod.getAnnotations() as _methodAnnotation >
    <#--
     Si algún método está marcado con una anotación de javax.persistence, 
     las anotaciones se buscan en los métodos
    -->
    <#if _methodAnnotation.getType().getValue()?starts_with("javax.persistence.") >
      <#assign parentUseMethods=true />
    </#if>
    <#if _methodAnnotation.getType().getValue() == "javax.persistence.Id" || 
         _methodAnnotation.getType().getValue() == "javax.persistence.EmbeddedId" >
      <#assign grandParentIdName = _parentName + fmkUtilities("leftCut", _grandParentMethod.getName(), 3)?uncap_first />
      <#assign grandParentIdType = _grandParentMethod.getReturns().getValue() />
    </#if>
  </#list>
  </#if>
</#list>
<#list _parentEntity.getFields() as _grandParentField >
  <#list _grandParentField.getAnnotations() as _fieldAnnotation >
    <#--
     Si algún campo está marcado con una anotación de javax.persistence, 
     las anotaciones se buscan en los campos
    -->
    <#if _fieldAnnotation.getType().getValue()?starts_with("javax.persistence.") >
      <#assign parentUseFields=true />
    </#if>
    <#if _fieldAnnotation.getType().getValue() == "javax.persistence.Id" || 
         _fieldAnnotation.getType().getValue() == "javax.persistence.EmbeddedId" >
      <#assign grandParentIdName = _parentName + _grandParentField.getName() />
      <#assign grandParentIdType = _grandParentField.getType().getValue() />
    </#if>
  </#list>
</#list>
<#--
Identificando el tipo de colección de elementos a utilizar, métodos o campos
De esta manera, se pueden parsear anotaciones en campos o elementos, recorriéndolos
sólo una vez
-->
<#if parentUseMethods >
  <#assign parentModelElements = _parentEntity.getMethods() />
<#elseif parentUseFields >
  <#assign parentModelElements = _parentEntity.getFields() />
<#else>
  <#assign parentModelElements = [] />
</#if>
<#assign info={
   "columnName":columnName
  ,"parentUseMethods":parentUseMethods
  ,"parentUseFields":parentUseFields
  ,"parentIsAbstract":parentIsAbstract
  ,"parentTableName":parentTableName
  ,"columnNullable":columnNullable
  ,"grandParentIdName":grandParentIdName
  ,"grandParentIdType":grandParentIdType
  ,"parentModelElements":parentModelElements
  } />
<#return info />
</#function>
<#-- ##########################################################################
 Macro que delega en parentMethodInfo o parentFieldInfo en función del tipo de argumento
 y contiene la implementación común de la extracción de información de anotaciones
 Argumentos:
   arg_parentElem: QDox JavaMethod o JavaField del campo padre persistente, 
       obtenido de la iteración de common_modelElements
 Información obtenida:
   parentIsEnum: Si el campo es un enum
   parentIsIdentifier: Si es campo es el identificador de la entidad
   parentGeneratedValueType: Tipo de generación de la clave primaria del padre
   parentColumnName: Columna de base de datos asociada al campo
   parentColumnNullable: Indica si el campo es nullable (true) o not-null (false)
   parentIsManyToOne: Si el campo es padre de la entidad
   parentIsComposite: Si el campo es compuesto (clave primaria o foránea compuesta)
   parentIsVersion: Si es campo es la version de la entidad
   parentTemporalValue: Para los campos de fecha, indica si es DATE o TIMESTAMP
-->
<#function parentElementInfo arg_parentElem>
<#if arg_parentElem.getClass().getSimpleName() == "JavaField" >
  <#assign info = parentFieldInfo(arg_parentElem) />
<#elseif arg_parentElem.getClass().getSimpleName() == "JavaMethod" >
  <#assign info = parentMethodInfo(arg_parentElem) />
<#else>
<#-- TODO -->
</#if>
<#--
 Extracción de información de las anotaciones, que es igual
 en métodos y campos
-->
<#assign parentIsEnum = false/>
<#assign parentIsIdentifier = false/>
<#assign parentGeneratedValueType = "none"/>
<#assign parentColumnName = ""/>
<#assign parentColumnNullable = true/>
<#assign parentIsManyToOne = false/>
<#assign parentIsComposite = false/>
<#assign parentIsVersion = false/>
<#assign parentTemporalValue = ""/>
<#list arg_parentElem.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.Enumerated" >
    <#assign parentIsEnum = true />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.Id" ||
       _annotation.getType().getValue() == "javax.persistence.EmbeddedId">
    <#assign parentIsIdentifier = true />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.GeneratedValue" >
    <#assign parentGeneratedValueType = annotationNamedParameter(_annotation, "strategy") />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.EmbeddedId" ||
       _annotation.getType().getValue() == "javax.persistence.JoinColumns">
    <#assign parentIsComposite = true />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.Version" >
    <#assign parentIsVersion = true />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.Column" >
    <#assign parentColumnName = annotationNamedParameter(_annotation, "name") />
    <#assign parentColumnNullable=annotationNamedParameterDefault(_annotation, "nullable", true) />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.Temporal" >
    <#assign parentTemporalValue = annotationNamedParameter(_annotation, "value") />
  </#if>
  <#if _annotation.getType().getValue() == "javax.persistence.JoinColumn" >
    <#assign parentIsManyToOne = true/>
    <#assign parentColumnName = annotationNamedParameter(_annotation, "name") />
    <#assign parentColumnNullable=annotationNamedParameterDefault(_annotation, "nullable", true) />
  </#if>
</#list>
<#assign elem={
   "info":info
  ,"parentIsEnum":parentIsEnum
  ,"parentIsIdentifier":parentIsIdentifier
  ,"parentGeneratedValueType":parentGeneratedValueType
  ,"parentColumnName":parentColumnName
  ,"parentColumnNullable":parentColumnNullable
  ,"parentIsManyToOne":parentIsManyToOne
  ,"parentIsComposite":parentIsComposite
  ,"parentIsVersion":parentIsVersion
  ,"parentTemporalValue":parentTemporalValue
  } />
<#return elem />
</#function>
<#-- ##########################################################################
 Macro para extraer los datos desde un método getter anotado de un campo padre
 Argumentos:
   arg_parentMethod: QDox JavaMethod del campo persistente padre
 Información obtenida:
   parentFieldName: Nombre del campo java extraído por el getter
   parentFieldJavaType: Tipo (QDox Type) del campo
   parentFieldFullClassName: String con el full class name del campo (ej: java.util.Date)
   parentFieldType: String con el simple class name del campo (ej: Date)
   parentFieldIsArray: Indica si el campo es del tipo array
   parentIsCriterionUsable: Indica si el campo se puede usar como criterios de búsqueda
-->
<#function parentMethodInfo arg_parentMethod>
<#--
 Principal. Se extrae de los getter
-->
<#if arg_parentMethod.getName()?starts_with("get") >
  <#assign parentFieldName = fmkUtilities("leftCut", arg_parentMethod.getName(), 3)?uncap_first />
  <#assign parentFieldJavaType = arg_parentMethod.getReturns() />
  <#assign parentFieldFullClassName = parentFieldJavaType.getValue() />
  <#assign parentFieldType = fmkUtilities("classNameFromPackage", parentFieldFullClassName) />
  <#assign parentFieldIsArray = parentFieldJavaType.isArray() />
  <#if parentFieldIsArray >
    <#assign parentFieldType = parentFieldType + "[]" />
    <#assign parentFieldFullClassName = parentFieldFullClassName + "[]" />
  </#if>
  <#assign parentIsCriterionUsable = true />
  <#if parentFieldType  == "byte[]" || parentFieldType == "FileBufferOutputStream" || parentFieldType == "FileBufferWriter">
    <#assign parentIsCriterionUsable = false />
  </#if>
  <#list arg_parentMethod.getAnnotations() as _annotation >
    <#-- TODO -->
  </#list>
  <#assign info = {
     "parentFieldName":parentFieldName
    ,"parentFieldJavaType":parentFieldJavaType
    ,"parentFieldFullClassName":parentFieldFullClassName
    ,"parentFieldType":parentFieldType
    ,"parentFieldIsArray":parentFieldIsArray
    ,"parentIsCriterionUsable":parentIsCriterionUsable
  } />
<#else>
  <#assign info = [] />
</#if>
<#return info />
</#function>
<#-- ##########################################################################
 Macro para extraer los datos desde un campo padre anotado
 Argumentos:
   arg_parentField: QDox JavaField del campo persistente padre
 Información obtenida:
   parentFieldName: Nombre del campo
   parentFieldJavaType: Tipo (QDox Type) del campo
   parentFieldFullClassName: String con el full class name del campo (ej: java.util.Date)
   parentFieldType: String con el simple class name del campo (ej: Date)
   parentFieldIsArray: Indica si el campo es del tipo array
   parentIsCriterionUsable: Indica si el campo se puede usar como criterios de búsqueda
 Si el campo es padre, obtiene además:
   parentParentName: Nombre del campo padre (igual al nombre del campo)
   parentParentField: Nombre del campo padre con nombre de su identificador ("campo.identificador")
   parentParentIdType: Tipo (QDox Type) del identificador del padre
-->
<#function parentFieldInfo arg_parentField>
<#assign parentFieldName = arg_parentField.getName() />
<#assign parentFieldJavaType = arg_parentField.getType() />
<#assign parentFieldFullClassName = parentFieldJavaType.getValue() />
<#assign parentFieldType = fmkUtilities("classNameFromPackage", parentFieldFullClassName) />
<#assign parentFieldIsArray = parentFieldJavaType.isArray() />
<#if parentFieldIsArray >
  <#assign parentFieldType = parentFieldType + "[]" />
  <#assign parentFieldFullClassName = parentFieldFullClassName + "[]" />
</#if>
<#assign parentIsCriterionUsable = true />
<#if parentFieldType  == "byte[]" || parentFieldType == "FileBufferOutputStream" || parentFieldType == "FileBufferWriter">
  <#assign parentIsCriterionUsable = false />
</#if>
<#-- TODO: Generic type -->
<#list arg_parentField.getAnnotations() as _annotation >
  <#if _annotation.getType().getValue() == "javax.persistence.JoinColumn" >
    <#assign parent = arg_parentField.getType().getJavaClass() />
    <#list parent.getFields() as _parentField >
      <#list _parentField.getAnnotations() as _fieldAnnotation >
        <#if _fieldAnnotation.getType().getValue() == "javax.persistence.Id">
          <#assign parentParentName = arg_parentField.getName()/>
          <#assign parentParentField = arg_parentField.getName() + "." + _parentField.getName()/>
          <#assign parentParentIdType = _parentField.getType().getValue()/>
        </#if>
      </#list>
    </#list>
  </#if>
</#list>
<#assign info = {
   "parentFieldName":parentFieldName
  ,"parentFieldJavaType":parentFieldJavaType
  ,"parentFieldFullClassName":parentFieldFullClassName
  ,"parentFieldType":parentFieldType
  ,"parentFieldIsArray":parentFieldIsArray
  ,"parentIsCriterionUsable":parentIsCriterionUsable
  ,"parentParentName":parentParentName
  ,"parentParentField":parentParentField
  ,"parentParentIdType":parentParentIdType
} />
<#return info />
</#function>
<#-- ##########################################################################
 Macro para convertir un primitivo en wrapper
 Argumentos:
   arg_type: String con el tipo del campo
 Información obtenida:
   typeWrapper: String con el tipo wrapper si arg_type es un primitivo,
       o la misma clase si no.
-->
<#function wrapperType arg_type>
<#assign typeWrapper = arg_type/>
<#if arg_type = "int">
  <#assign typeWrapper = "Integer"/>
<#elseif arg_type = "long">
  <#assign typeWrapper = "Long"/>
<#elseif arg_type = "short">
  <#assign typeWrapper = "Short"/>
<#elseif arg_type = "char">
  <#assign typeWrapper = "Character"/>
<#elseif arg_type = "boolean">
  <#assign typeWrapper = "Boolean"/>
<#elseif arg_type = "float">
  <#assign typeWrapper = "Float"/>
<#elseif arg_type = "double">
  <#assign typeWrapper = "Double"/>
<#elseif arg_type = "byte">
  <#assign typeWrapper = "Byte"/>
</#if>
<#return typeWrapper />
</#function>
<#-- ##########################################################################
 Macro para extracción de un parámetro de una anotación
 Desde QDox 1.7, los parámetros tipo String retornan también las comillas
 Argumentos:
   arg_annotation: QDox Annotation a utilizar
   arg_namedParam: String con el nombre del campo de la anotación
   arg_defaultValue: (Opcional) Valor default si no se encuentra el campo en 
       la anotación
 Información obtenida:
   paramValue: Valor del campo de la anotación. Si es String, se le
       eliminan las comillas y se le hace un trim
-->
<#function annotationNamedParameter arg_annotation arg_namedParam>
  <#return annotationNamedParameterDefault(arg_annotation, arg_namedParam, "") />
</#function>
<#function annotationNamedParameterDefault arg_annotation arg_namedParam arg_defaultValue>
  <#if arg_annotation.getNamedParameter(arg_namedParam)??>
    <#if arg_annotation.getNamedParameter(arg_namedParam)?is_string>
      <#assign paramValue = arg_annotation.getNamedParameter(arg_namedParam)?string?trim/>
      <#--Eliminando las comillas-->
      <#if paramValue[0] == '"'>
        <#assign paramValue = fmkUtilities("rightCut", paramValue, 1) />
        <#assign paramValue = fmkUtilities("leftCut", paramValue, 1) />
      </#if>
    </#if>
  <#else>
    <#assign paramValue = arg_defaultValue />
  </#if>
  <#return paramValue />
</#function>
