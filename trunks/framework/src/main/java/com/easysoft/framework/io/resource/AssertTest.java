package com.easysoft.framework.io.resource;

/**
 * Created by huangxa on 2014/6/18.
 */
public class AssertTest {
    public static void main(String[] args) {
        String locationPattern= "classpath*:org/mangocube/corenut/pf/**/ResultProcessorFactory.properties";
        assert (locationPattern == null);
    }
}
