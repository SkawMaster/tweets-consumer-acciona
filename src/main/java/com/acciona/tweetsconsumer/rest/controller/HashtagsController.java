package com.acciona.tweetsconsumer.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.acciona.tweetsconsumer.rest.generated.api.HashtagsApi;
import com.acciona.tweetsconsumer.rest.generated.model.Hashtag;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Controller
public class HashtagsController implements HashtagsApi {

	public ResponseEntity<List<Hashtag>> obtenerHashtagsMasUsados(Integer top) {

		List<Hashtag> listaHastags = new ArrayList<Hashtag>();
		Hashtag hashtagNuevo;
		ResponseEntity<List<Hashtag>> respuestaServicio;

		// The factory instance is re-useable and thread safe.
		Twitter twitter = TwitterFactory.getSingleton();

		int world = 1;
		Trends result;
		try {
			result = twitter.getPlaceTrends(world);

			for (Trend trend : result.getTrends()) {

				hashtagNuevo = new Hashtag();
				hashtagNuevo.setHashtag(trend.getName());
				listaHastags.add(hashtagNuevo);
			}

			if (top > listaHastags.size())
				top = listaHastags.size();

			listaHastags = listaHastags.subList(0, top);

			respuestaServicio = new ResponseEntity<List<Hashtag>>(listaHastags, HttpStatus.OK);

		} catch (TwitterException e) {
			hashtagNuevo = new Hashtag();
			hashtagNuevo.setHashtag(e.getErrorMessage());
			listaHastags.add(hashtagNuevo);
			respuestaServicio = new ResponseEntity<List<Hashtag>>(listaHastags, HttpStatus.valueOf(e.getErrorCode()));
		}

		return respuestaServicio;
	}

}
