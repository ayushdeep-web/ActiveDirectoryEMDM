package com.sg.loginutils.model;

import org.springframework.util.MultiValueMap;

public abstract class Credentials {

	public abstract String getUserName();

	public abstract String getPassword();

	public abstract String getEmail();
	
	public abstract MultiValueMap<String, String> getMap();

}
