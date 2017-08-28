package com.chaitu.twitter.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:foo.properties")
public class TwitterCredentails 
{
	 	@Value("${twitter.consumer.key}")
	    public String sonsumerKey;

	    @Value("${twitter.consumer.secret}")
	    public String consumerSecret ;

		public String getSonsumerKey() {
			return sonsumerKey;
		}

		public void setSonsumerKey(String sonsumerKey) {
			this.sonsumerKey = sonsumerKey;
		}

		public String getConsumerSecret() {
			return consumerSecret;
		}

		public void setConsumerSecret(String consumerSecret) {
			this.consumerSecret = consumerSecret;
		}
	    
}
