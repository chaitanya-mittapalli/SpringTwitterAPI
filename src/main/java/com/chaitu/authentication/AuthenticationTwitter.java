package com.chaitu.authentication;

import javax.swing.JOptionPane;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

public class AuthenticationTwitter 
{
	public static String getAppToken()
	{
		String appId =<AppID Here>;
		String appSecret = <Client Secret here>;
		return AuthenticationTwitter.fetchApplicationAccessToken(appId, appSecret);
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
