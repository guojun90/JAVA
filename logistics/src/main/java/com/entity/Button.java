package com.entity;
/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月18日 下午9:50:02
* 类说明
*/

import java.util.ArrayList;
import java.util.List;

public class Button {

	private List<AbstractButton> button = new ArrayList<AbstractButton>();

	public List<AbstractButton> getButton() {
		return button;
	}

	public void setButton(List<AbstractButton> button) {
		this.button = button;
	}

}
