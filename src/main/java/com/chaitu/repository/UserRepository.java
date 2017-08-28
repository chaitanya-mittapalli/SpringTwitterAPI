package com.chaitu.repository;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer>
{
	public Iterable<User> findUsersByName(String name);
}
