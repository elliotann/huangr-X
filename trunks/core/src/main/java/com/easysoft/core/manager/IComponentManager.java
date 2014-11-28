package com.easysoft.core.manager;

import com.easysoft.framework.component.ComponentView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: huangxa
 * Date: 13-6-4
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public interface IComponentManager{

    public  void startComponents();

    public  void saasStartComponents();

    public  List<ComponentView> list();

    public  void install(String componentid);

    public  void unInstall(String componentid);

    public  void start(String componentid);

    public  void stop(String componentid);
}
