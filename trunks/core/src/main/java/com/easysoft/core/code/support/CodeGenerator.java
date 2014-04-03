package com.easysoft.core.code.support;


import com.easysoft.core.code.ICallBack;
import com.easysoft.core.code.pojo.CreateFileProperty;
import com.easysoft.core.code.pojo.GenerateEntity;
import com.easysoft.core.code.support.factory.CodeFactory;
import com.easysoft.core.model.FormField;
import com.easysoft.core.utils.CodeResourceUtil;
import com.easysoft.core.utils.FtlDef;
import com.easysoft.framework.utils.DateUtil;
import com.easysoft.framework.utils.NonceUtils;
import com.easysoft.framework.utils.StringUtil;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.*;

/**
 * User: andy
 * Date: 14-4-2
 * Time: 下午4:25
 *
 * @since:
 */
public class CodeGenerator implements ICallBack {
    private static final Log logger = LogFactory.getLog(CodeGenerator.class);
    private String entityPackage = "test";
    private String entityName = "Person";
    private String tableName = "person";
    private String tableTitle = "公告";
    public static int FIELD_ROW_NUM = 1;
    private String primaryKeyPolicy = "uuid";
    private String sequenceCode = "";
    private GenerateEntity cgformConfig;
    private String[] foreignKeys;
    private static CreateFileProperty createFileProperty = new CreateFileProperty();
    public CodeGenerator(){
    }

    public CodeGenerator(CreateFileProperty createFileProperty2, GenerateEntity generateEntity){
        this.entityName = generateEntity.getEntityName();
        this.entityPackage = generateEntity.getPackageName();
        this.tableName = generateEntity.getTableName();
        this.tableTitle = generateEntity.getTableTitle();
        FIELD_ROW_NUM = 1;
        createFileProperty = createFileProperty2;
        createFileProperty.setJspMode("01");
        this.primaryKeyPolicy = generateEntity.getPrimaryKeyPolicy();
        this.sequenceCode = "";
        this.cgformConfig = generateEntity;
    }
    public void generateToFile() throws TemplateException, IOException {
        logger.info("----jeap---Code----Generation----[单表模型:" + this.tableName + "]------- 生成中。。。");
        CodeFactory codeFactory = new CodeFactory();
        codeFactory.setProjectPath(this.cgformConfig.getProjectPath());
        if (this.cgformConfig.getFormEntity().getFormType() == 1)
            codeFactory.setCallBack(new CodeGenerator(createFileProperty, this.cgformConfig));
        else {
            //codeFactory.setCallBack(new CodeGenerator(this.sub, this.subG, this.subFileProperty, "uuid", this.foreignKeys));
        }

        if (createFileProperty.isJspFlag()) {
            if ("03".equals(createFileProperty.getJspMode())) {
                codeFactory.invoke("onetomany/cgform_jspSubTemplate.ftl", "jspList");
            } else {
                if ("01".equals(createFileProperty.getJspMode())) {
                    codeFactory.invoke("JspTableTemplate_add.ftl", "jsp_add");
                    codeFactory.invoke("JspTableTemplate_update.ftl", "jsp_update");
                }
                if ("02".equals(createFileProperty.getJspMode())) {
                    codeFactory.invoke("JspTableTemplate_add.ftl", "jsp_add");
                    codeFactory.invoke("cgform_jspDivTemplate_update.ftl", "jsp_update");
                }
                codeFactory.invoke("cgform_jspListTemplate.ftl", "jspList");
                codeFactory.invoke("cgform_jsListEnhanceTemplate.ftl", "jsList");
                codeFactory.invoke("cgform_jsEnhanceTemplate.ftl", "js");
            }
        }

        if (createFileProperty.isServiceFlag()) {
            codeFactory.invoke("ServiceITemplate.ftl", "service");
            codeFactory.invoke("ServiceImplTemplate.ftl", "serviceImpl");
        }
        if (createFileProperty.isActionFlag()) {
            codeFactory.invoke("ControllerTemplate.ftl", "controller");
        }
        if (createFileProperty.isEntityFlag())
        {
            codeFactory.invoke("EntityTemplate.ftl", "entity");
        }
        logger.info("----jeecg----Code----Generation-----[单表模型：" + this.tableName + "]------ 生成完成。。。");
    }
    @Override
    public Map<String, Object> execute(){
        Map data = new HashMap();
        Map fieldMeta = new HashMap();

        data.put("bussiPackage", CodeResourceUtil.bussiPackage);

        data.put("entityPackage", this.entityPackage);

        data.put("entityName", this.entityName);

        data.put("tableName", this.tableName);

        data.put("ftl_description", this.tableTitle);

        data.put(FtlDef.JEECG_TABLE_ID, CodeResourceUtil.JEAP_GENERATE_TABLE_ID);

        data.put(FtlDef.JEECG_PRIMARY_KEY_POLICY, this.primaryKeyPolicy);
        data.put(FtlDef.JEECG_SEQUENCE_CODE, this.sequenceCode);

        data.put("ftl_create_time", DateUtil.toString(new Date(),null));

        data.put("foreignKeys", this.foreignKeys);

        data.put(FtlDef.FIELD_REQUIRED_NAME, Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtil.JEAP_UI_FIELD_REQUIRED_NUM) ? Integer.parseInt(CodeResourceUtil.JEAP_UI_FIELD_REQUIRED_NUM) : -1));

        data.put(FtlDef.SEARCH_FIELD_NUM, Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtil.JEAP_UI_FIELD_SEARCH_NUM) ? Integer.parseInt(CodeResourceUtil.JEAP_UI_FIELD_SEARCH_NUM) : -1));

        data.put(FtlDef.FIELD_ROW_NAME, Integer.valueOf(FIELD_ROW_NUM));
        try {
            List<FormField> columns = this.cgformConfig.deepCopy().getFormEntity().getFields();
            String type;
            for (FormField cf : columns) {
                type = cf.getDataType();
                if ("string".equalsIgnoreCase(type)||"VARCHAR".equalsIgnoreCase(type))
                    cf.setType("java.lang.String");
                else if ("Date".equalsIgnoreCase(type))
                    cf.setType("java.util.Date");
                else if ("double".equalsIgnoreCase(type))
                    cf.setType("java.lang.Double");
                else if ("int".equalsIgnoreCase(type))
                    cf.setType("java.lang.Integer");
                else if ("BigDecimal".equalsIgnoreCase(type))
                    cf.setType("java.math.BigDecimal");
                else if ("Text".equalsIgnoreCase(type))
                    cf.setType("javax.xml.soap.Text");
                else if ("Blob".equalsIgnoreCase(type)) {
                    cf.setType("java.sql.Blob");
                }
                String fieldName = cf.getFieldName();
                String fieldNameV = StringUtil.formatDBName(fieldName);
                cf.setFieldName(fieldNameV);
                fieldMeta.put(fieldNameV, fieldName.toUpperCase());
            }
            List pageColumns = new ArrayList();
            for (FormField cf : columns) {
                if (cf.isInform()) {
                    pageColumns.add(cf);
                }
            }

            data.put("cgformConfig", this.cgformConfig);
            data.put("fieldMeta", fieldMeta);
            data.put("columns", columns);
            data.put("pageColumns", pageColumns);
           // data.put("buttons", this.cgformConfig.getButtons() == null ? new ArrayList(0) : this.cgformConfig.getButtons());
            data.put("buttonSqlMap", this.cgformConfig.getButtonSqlMap() == null ? new HashMap(0) : this.cgformConfig.getButtonSqlMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long serialVersionUID = NonceUtils.randomLong() +
                NonceUtils.currentMills();
        data.put("serialVersionUID", String.valueOf(serialVersionUID));
        return data;
    }

}
