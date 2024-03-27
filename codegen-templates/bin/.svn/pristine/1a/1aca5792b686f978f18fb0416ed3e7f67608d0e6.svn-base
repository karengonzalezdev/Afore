<#--
 Criterios de búsqueda, que son comunes a todos los ORM (no iBatis)
-->
<#macro queryCommon >
<#if locale == "es" >
    // Constantes con los nombres de los campos de la entidad, para ser
    // utilizados en los criterios de búsqueda y ordenamiento
<#else>
    // Constants with the entity field names, to be used in search
    // and order criteria
</#if>
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.info.isCriterionUsable  &&
       !elemInfo.isVersion >
    public static final String ${elemInfo.info.fieldName?upper_case} = "${elemInfo.info.fieldName}";
  </#if>
  <#if elemInfo.isManyToMany>
    public static final String ${elemInfo.info.fieldName?upper_case} = "${elemInfo.info.fieldName}";
  </#if>
  <#if elemInfo.isOneToMany>
    public static final String ${elemInfo.info.fieldName?upper_case} = "${elemInfo.info.fieldName}";
  </#if>
</#list>

    private static final Set<String> FIELD_NAMES_SET = new HashSet<String>();
<#if entity.hasParents >
    private static final Set<String> PARENT_NAMES_SET = new HashSet<String>();
</#if>
    
    static {
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.info.isCriterionUsable  &&
       !elemInfo.isVersion>
     <#if elemInfo.isManyToOne >
        PARENT_NAMES_SET.add(${elemInfo.info.fieldName?upper_case});
     <#else>
        FIELD_NAMES_SET.add(${elemInfo.info.fieldName?upper_case});
     </#if>
  </#if>
</#list>
    }
<#--
 Declaración de campos
-->
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.info.isCriterionUsable  &&
       !elemInfo.isVersion>
    <#assign typeSuffix = "" />
    <#if elemInfo.isManyToOne >
      <#assign typeSuffix = "Query" />
    </#if>
    <#if elemInfo.info.fieldType == "Date">

    /**
      <#if locale == "es" >
     * Valor inferior de rango de búsqueda de fecha '${elemInfo.info.fieldName}'
      <#else>
     * Lower value of range in search of date '${elemInfo.info.fieldName}'
      </#if>
     */
    private ${elemInfo.info.fieldType} ${elemInfo.info.fieldName}Min;

    /**
      <#if locale == "es" >
     * Valor superior de rango de búsqueda de fecha '${elemInfo.info.fieldName}'
      <#else>
     * Upper value of range in search of date '${elemInfo.info.fieldName}'
      </#if>
     */
    private ${elemInfo.info.fieldType} ${elemInfo.info.fieldName}Max;
    <#else>

    /**
      <#if locale == "es" >
     * Valor de búsqueda de campo '${elemInfo.info.fieldName}'
      <#else>
     * Search value of field '${elemInfo.info.fieldName}'
      </#if>
     */
    private ${elemInfo.info.fieldTypeWrapper}${typeSuffix} ${elemInfo.info.fieldName};
      <#if elemInfo.info.fieldType == "String">
        <#if elemInfo.searchTypes == "" || 
             (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.LIKE")) >

    /**
      <#if locale == "es" >
     * Tipo de comparador para la busqueda por campo '${elemInfo.info.fieldName}'
      <#else>
     * Comparator type for searching by field '${elemInfo.info.fieldName}'
      </#if>
     */
    private TextComparator ${elemInfo.info.fieldName}Comparator = TextComparator.EQUALS;
        </#if>
        <#if elemInfo.searchIgnoreCase == "true">

    /**
      <#if locale == "es" >
     * Activa busqueda por campo '${elemInfo.info.fieldName}' con ignore-case
      <#else>
     * Actives searching by field '${elemInfo.info.fieldName}' with ignore-case
      </#if>
     */
    private boolean ${elemInfo.info.fieldName}IgnoreCase = false;
        </#if>
      </#if>
      <#if elemInfo.info.fieldTypeWrapper == "Integer" ||
           elemInfo.info.fieldTypeWrapper == "Long" ||
           elemInfo.info.fieldType == "String" ||
           elemInfo.info.fieldIsEnum>
        <#if elemInfo.searchTypes == "" || 
             (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.IN")) >

    /**
      <#if locale == "es" >
     * Lista de valores del campo '${elemInfo.info.fieldName}' para busquedas tipo IN
      <#else>
     * Values list of field '${elemInfo.info.fieldName}' for IN type searching
      </#if>
     */
    private List<${elemInfo.info.fieldTypeWrapper}> ${elemInfo.info.fieldName}In = new ArrayList<${elemInfo.info.fieldTypeWrapper}>(0);
        </#if>
      </#if>
      <#if elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.RANGE") >

    /**
      <#if locale == "es" >
     * Valor inferior de rango de búsqueda de campo '${elemInfo.info.fieldName}'
      <#else>
     * Lower value for range searching by field '${elemInfo.info.fieldName}'
      </#if>
     */
    private ${elemInfo.info.fieldTypeWrapper} ${elemInfo.info.fieldName}Min;

    /**
      <#if locale == "es" >
     * Valor superior de rango de búsqueda de campo '${elemInfo.info.fieldName}'
      <#else>
     * Upper value for range searching by field '${elemInfo.info.fieldName}'
      </#if>
     */
    private ${elemInfo.info.fieldTypeWrapper} ${elemInfo.info.fieldName}Max;
      </#if>
    </#if>
    <#if elemInfo.isManyToOne >

    /**
      <#if locale == "es" >
     * Lista de valores del campo padre '${elemInfo.info.fieldName}' para busquedas tipo IN
     * Sólo se consideran los campos ID
      <#else>
     * Values list of parent field '${elemInfo.info.fieldName}' for IN type searching
     * Only the ID fields are considered
      </#if>
     */
    private List<${elemInfo.info.fieldType}> ${elemInfo.info.fieldName}IdIn = new ArrayList<${elemInfo.info.fieldType}>(0);
    </#if>
    <#if !elemInfo.isIdentifier>

    /**
      <#if locale == "es" >
     * Permite buscar cuando campo '${elemInfo.info.fieldName}' es NULL
      <#else>
     * Searches when field '${elemInfo.info.fieldName}' is NULL
      </#if>
     */
    private boolean ${elemInfo.info.fieldName}IsNull = false;

    /**
      <#if locale == "es" >
     * Permite buscar cuando campo '${elemInfo.info.fieldName}' es NOT NULL
      <#else>
     * Searches when field '${elemInfo.info.fieldName}' is NOT NULL
      </#if>
     */
    private boolean ${elemInfo.info.fieldName}IsNotNull = false;
    </#if>
  </#if>
</#list>
<#--
 Flag de join a campos padre o relaciones
-->
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.isManyToOne>

    /**
      <#if locale == "es" >
     * Indica si en la consulta se hace un inner join con el padre '${elemInfo.info.fieldName}'
      <#else>
     * Indicates if the query uses an inner join with the parent field '${elemInfo.info.fieldName}'
      </#if>
     */
    private boolean innerJoin${elemInfo.info.fieldName?cap_first} = false;

    /**
      <#if locale == "es" >
     * Indica si en la consulta se hace un left join con el padre '${elemInfo.info.fieldName}'
      <#else>
     * Indicates if the query uses a left join with the parent field '${elemInfo.info.fieldName}'
      </#if>
     */
    private boolean leftJoin${elemInfo.info.fieldName?cap_first} = false;
  </#if>
  <#if elemInfo.isManyToMany>

    /**
      <#if locale == "es" >
     * Indica si en la consulta se hace un join con el campo relacionado '${elemInfo.info.fieldName}'
      <#else>
     * Indicates if the query uses a join with the associated field '${elemInfo.info.fieldName}'
      </#if>
     */
    private boolean join${elemInfo.info.fieldName?cap_first} = false;
  </#if>
  <#if elemInfo.isOneToMany>

    /**
      <#if locale == "es" >
     * Indica si en la consulta se hace un join con el campo hijo '${elemInfo.info.fieldName}'
     * Warning: Hacer join a m�s de un hijo genera productos cartesianos en el resultado.
      <#else>
     * Indicates if the query uses a join with the children field '${elemInfo.info.fieldName}'
     * Warning: Join to more than one children produces cartesian product in the result.
      </#if>
     */
    private boolean join${elemInfo.info.fieldName?cap_first} = false;
  </#if>
</#list>
<#--
 Constructores
-->
<#if !entity.isAbstract >

    /**
      <#if locale == "es" >
     * Constructor default
      <#else>
     * Default constructor
      </#if>
     */
    public ${classname}() {
    
    }
  <#if !entity.isSubclass >

    /**
      <#if locale == "es" >
     * Constructor usando identificador
      <#else>
     * Constructor by identifier
      </#if>
     */
    public ${classname}(${entity.idClassName} ${entity.idName}) {
        set${entity.idName?cap_first}(${entity.idName});
    }
  <#else>

    /**
      <#if locale == "es" >
     * Constructor usando identificador de superclase
      <#else>
     * Constructor by superclass identifier
      </#if>
     */
    public ${classname}(${entity.superIdClassName} ${entity.superIdName}) {
        set${entity.superIdName?cap_first}(${entity.superIdName});
    }
  </#if>
</#if>
<#--
 Getters y setters de campos
-->
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.info.isCriterionUsable  &&
       !elemInfo.isVersion>
    <#assign typeSuffix = "" />
    <#if elemInfo.isManyToOne >
      <#assign typeSuffix = "Query" />
    </#if>
    <#if elemInfo.info.fieldType == "Date">

    /**
      <#if locale == "es" >
     * Obtiene valor inferior de rango de búsqueda de fecha '${elemInfo.info.fieldName}'
      <#else>
     * Gets lower value of range in search of date '${elemInfo.info.fieldName}'
      </#if>
     * @return ${elemInfo.info.fieldName}Min
     */
    public ${elemInfo.info.fieldType} get${elemInfo.info.fieldName?cap_first}Min() {
        if (isFixDatetimes() && ${elemInfo.info.fieldName}Min != null) {
            return DateUtil.toDayBegin(${elemInfo.info.fieldName}Min);
        }
        return ${elemInfo.info.fieldName}Min;
    }

    /**
      <#if locale == "es" >
     * Asigna valor inferior de rango de búsqueda de fecha '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName}Min valor asignado
      <#else>
     * Sets lower value of range in search of date '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName}Min setter value
      </#if>
     */
    public void set${elemInfo.info.fieldName?cap_first}Min(${elemInfo.info.fieldType} ${elemInfo.info.fieldName}Min) {
        this.${elemInfo.info.fieldName}Min = ${elemInfo.info.fieldName}Min;
    }

    /**
      <#if locale == "es" >
     * Obtiene valor superior de rango de búsqueda de fecha '${elemInfo.info.fieldName}'
      <#else>
     * Gets upper value of range in search of date '${elemInfo.info.fieldName}'
      </#if>
     * @return ${elemInfo.info.fieldName}Max
     */
    public ${elemInfo.info.fieldType} get${elemInfo.info.fieldName?cap_first}Max() {
        if (isFixDatetimes() && ${elemInfo.info.fieldName}Max != null) {
            return DateUtil.toDayEnd(${elemInfo.info.fieldName}Max);
        }
        return ${elemInfo.info.fieldName}Max;
    }

    /**
      <#if locale == "es" >
     * Asigna valor superior de rango de búsqueda de fecha '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName}Max valor asignado
      <#else>
     * Sets upper value of range in search of date '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName}Max setter value
      </#if>
     */
    public void set${elemInfo.info.fieldName?cap_first}Max(${elemInfo.info.fieldType} ${elemInfo.info.fieldName}Max) {
        this.${elemInfo.info.fieldName}Max = ${elemInfo.info.fieldName}Max;
    }
    <#else>

    /**
      <#if locale == "es" >
     * Obtiene el valor de búsqueda de campo '${elemInfo.info.fieldName}'
      <#else>
     * Gets the search value of field '${elemInfo.info.fieldName}'
      </#if>
     * @return ${elemInfo.info.fieldName}
     */
    public ${elemInfo.info.fieldTypeWrapper}${typeSuffix} get${elemInfo.info.fieldName?cap_first}() {
      <#if elemInfo.info.fieldType == "String">
        <#if elemInfo.searchTypes == "" || 
             (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.LIKE")) >
        if (${elemInfo.info.fieldName}Comparator != TextComparator.EQUALS) {
            return escapeLikeCharacters(${elemInfo.info.fieldName});
        }
        </#if>
      </#if>
        return ${elemInfo.info.fieldName};
    }
    
    /**
      <#if locale == "es" >
     * Asigna el valor de búsqueda de campo '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName} valor asignado
      <#else>
     * Sets the search value of field '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName} setter value
      </#if>
     */
    public void set${elemInfo.info.fieldName?cap_first}(${elemInfo.info.fieldTypeWrapper}${typeSuffix} ${elemInfo.info.fieldName}) {
        this.${elemInfo.info.fieldName} = ${elemInfo.info.fieldName};
    }
      <#if elemInfo.info.fieldType == "String">
        <#if elemInfo.searchTypes == "" || 
             (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.LIKE")) >

    /**
      <#if locale == "es" >
     * Obtiene el tipo de comparador para la busqueda por campo '${elemInfo.info.fieldName}'
      <#else>
     * Gets the comparator type for searching by field '${elemInfo.info.fieldName}'
      </#if>
     * @return ${elemInfo.info.fieldName}Comparator
     */
    public TextComparator get${elemInfo.info.fieldName?cap_first}Comparator() {
        return ${elemInfo.info.fieldName}Comparator;
    }
    
    /**
      <#if locale == "es" >
     * Asigna el tipo de comparador para la busqueda por campo '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName}Comparator valor asignado
      <#else>
     * Sets the comparator type for searching by field '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName}Comparator setter value
      </#if>
     */
    public void set${elemInfo.info.fieldName?cap_first}Comparator(TextComparator ${elemInfo.info.fieldName}Comparator) {
        this.${elemInfo.info.fieldName}Comparator = ${elemInfo.info.fieldName}Comparator;
    }
        </#if>
        <#if elemInfo.searchIgnoreCase == "true">

    /**
      <#if locale == "es" >
     * Indica si está activada busqueda por campo '${elemInfo.info.fieldName}' con ignore-case
      <#else>
     * Indicated if searching by field '${elemInfo.info.fieldName}' with ignore-case is active
      </#if>
     * @return ${elemInfo.info.fieldName}IgnoreCase
     */
    public boolean is${elemInfo.info.fieldName?cap_first}IgnoreCase() {
        return ${elemInfo.info.fieldName}IgnoreCase;
    }
    
    /**
      <#if locale == "es" >
     * Activa busqueda por campo '${elemInfo.info.fieldName}' con ignore-case
      <#else>
     * Actives searching by field '${elemInfo.info.fieldName}' with ignore-case
      </#if>
     */
    public void set${elemInfo.info.fieldName?cap_first}IgnoreCase(boolean ${elemInfo.info.fieldName}IgnoreCase) {
        this.${elemInfo.info.fieldName}IgnoreCase = ${elemInfo.info.fieldName}IgnoreCase;
    }
        </#if>
      </#if>
      <#if elemInfo.info.fieldTypeWrapper == "Integer" ||
           elemInfo.info.fieldTypeWrapper == "Long" ||
           elemInfo.info.fieldType == "String" ||
           elemInfo.info.fieldIsEnum>
        <#if elemInfo.searchTypes == "" || 
             (elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.IN")) >

    /**
      <#if locale == "es" >
     * Lista de valores del campo '${elemInfo.info.fieldName}' para busquedas tipo IN
      <#else>
     * Values list of field '${elemInfo.info.fieldName}' for IN type searching
      </#if>
     * @return List<${elemInfo.info.fieldTypeWrapper}>
     */
    public List<${elemInfo.info.fieldTypeWrapper}> get${elemInfo.info.fieldName?cap_first}In() {
        return ${elemInfo.info.fieldName}In;
    }
    
    /**
      <#if locale == "es" >
     * Agrega un valor del campo '${elemInfo.info.fieldName}' para busquedas tipo IN
      <#else>
     * Adds a value of field '${elemInfo.info.fieldName}' for IN type searching
      </#if>
     * @param ${elemInfo.info.fieldTypeWrapper}
     */
    public void add${elemInfo.info.fieldName?cap_first}In(${elemInfo.info.fieldTypeWrapper} ${elemInfo.info.fieldName}) {
        ${elemInfo.info.fieldName}In.add(${elemInfo.info.fieldName});
    }
        </#if>
      </#if>
      <#if elemInfo.searchTypes != "" && elemInfo.searchTypes?contains("SearchType.RANGE") >

    /**
      <#if locale == "es" >
     * Obtiene valor inferior de rango de búsqueda de campo '${elemInfo.info.fieldName}'
      <#else>
     * Gets lower value for range searching by field '${elemInfo.info.fieldName}'
      </#if>
     * @return ${elemInfo.info.fieldName}Min
     */
    public ${elemInfo.info.fieldTypeWrapper} get${elemInfo.info.fieldName?cap_first}Min() {
        return ${elemInfo.info.fieldName}Min;
    }

    /**
      <#if locale == "es" >
     * Obtiene valor inferior de rango de búsqueda de campo '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName}Min valor asignado
      <#else>
     * Sets lower value for range searching by field '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName}Min setter value
      </#if>
     */
    public void set${elemInfo.info.fieldName?cap_first}Min(${elemInfo.info.fieldTypeWrapper} ${elemInfo.info.fieldName}Min) {
        this.${elemInfo.info.fieldName}Min = ${elemInfo.info.fieldName}Min;
    }

    /**
      <#if locale == "es" >
     * Obtiene valor superior de rango de búsqueda de campo '${elemInfo.info.fieldName}'
      <#else>
     * Gets upper value for range searching by field '${elemInfo.info.fieldName}'
      </#if>
     * @return ${elemInfo.info.fieldName}Max
     */
    public ${elemInfo.info.fieldTypeWrapper} get${elemInfo.info.fieldName?cap_first}Max() {
        return ${elemInfo.info.fieldName}Max;
    }

    /**
      <#if locale == "es" >
     * Obtiene valor superior de rango de búsqueda de campo '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName}Max valor asignado
      <#else>
     * Sets upper value for range searching by field '${elemInfo.info.fieldName}'
     * @param ${elemInfo.info.fieldName}Max setter value
      </#if>
     */
    public void set${elemInfo.info.fieldName?cap_first}Max(${elemInfo.info.fieldTypeWrapper} ${elemInfo.info.fieldName}Max) {
        this.${elemInfo.info.fieldName}Max = ${elemInfo.info.fieldName}Max;
    }
      </#if>
    </#if>
    <#if elemInfo.isManyToOne >

    /**
      <#if locale == "es" >
     * Lista de valores del campo padre '${elemInfo.info.fieldName}' para busquedas tipo IN
     * Sólo se consideran los campos ID
      <#else>
     * Values list of parent field '${elemInfo.info.fieldName}' for IN type searching
     * Only the ID fields are considered
      </#if>
     * @return List<${elemInfo.info.fieldType}>
     */
    public List<${elemInfo.info.fieldType}> get${elemInfo.info.fieldName?cap_first}IdIn() {
        return ${elemInfo.info.fieldName}IdIn;
    }

    /**
      <#if locale == "es" >
     * Lista de valores del campo padre '${elemInfo.info.fieldName}' para busquedas tipo IN
     * Sólo se consideran los campos ID
      <#else>
     * Values list of parent field '${elemInfo.info.fieldName}' for IN type searching
     * Only the ID fields are considered
      </#if>
     * @return List<${elemInfo.info.fieldType}>
     */
    public void add${elemInfo.info.fieldName?cap_first}IdIn(${elemInfo.info.fieldType} ${elemInfo.info.fieldName}) {
        ${elemInfo.info.fieldName}IdIn.add(${elemInfo.info.fieldName});
    }
    </#if>
    <#if !elemInfo.isIdentifier>

    /**
      <#if locale == "es" >
     * Permite buscar cuando campo '${elemInfo.info.fieldName}' es NULL
      <#else>
     * Searches when field '${elemInfo.info.fieldName}' is NULL
      </#if>
     * @return boolean
     */
    public boolean is${elemInfo.info.fieldName?cap_first}IsNull() {
        return ${elemInfo.info.fieldName}IsNull;
    }

    /**
      <#if locale == "es" >
     * Permite buscar cuando campo '${elemInfo.info.fieldName}' es NULL
     * @param ${elemInfo.info.fieldName}IsNull valor asignado
      <#else>
     * Searches when field '${elemInfo.info.fieldName}' is NULL
     * @param ${elemInfo.info.fieldName}IsNull setter value
      </#if>
     */
    public void set${elemInfo.info.fieldName?cap_first}IsNull(boolean ${elemInfo.info.fieldName}IsNull) {
        this.${elemInfo.info.fieldName}IsNull = ${elemInfo.info.fieldName}IsNull;
    }

    /**
      <#if locale == "es" >
     * Permite buscar cuando campo '${elemInfo.info.fieldName}' es NOT NULL
      <#else>
     * Searches when field '${elemInfo.info.fieldName}' is NOT NULL
      </#if>
     * @return boolean
     */
    public boolean is${elemInfo.info.fieldName?cap_first}IsNotNull() {
        return ${elemInfo.info.fieldName}IsNotNull;
    }

    /**
      <#if locale == "es" >
     * Permite buscar cuando campo '${elemInfo.info.fieldName}' es NOT NULL
     * @param ${elemInfo.info.fieldName}IsNotNull valor asignado
      <#else>
     * Searches when field '${elemInfo.info.fieldName}' is NOT NULL
     * @param ${elemInfo.info.fieldName}IsNotNull setter value
      </#if>
     */
    public void set${elemInfo.info.fieldName?cap_first}IsNotNull(boolean ${elemInfo.info.fieldName}IsNotNull) {
        this.${elemInfo.info.fieldName}IsNotNull = ${elemInfo.info.fieldName}IsNotNull;
    }
    </#if>
  </#if>
</#list>
<#--
 Getters y setters de flag de join a campos padre o relaciones
-->
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.isManyToOne>

    /**
      <#if locale == "es" >
     * Indica si en la consulta se hace un inner join con el padre '${elemInfo.info.fieldName}'
      <#else>
     * Indicates if the query uses an inner join with the parent field '${elemInfo.info.fieldName}'
      </#if>
     */
    public boolean isInnerJoin${elemInfo.info.fieldName?cap_first}() {
        return innerJoin${elemInfo.info.fieldName?cap_first};
    }

    /**
      <#if locale == "es" >
     * Asigna si en la consulta se hace un inner join con el padre '${elemInfo.info.fieldName}'
      <#else>
     * Sets if the query uses an inner join with the parent field '${elemInfo.info.fieldName}'
      </#if>
     */
    public void setInnerJoin${elemInfo.info.fieldName?cap_first}(boolean innerJoin${elemInfo.info.fieldName?cap_first}) {
        this.innerJoin${elemInfo.info.fieldName?cap_first} = innerJoin${elemInfo.info.fieldName?cap_first};
    }

    /**
      <#if locale == "es" >
     * Indica si en la consulta se hace un left join con el padre '${elemInfo.info.fieldName}'
      <#else>
     * Indicates if the query uses an left join with the parent field '${elemInfo.info.fieldName}'
      </#if>
     */
    public boolean isLeftJoin${elemInfo.info.fieldName?cap_first}() {
        return leftJoin${elemInfo.info.fieldName?cap_first};
    }

    /**
      <#if locale == "es" >
     * Asigna si en la consulta se hace un left join con el padre '${elemInfo.info.fieldName}'
      <#else>
     * Sets if the query uses an left join with the parent field '${elemInfo.info.fieldName}'
      </#if>
     */
    public void setLeftJoin${elemInfo.info.fieldName?cap_first}(boolean leftJoin${elemInfo.info.fieldName?cap_first}) {
        this.leftJoin${elemInfo.info.fieldName?cap_first} = leftJoin${elemInfo.info.fieldName?cap_first};
    }
  </#if>
  <#if elemInfo.isManyToMany>

    /**
      <#if locale == "es" >
     * Indica si en la consulta se hace un join con el campo relacionado '${elemInfo.info.fieldName}'
      <#else>
     * Indicates if the query uses an join with the associated field '${elemInfo.info.fieldName}'
      </#if>
     */
    public boolean isJoin${elemInfo.info.fieldName?cap_first}() {
        return join${elemInfo.info.fieldName?cap_first};
    }

    /**
      <#if locale == "es" >
     * Asigna si en la consulta se hace un join con el campo relacionado '${elemInfo.info.fieldName}'
      <#else>
     * Sets if the query uses an join with the associated field '${elemInfo.info.fieldName}'
      </#if>
     */
    public void setJoin${elemInfo.info.fieldName?cap_first}(boolean join${elemInfo.info.fieldName?cap_first}) {
        this.join${elemInfo.info.fieldName?cap_first} = join${elemInfo.info.fieldName?cap_first};
    }
  </#if>
  <#if elemInfo.isOneToMany>

    /**
      <#if locale == "es" >
     * Indica si en la consulta se hace un join con el campo hijo '${elemInfo.info.fieldName}'
      <#else>
     * Indicates if the query uses an join with the children field '${elemInfo.info.fieldName}'
      </#if>
     */
    public boolean isJoin${elemInfo.info.fieldName?cap_first}() {
        return join${elemInfo.info.fieldName?cap_first};
    }

    /**
      <#if locale == "es" >
     * Asigna si en la consulta se hace un join con el campo hijo '${elemInfo.info.fieldName}'
      <#else>
     * Sets if the query uses an join with the children field '${elemInfo.info.fieldName}'
      </#if>
     */
    public void setJoin${elemInfo.info.fieldName?cap_first}(boolean join${elemInfo.info.fieldName?cap_first}) {
        this.join${elemInfo.info.fieldName?cap_first} = join${elemInfo.info.fieldName?cap_first};
    }
  </#if>

</#list>

    /**
<#if locale == "es" >
     * Valida que el nombre de los campos exista en la entidad.
<#else>
     * Validates existence of select field names
</#if>
     * @param fieldName
     */
    protected void validateFields(String... fieldNames) {
        for (int i = 0; i < fieldNames.length; i++) {
            validateField(fieldNames[i]);
        }
    }

    /**
<#if locale == "es" >
     * Valida que el nombre del campo exista en la entidad.
<#else>
     * Validates existence of select field name in entity
</#if>
     * @param fieldName
     */
    public static void validateField(String fieldName) {
        if (fieldName == null) {
            throw new PersistenceException("cannot_add_null_field");
        }
    
<#if locale == "es" >
        //Si el nombre corresponde a un campo de la entidad, es válido
<#else>
        //if name matches to an entity field, it's valid
</#if>
<#if entity.hasParents >
        if (FIELD_NAMES_SET.contains(fieldName) || PARENT_NAMES_SET.contains(fieldName)) {
            return;
        }
<#else>
        if (FIELD_NAMES_SET.contains(fieldName)) {
            return;
        }
</#if>
<#if entity.hasParents >
        if (fieldName.contains(".")) {
            String[] ar = fieldName.split("\\.");
            if (ar.length != 2) {
                throw new PersistenceException("El campo agregado no es válido: '" + fieldName + "'");
            }
            String parentName = ar[0];
            String parentField = ar[1];
            if (PARENT_NAMES_SET.contains(parentName)) {
<#list entity.modelElements as element>
  <#assign elemInfo = elementInfo(element) />
  <#if elemInfo.isPersistent && 
       elemInfo.isManyToOne>
                if (parentName.equals(${elemInfo.info.fieldName?upper_case})) {
                    ${elemInfo.info.fieldType}Query.validateField(parentField);
                    return;
                }
  </#if>
</#list>
            }
        }
</#if>
        throw new PersistenceException("cannot_add_invalid_field", new Object[]{ fieldName });
    }
</#macro>
