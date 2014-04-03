package com.easysoft.core.utils;

/**
 * User: andy
 * Date: 14-4-2
 * Time: 下午4:41
 *
 * @since:
 */
public class CodeResourceUtil {

    public static String FREEMARKER_CLASSPATH = "/jeap/template";
    public static String SYSTEM_ENCODING = "UTF-8";
    public static String JSPPATH;
    public static String CODEPATH;
    public static String bussiPackage = "com.easysoft.component";
    public static String JEAP_GENERATE_TABLE_ID;
    public static String JEAP_UI_FIELD_REQUIRED_NUM = "4";
    public static String JEAP_UI_FIELD_SEARCH_NUM = "3";
    public static String source_root_package = "src/main/java";
    public static String project_path = "c:/workspace/jeecg";
    static{
        String bussiPackageUrl = bussiPackage.replace(".", "/");
        CODEPATH = source_root_package + "/" + bussiPackageUrl + "/";
        JEAP_GENERATE_TABLE_ID = "id";
    }


    public static void setProject_path(String project_path)
    {
        project_path = project_path;
    }
}
