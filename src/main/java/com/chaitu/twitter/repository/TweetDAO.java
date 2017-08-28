package com.chaitu.twitter.repository;

import org.springframework.data.repository.CrudRepository;

import com.chaitu.repository.User;

public interface TweetDAO extends CrudRepository<Tweet,Long>
{
	Iterable<Tweet> findAll();
    Tweet findById(Long id);
    Iterable<Tweet> findByUserName(String userName);
}
