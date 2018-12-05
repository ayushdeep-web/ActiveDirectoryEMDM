package com.sg.loginutils.service;

import org.springframework.http.ResponseEntity;

public interface IAuthentication {
	ResponseEntity<Object> checkAuthentication();

}
