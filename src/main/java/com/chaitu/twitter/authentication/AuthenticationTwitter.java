package com.chaitu.twitter.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class AuthenticationTwitter 
{
	private final TwitterCredentails twitterCredentails;

    @Autowired
	public AuthenticationTwitter(TwitterCredentails twitterCredentails)
	{
		// TODO Auto-generated constructor stub
		this.twitterCredentails=twitterCredentails;
	}
    
	public String getAppToken()
	{
		System.out.println("APP ID "+twitterCredentails.getSonsumerKey()+" App Secret "+twitterCredentails.getConsumerSecret());
		return AuthenticationTwitter.fetchApplicationAccessToken(twitterCredentails.getSonsumerKey(), twitterCredentails.getConsumerSecret());
	}
	/*private static String promptForInput(String promptText) {
		return JOptionPane.showInputDialog(promptText + " ");
	}*/
	private static String fetchApplicationAccessToken(String appId, String appSecret) {
		// Twitter supports OAuth2 *only* for obtaining an application token, not for user tokens.
		OAuth2Operations oauth = new OAuth2Template(appId, appSecret, "", "https://api.twitter.com/oauth2/token");
		return oauth.authenticateClient().getAccessToken();
	}
}
