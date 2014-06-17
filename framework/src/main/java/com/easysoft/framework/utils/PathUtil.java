package com.easysoft.framework.utils;

/**
 * 路径工具类
 * User: andy
 * Date: 13-9-4
 * Time: 下午4:07
 *
 * @since: 1.0
 */
public class PathUtil {
    /**
     * 得到根目录绝对路径(不包含WEB-INF)
     * @return
     */
    public static String getRootPath(){
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if(filePath.toLowerCase().startsWith("file:")){
            filePath = filePath.substring(6);
        }
        if(filePath.toLowerCase().indexOf("classes")>-1){
            filePath = filePath.replaceAll("/classes","");
        }
        if(filePath.toLowerCase().indexOf("web-inf")>-1){
            filePath = filePath.substring(0,filePath.length()-9);
        }
        return filePath;
    }
    public static String getRootPath(String resource) {
        String filePath = Thread.currentThread().getContextClassLoader()
                .getResource(resource).toString();
        if (filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }
        if (!filePath.endsWith("/"))
            filePath += "/";

        return filePath;
    }

}
