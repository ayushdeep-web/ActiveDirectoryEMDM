package com.sg.loginutils;

import org.springframework.beans.factory.annotation.Autowired;

import com.sg.loginutils.service.IAuthentication;
import com.sg.loginutils.service.impl.EmdmFactory;

/**
 * Hello world!
 *
 */
public class App {
	@Autowired
	static IAuthentication iAuthentication;

	public static void main(String[] args) {
		/*
		 * iAuthentication = new ActiveDirectoryFactory("ayush.deep", "Jetjet#12345",
		 * null) { };
		 */
		iAuthentication = new EmdmFactory("appster", "appster", "xebia.test1@spicejet.com") {
		};
		System.out.println(iAuthentication.checkAuthentication().getBody());
	}
}
