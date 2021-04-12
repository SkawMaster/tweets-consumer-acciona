package com.acciona.tweetsconsumer.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.acciona.tweetsconsumer.rest.generated.api.TweetsApi;
import com.acciona.tweetsconsumer.rest.generated.model.TweetDto;
import com.acciona.tweetsconsumer.rest.generated.model.TweetValidado;
import com.acciona.tweetsconsumer.rest.mapper.TweetsconsumerMapper;
import com.acciona.tweetsconsumer.rest.model.Tweet;
import com.acciona.tweetsconsumer.rest.repository.TweetH2Repository;

@Controller
public class TweetsController implements TweetsApi {

	@Autowired
	TweetH2Repository repository;

	@Autowired
	TweetsconsumerMapper mapper;

	public ResponseEntity<List<TweetDto>> obtenerTweets(String usuario, Boolean validado) {
		ResponseEntity<List<TweetDto>> respuestaServicio;

		List<TweetDto> listaTweetsDto = new ArrayList<TweetDto>();

		Optional<List<Tweet>> listaTweetsOptional;
		
		if (usuario == null && validado == null) {
			listaTweetsOptional = Optional.ofNullable(repository.findAll());
			
			TweetDto nuevoTweet;
			List<Tweet> listaTweets = listaTweetsOptional.get();
			for (Tweet tweet : listaTweets) {
				nuevoTweet = mapper.tweetToTweetDto(tweet);
				listaTweetsDto.add(nuevoTweet);
			}
		}
		else {
			if (usuario != null && validado != null)
			{
				listaTweetsOptional = repository.findByUsuarioAndValidacion(usuario, validado);
				
				TweetDto nuevoTweet;
				List<Tweet> listaTweets = listaTweetsOptional.get();
				for (Tweet tweet : listaTweets) {
					nuevoTweet = mapper.tweetToTweetDto(tweet);
					listaTweetsDto.add(nuevoTweet);
				}
			}
		}
			
		respuestaServicio = new ResponseEntity<List<TweetDto>>(listaTweetsDto, HttpStatus.OK);
		return respuestaServicio;
	}

	public ResponseEntity<Void> validarTweet(Integer id, TweetValidado validacion) {
		
		Optional<Tweet> tweetRecuperado = repository.findById(id);
		
		if (tweetRecuperado.isPresent())
		{
			Tweet tweetAValidar = tweetRecuperado.get();
			tweetAValidar.setValidacion(validacion.isValidacion());
			
			repository.save(tweetAValidar);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
