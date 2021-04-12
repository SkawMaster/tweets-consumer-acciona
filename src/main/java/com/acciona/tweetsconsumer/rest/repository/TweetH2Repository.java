package com.acciona.tweetsconsumer.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acciona.tweetsconsumer.rest.model.Tweet;

@Repository
public interface TweetH2Repository extends CrudRepository<Tweet, Integer> {

	Optional<Tweet> findById(Integer id);
	
	List<Tweet> findAll();

	Optional<List<Tweet>> findByUsuarioAndValidacion(String usuario, Boolean validacion);
	
	@SuppressWarnings("unchecked")
	Tweet save(Tweet tweet);
}
