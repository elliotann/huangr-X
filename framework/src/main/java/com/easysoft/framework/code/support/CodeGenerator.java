package com.easysoft.framework.code.support;

import com.easysoft.framework.code.ICallBack;
import com.easysoft.framework.code.pojo.CreateFileProperty;

import java.util.Map;

/**
 * User: andy
 * Date: 14-4-2
 * Time: 下午4:25
 *
 * @since:
 */
public class CodeGenerator implements ICallBack {
    public CodeGenerator(){
    }

    public CodeGenerator(CreateFileProperty createFileProperty2, GenerateEntity generateEntity){
        this.entityName = generateEntity.getEntityName();
        this.entityPackage = generateEntity.getEntityPackage();
        this.tableName = generateEntity.getTableName();
        this.ftlDescription = generateEntity.getFtlDescription();
        FIELD_ROW_NUM = 1;
        createFileProperty = createFileProperty2;
        createFileProperty.setJspMode("01");
        this.primaryKeyPolicy = generateEntity.getPrimaryKeyPolicy();
        this.sequenceCode = "";
        this.cgformConfig = generateEntity;
    }
    @Override
    public Map<String, Object> execute(){
        return null;
    }
}
