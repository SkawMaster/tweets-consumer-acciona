package com.acciona.tweetsconsumer.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.acciona.tweetsconsumer.rest.model.Tweet;
import com.acciona.tweetsconsumer.rest.repository.TweetH2Repository;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@EnableJpaRepositories("com.acciona.tweetsconsumer.rest")
@SpringBootApplication(scanBasePackages = { "com.acciona.tweetsconsumer" })
public class TweetconsumerApplication {
	
	@Autowired
	TweetH2Repository repository; 

	public static void main(String[] args) {
		SpringApplication.run(TweetconsumerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void cargarH2ConTweets() {
		// The factory instance is re-useable and thread safe.
		Twitter twitter = TwitterFactory.getSingleton();
		
		int contador = 0;
		
		try {
			Query query = new Query("lang:es");
            
//            do {
            
			contador = realizarBusqueda(twitter, contador, query);
//            } while ((query = result.nextQuery()) != null);
            
            query = new Query("lang:fr");     
            contador = realizarBusqueda(twitter, contador, query);
            
			query = new Query("lang:it");         
			contador = realizarBusqueda(twitter, contador, query);
            
          System.out.println("Insertados " + contador + " tweets.");
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}

	private int realizarBusqueda(Twitter twitter, int contador, Query query) throws TwitterException {
		QueryResult result;
		result = twitter.search(query);
		List<Status> tweets = result.getTweets();
		for (Status tweet : tweets) {
		    if (tweet.getUser().getFollowersCount()>1500) {
		    	insertarTweet(tweet);
		    	contador ++;
		    	System.out.println("Insertando tweets : " + contador + " insertados.");
		    }                    	
		}
		return contador;
	}

	private void insertarTweet(Status tweet) {
		Tweet nuevoTweet;
		nuevoTweet = new Tweet();
		nuevoTweet.setUsuario(tweet.getUser().getName());
		nuevoTweet.setTexto(tweet.getText());
		nuevoTweet.setLocalizacion(tweet.getLang());
		nuevoTweet.setValidacion(false);
		repository.save(nuevoTweet);
	}

}
