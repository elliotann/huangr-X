package org.jwebap.cfg.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Jwebap����
 * 
 * ����plugin�����ã�ͬʱ���ں���һ�������plugin���ã�������jwebap.xml�����е����ÿ��Ը��Ǹ�plugin��Ӧ�����á�
 * �����ڽ��沿��ʱ�������plugin����ʱ���á�
 * 
 * @author leadyu(yu-lead@163.com)
 * @since Jwebap 0.5
 * @date 2008-4-6
 */
public class JwebapDef implements Cloneable{

	/**
	 * �ڲ����ö���
	 */
	private PluginDef _internalDef = null;

	/**
	 * plugin��������
	 * 
	 * @see PluginDefRef
	 */
	private List _plugins = null;


	public JwebapDef() {
		_internalDef = new PluginDef();
		_plugins=new ArrayList();
	}

	public JwebapDef(PluginDef internalDef,List plugins) {
		_internalDef = internalDef;
		_plugins=plugins;
	}

	public void addPluginDef(PluginDefRef plugin) {
		_plugins.add(plugin);
	}

	public PluginDefRef getPluginDef(String name) {
		for(int i=0;i<_plugins.size();i++){
			PluginDefRef ref=(PluginDefRef)_plugins.get(i);
			if(ref.getName()!=null && ref.getName().equals(name)){
				return ref;
			}
		}
		return null;
	}
	
	/**
	 * �Ƴ���
	 * @param name
	 * @return
	 */
	public PluginDefRef removePluginDef(String name) {
		for(int i=0;i<_plugins.size();i++){
			PluginDefRef ref=(PluginDefRef)_plugins.get(i);
			if(ref.getName()!=null && ref.getName().equals(name)){
				_plugins.remove(ref);
			}
		}
		return null;
	}
	
	public Collection getPluginDefs(){
		return _plugins;
	}
	
	/**
	 * ����Component����
	 * 
	 * ��Ӧjwebap.xml��Component���壬�����ȼ�����plugin��Component����
	 * 
	 * @param name
	 * @param component
	 */
	public void addComponentDef(ComponentDef component) {
		_internalDef.addComponentDef(component);
	}

	/**
	 * ���Component����
	 * 
	 * jwebap.xml�����Component���ȼ�����plugin�е�Component����
	 * 
	 * @param name
	 * @return
	 */
	public ComponentDef getComponentDef(String name) {

		ComponentDef def = _internalDef.getComponentDef(name);
		if (def != null) {
			return def;
		}
		for (int i = 0; i < _plugins.size(); i++) {
			PluginDefRef pluginDef = (PluginDefRef)_plugins.get(i);
			if(pluginDef==null){
				continue;
			}
			def = pluginDef.getComponentDef(name);
			if (pluginDef != null)
				return def;

		}
		return def;
	}

	
	/**
	 * ��ȡcomponent�������Ը���
	 * @param name
	 * @return
	 */
	public ComponentDef getComponentDefForUpdate(String name) {

		ComponentDef def = getComponentDef(name);
		ComponentDef internalComponentDef = _internalDef.getComponentDef(name);
		//component��������ѱ����¹���ô���pluginDef���Ƶ�internalDef
		if (internalComponentDef == null) {
			addComponentDef(def);
		}
		return def;

	}
	
	/**
	 * �õ�������壬������jwebap.xml
	 * @return
	 */
	public Collection getComponentDefs() {
		return _internalDef.getComponentDefs();
	}
	
	/**
	 * ��������������
	 * @return
	 */
	public Collection getAllComponentDefs() {
		List components=new ArrayList();
		//JwebapDef�е�����������ȼ����
		components.addAll(_internalDef.getComponentDefs());
		
		//��ȡ���в��
		Collection plugins=getPluginDefs();
		Iterator pluginIt=plugins.iterator();
		while(pluginIt.hasNext()){
			PluginDefRef ref =(PluginDefRef)pluginIt.next();
			Collection pluginCps=ref.getComponentDefs();
			Iterator pCpsIt=pluginCps.iterator();
			while(pCpsIt.hasNext()){
				ComponentDef def=(ComponentDef)pCpsIt.next();
				if(!components.contains(def)){
					components.add(def);
				}
			}
		}
		
		return components;
	}
	
	public void addDispatcherDef(DispatcherDef dispatcher) {
		_internalDef.addDispatcherDef(dispatcher);
	}

	/**
	 * ���Dispatcher����
	 * 
	 * jwebap.xml�����Dispatcher���ȼ�����plugin�е�Dispatcher����
	 * 
	 * @param name
	 * @return
	 */
	public DispatcherDef getDispatcherDef(String name) {
		DispatcherDef def = _internalDef.getDispatcherDef(name);
		if (def != null) {
			return def;
		}
		for (int i = 0; i < _plugins.size(); i++) {
			PluginDefRef pluginDef = (PluginDefRef)_plugins.get(i);
			def = pluginDef.getDispatcherDef(name);
			if (pluginDef != null)
				return def;

		}
		return def;
	}

	/**
	 * �õ�dispatcher���壬������jwebap.xml
	 * @return
	 */
	public Collection getDispatcherDefs() {
		return _internalDef.getDispatcherDefs();
	}
	
	/**
	 * �������Dispatcher����
	 * @return
	 */
	public Collection getAllDispatcherDefs() {
		List dispatchers=new ArrayList();
		//JwebapDef�е�����������ȼ����
		dispatchers.addAll(_internalDef.getDispatcherDefs());
		
		//��ȡ���в��
		Collection plugins=getPluginDefs();
		Iterator pluginIt=plugins.iterator();
		
		while(pluginIt.hasNext()){
			PluginDefRef ref =(PluginDefRef)pluginIt.next();
			Collection pluginCps=ref.getDispatcherDefs();
			Iterator pCpsIt=pluginCps.iterator();
			while(pCpsIt.hasNext()){
				DispatcherDef def=(DispatcherDef)pCpsIt.next();
				if(!dispatchers.contains(def)){
					dispatchers.add(def);
				}
			}
		}
		
		return dispatchers;
	}
	
	public void addActionDef(ActionDef action) {
		_internalDef.addActionDef(action);
	}

	/**
	 * ���Action����
	 * 
	 * jwebap.xml�����Action���ȼ�����plugin�е�Action����
	 * 
	 * @param name
	 * @return
	 */
	public ActionDef getActionDef(String name) {
		ActionDef def = _internalDef.getActionDef(name);
		if (def != null) {
			return def;
		}
		for (int i = 0; i < _plugins.size(); i++) {
			PluginDefRef pluginDef = (PluginDefRef)_plugins.get(i);
			def = pluginDef.getActionDef(name);
			if (pluginDef != null)
				return def;

		}
		return def;
	}
	
	/**
	 * �õ�action���壬������jwebap.xml
	 * @return
	 */
	public Collection getActionDefs() {
		return _internalDef.getActionDefs();
	}
	
	public Object clone(){
		JwebapDef defCopy=new JwebapDef();
		
		return defCopy;
	}
}
