package com.sg.loginutils.service.impl;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.sg.loginutils.constants.Constants;
import com.sg.loginutils.model.Credentials;
import com.sg.loginutils.service.IAuthentication;

@Service
public class ActiveDirectoryFactory extends Credentials implements IAuthentication {

	private String userName;
	private String password;
	private String email;

	public ActiveDirectoryFactory(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public MultiValueMap<String, String> getMap() {
		MultiValueMap<String, String> activeDirectoryMap = new LinkedMultiValueMap<>();
		activeDirectoryMap.add("username", getUserName());
		activeDirectoryMap.add("password", getPassword());
		activeDirectoryMap.add("email", getEmail());
		return activeDirectoryMap;
	}

	@Override
	public ResponseEntity<Object> checkAuthentication() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		try {
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(getMap(), headers);
			ResponseEntity<String> response = restTemplate.postForEntity(Constants.AD_URL, request, String.class);
			return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
		}

	}

}
