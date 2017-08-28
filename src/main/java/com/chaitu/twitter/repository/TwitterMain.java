package com.chaitu.twitter.repository;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.chaitu.repository.UserRepository;
import com.chaitu.twitter.authentication.AuthenticationTwitter;

@Component
public class TwitterMain 
{
	private  AuthenticationTwitter authenticationTwitter;
	
	@Autowired
	public void setAuthenticationTwitter(AuthenticationTwitter authenticationTwitter)
	{
		this.authenticationTwitter=authenticationTwitter;
	}
	public void loadIntoDB(TweetDAO tweetDAO) throws UnsupportedEncodingException 
	{
		String appToken=authenticationTwitter.getAppToken();
		List<Tweet> tweets = searchTwitter("#springframework", appToken);
		for (Tweet tweet : tweets) 
		{
			tweetDAO.save(tweet);
		}
		System.out.println("Completed loading the tweets into DB");
	}
	private static List<Tweet> searchTwitter(String query, String appToken) throws UnsupportedEncodingException
	{
		// Twitter supports OAuth2 *only* for obtaining an application token, not for user tokens.
		// Using application token for search so that we don't have to go through hassle of getting a user token.
		// This is not (yet) supported by Spring Social, so we must construct the request ourselves.
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + appToken);
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		Map<String, ?> result = rest.exchange("https://api.twitter.com/1.1/search/tweets.json?q={query}", HttpMethod.GET, requestEntity, Map.class, query).getBody();
		List<Map<String, ?>> statuses = (List<Map<String, ?>>) result.get("statuses");
		List<Tweet> tweets = new ArrayList<Tweet>();
		for (Map<String, ?> status : statuses) 
		{
			//System.out.println(status);
			Map<String, ?> userMap=(Map<String, ?>) status.get("user");
			Tweet tweet=new Tweet();
			tweet.setText(status.get("text").toString());
			tweet.setUserName(userMap.get("name").toString());
			tweet.setUserLocation(userMap.get("location").toString());
			tweets.add(tweet);
		}
		return tweets;
	}

}
