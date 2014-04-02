package com.easysoft.core.code.support.factory;

import com.easysoft.core.code.ICallBack;
import com.easysoft.core.utils.CodeResourceUtil;
import com.easysoft.framework.utils.StringUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: andy
 * Date: 14-4-2
 * Time: 下午4:39
 *
 * @since:
 */
public class CodeFactory extends BaseCodeFactory{
    public static enum CodeType
    {
        serviceImpl("ServiceImpl"),
        service("ServiceI"),
        controller("Controller"),
        entity("Entity"),
        jsp(""),
        jsp_add(""),
        jsp_update(""),
        jspList("List"),
        js(""),
        jsList("List");

        private String type;

        private CodeType(String type) { this.type = type; }

        public String getValue()
        {
            return this.type;
        }
    }
    private String projectPath;
    private ICallBack callBack;
    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public ICallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }
    public void invoke(String templateFileName, String type) throws TemplateException, IOException {
        Map data = new HashMap();
        data = this.callBack.execute();
        generateFile(templateFileName, type, data);
    }
    public void generateFile(String templateFileName, String type, Map data)
            throws TemplateException, IOException
    {
        try
        {
            String entityPackage = data.get("entityPackage").toString();
            String entityName = data.get("entityName").toString();
            String fileNamePath = getCodePath(type, entityPackage, entityName);
            String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
            Template template = getConfiguration().getTemplate(templateFileName);
            FileUtils.forceMkdir(new File(fileDir + "/"));
            Writer out = new OutputStreamWriter(
                    new FileOutputStream(fileNamePath), CodeResourceUtil.SYSTEM_ENCODING);
            template.process(data, out);
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public String getCodePath(String type, String entityPackage, String entityName)
    {
        String path = getProjectPath();
        StringBuilder str = new StringBuilder();
        if (StringUtils.isNotBlank(type)) {
            String codeType = ((CodeType)Enum.valueOf(CodeType.class, type)).getValue();
            str.append(path);
            if (("jsp".equals(type)) || ("jspList".equals(type)) || ("js".equals(type)) || ("jsList".equals(type)) ||
                    ("jsp_add".equals(type)) || ("jsp_update".equals(type)))
                str.append(CodeResourceUtil.JSPPATH);
            else {
                str.append(CodeResourceUtil.CODEPATH);
            }
            if ("Action".equalsIgnoreCase(codeType))
                str.append(StringUtils.lowerCase("action"));
            else if ("ServiceImpl".equalsIgnoreCase(codeType))
                str.append(StringUtils.lowerCase("service/impl"));
            else if ("ServiceI".equalsIgnoreCase(codeType))
                str.append(StringUtils.lowerCase("service"));
            else if (!"List".equalsIgnoreCase(codeType))
            {
                str.append(StringUtils.lowerCase(codeType));
            }
            str.append("/");
            str.append(StringUtils.lowerCase(entityPackage));
            str.append("/");

            if (("jsp".equals(type)) || ("jspList".equals(type))) {
                String jspName = StringUtils.capitalize(entityName);

                str.append(StringUtil.firstLowCase(jspName));
                str.append(codeType);
                str.append(".jsp");
            } else if (("jsp_add".equals(type)) || ("jspList_add".equals(type))) {
                String jsName = StringUtils.capitalize(entityName);

                str.append(StringUtil.firstLowCase(jsName));
                str.append(codeType);
                str.append("-add.jsp");
            } else if (("jsp_update".equals(type)) || ("jspList_update".equals(type))) {
                String jsName = StringUtils.capitalize(entityName);

                str.append(StringUtil.firstLowCase(jsName));
                str.append(codeType);
                str.append("-update.jsp");
            } else if (("js".equals(type)) || ("jsList".equals(type))) {
                String jsName = StringUtils.capitalize(entityName);

                str.append(StringUtil.firstLowCase(jsName));
                str.append(codeType);
                str.append(".js");
            } else {
                str.append(StringUtils.capitalize(entityName));
                str.append(codeType);
                str.append(".java");
            }
        } else {
            throw new IllegalArgumentException("type is null");
        }
        return str.toString();
    }

    public String getProjectPath() {
        return projectPath;
    }
}
