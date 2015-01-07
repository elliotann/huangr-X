package com.es.jeap.core.component.log.context;


/**
 * Created by Administrator on 2014/9/17.
 */
public class BnLogContext {


    public static String getSystem() {
        return "jeap";
    }

    public static void setSystem(String system) {
        system = system;
    }

    public static String getOperator() {
        return "admin";//UserServiceFactory.getUserService().getCurrentUser().getUsername();

    }

    public static void setOperator(String operator) {
        operator = operator;
    }
}
