package com.easysoft.core.code;

import com.easysoft.core.code.pojo.CreateFileProperty;
import com.easysoft.core.code.pojo.GenerateEntity;
import com.easysoft.core.code.support.CodeGenerator;
import com.easysoft.core.model.FormEntity;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;

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
        GenerateEntity generateEntity = new  GenerateEntity();
        generateEntity.setProjectPath("D:/");
        fileProperty.setServiceImplFlag(true);
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
