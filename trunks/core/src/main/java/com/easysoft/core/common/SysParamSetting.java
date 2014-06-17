package com.easysoft.core.common;

import com.easysoft.framework.utils.FileUtil;
import com.easysoft.framework.utils.StringUtil;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-10
 * Time: 下午10:42
 * To change this template use File | Settings | File Templates.
 */
public class SysParamSetting {
    public static String runmode = "2";
    static {
        try{
            InputStream in  = FileUtil.getResourceAsStream("esf.properties");
            Properties props = new Properties();
            props.load(in);
            init(props);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void init(Properties props ){
        //运行模式
        String runmodeProp = props.getProperty("runmode");
        runmode=  StringUtil.isEmpty(runmodeProp)?runmode:runmodeProp;
    }
}
