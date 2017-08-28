package com.chaitu.twitter.repository;

import java.io.UnsupportedEncodingException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public final class Tweet 
{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String text;
    
    private String userName;
    
    private String userLocation;
    
    public Tweet() {
		// TODO Auto-generated constructor stub
	}
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) throws UnsupportedEncodingException 
	{
		 byte[] encoding1 = userName.getBytes("UTF-8");
		this.userName = new String(encoding1, "ISO8859-1");;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) throws UnsupportedEncodingException
	{
		 byte[] encoding1 = userLocation.getBytes("UTF-8");
		this.userLocation = new String(encoding1, "ISO8859-1");
	}

	public Tweet(Long id, String text,String name,String location) 
	{
        this.id = id;
        this.text = text;
        this.userName=name;
        this.userLocation=location;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
        return text;
    }
    @Override
    public String toString()
    {
    	// TODO Auto-generated method stub
    	return "ID :"+this.id+" | Tweet :"+this.text+" | UserName :"+this.userName+" | UserLocation :"+this.userLocation;
    }
}
