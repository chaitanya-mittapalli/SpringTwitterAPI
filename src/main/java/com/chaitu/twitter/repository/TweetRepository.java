package com.chaitu.twitter.repository;

import org.springframework.data.repository.CrudRepository;

import com.chaitu.repository.User;

public interface TweetRepository extends CrudRepository<TwitterDAO,Integer>
{
}
