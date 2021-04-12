package com.acciona.tweetsconsumer.rest.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.acciona.tweetsconsumer.rest.generated.model.TweetDto;
import com.acciona.tweetsconsumer.rest.model.Tweet;

@Mapper(componentModel = "spring")
public interface TweetsconsumerMapper {

	Tweet tweetDtoToTweet(TweetDto tweetDto);

	@InheritInverseConfiguration
	TweetDto tweetToTweetDto(Tweet tweet);
}
