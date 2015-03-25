package com.easysoft.component.weixin.vo;

import java.util.ArrayList;
import java.util.List;


public class ComplexButton  extends Button{
	private List<Button> sub_button;

	
	
	public List<Button> getSub_button() {
		return sub_button;
	}



	public void setSub_button(List<Button> sub_button) {
		this.sub_button = sub_button;
	}



	public void addButton(Button button){
		if(sub_button==null){
			sub_button = new ArrayList<Button>();
		}
		sub_button.add(button);
	}
	
}
