package com.weddingorganizer.security;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.weddingorganizer.models.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4776159083190782880L;
	private String appUrl;
	private Locale locale;
	private User user;
	
	public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
		super(user);
		
		this.user = user;
		this.locale = locale;
		this.appUrl = appUrl;
	}

}
