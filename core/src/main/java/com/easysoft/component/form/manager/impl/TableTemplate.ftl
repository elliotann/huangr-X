<?xml version="1.0" encoding="UTF-8"?>
<dbsolution>
    <action>
        <command>create</command>
        <table>${formEntity.tableName}</table>
    <#list formEntity.fields as field>
        <field><name>${field.fieldName}</name><type>${field.dataType}</type><size>${field.dataTypeLength}</size><option><#if field.ispk>11<#elseif field.nullable>01<#else>00</#if></option></field>
    </#list>
    </action>
</dbsolution>