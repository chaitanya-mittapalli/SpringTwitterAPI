package com.chaitu.twitter.repository;


public final class Tweet 
{
    private String id;

    private String text;
    
    private String userName;
    
    private String userLocation;
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public Tweet(String id, String text,String name,String location) 
	{
        this.id = id;
        this.text = text;
        this.userName=name;
        this.userLocation=location;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
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
