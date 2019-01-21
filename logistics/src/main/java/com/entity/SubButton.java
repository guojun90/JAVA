package com.entity;
/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月18日 下午9:52:49
* 类说明
*/

import java.util.ArrayList;
import java.util.List;

public class SubButton extends AbstractButton{

	private List<AbstractButton> sub_button = new ArrayList<AbstractButton>();

	public List<AbstractButton> getSub_button() {
		return sub_button;
	}

	public SubButton(String name) {
		super(name);
	}
}
