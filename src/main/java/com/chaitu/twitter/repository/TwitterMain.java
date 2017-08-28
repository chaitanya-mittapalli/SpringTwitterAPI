package com.chaitu.twitter.repository;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.chaitu.authentication.AuthenticationTwitter;
import com.chaitu.repository.UserRepository;

public class TwitterMain 
{
	
	public void loadIntoDB(TweetRepository tweetRepository) throws UnsupportedEncodingException 
	{
		String appToken=AuthenticationTwitter.getAppToken();
		List<Tweet> tweets = searchTwitter("#springframework", appToken);
		for (Tweet tweet : tweets) 
		{
			System.out.println(tweet);
			TwitterDAO twitterDAO=new TwitterDAO();
			twitterDAO.setTweetDesc(tweet.getText());
			byte[] encoding1 = tweet.getUserName().getBytes("UTF-8");
	        String string1 = new String(encoding1, "ISO8859-1");
			twitterDAO.setUserName(string1);
			byte[] encoding2 = tweet.getUserLocation().getBytes("UTF-8");
	        String string2 = new String(encoding2, "ISO8859-1");
			twitterDAO.setUserLocation(string2);
			tweetRepository.save(twitterDAO);
		}
		System.out.println("Completed loading the tweets into DB");
	}
	private static List<Tweet> searchTwitter(String query, String appToken)
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
			tweets.add(new Tweet(status.get("id").toString(), status.get("text").toString(),userMap.get("name").toString(),userMap.get("location").toString()));
		}
		return tweets;
	}

}
