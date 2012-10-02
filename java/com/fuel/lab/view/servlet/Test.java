package com.fuel.lab.view.servlet;

import java.util.ResourceBundle;

public class Test {
	public static void main(String[] args) {

		ResourceBundle bundle = ResourceBundle.getBundle("MailID");
		String fromMailID = bundle.getString("fromMailID");
		System.out.println(fromMailID);
		//System.out.println("1203    "+bundle.getStringArray("toMailID"));
		String[] list = bundle.getString("toMailID").split(",");
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
	}
}
