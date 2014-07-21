package com.easysoft.core.code;

import com.easysoft.core.code.pojo.CreateFileProperty;
import com.easysoft.core.code.pojo.GenerateEntity;
import com.easysoft.core.code.support.CodeGenerator;
import com.easysoft.core.model.FormEntity;
import com.easysoft.core.model.FormField;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: andy
 * Date: 14-4-2
 * Time: 下午5:07
 *
 * @since:
 */
public class CodeGeneratorTest {
    @Test
    public void generate(){

        CreateFileProperty fileProperty = new CreateFileProperty();
        FormEntity formEntity = new FormEntity();
        FormField field = new FormField();
        field.setFieldName("name");
        field.setType("String");
        List<FormField> fields = new ArrayList<FormField>();
        fields.add(field);
        formEntity.setFields(fields);
        GenerateEntity generateEntity = new  GenerateEntity();
        generateEntity.setProjectPath("D:/");
        generateEntity.setPackageName("test");
        generateEntity.setEntityName("ProvideLoanInfo");
        fileProperty.setServiceFlag(true);
        generateEntity.setFormEntity(formEntity);
        CodeGenerator generator = new CodeGenerator(fileProperty,generateEntity);
        try {
            generator.generateToFile();
        } catch (TemplateException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}