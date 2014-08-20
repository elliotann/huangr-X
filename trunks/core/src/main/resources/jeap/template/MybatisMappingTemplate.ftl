<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${bussiPackage}.${entityPackage}.dao.I${entityName}Dao">
    <resultMap id="${entityName?uncap_first}Map" type="${entityName}">
    <#list columns as po>
        <id property="${po.fieldName}" column="${fieldMeta[po.fieldName]}"/>
    </#list>
    </resultMap>
    <insert id="save" parameterType="${entityName}" useGeneratedKeys="true" keyProperty="id">
        insert ${tableName} (
            <#list fieldMeta?keys as columnKey>
                ${fieldMeta[columnKey]},
            </#list>
        )
        values (
            <#list columns as po>
                ${r"#{"+po.fieldName+"}"}<#if po_has_next>,</#if>
            </#list>
        )
    </insert>
    <select id="queryForList" resultMap="${entityName?uncap_first}Map">
        select * from ${tableName}
    </select>

</mapper>
