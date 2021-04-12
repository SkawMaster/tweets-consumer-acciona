package com.acciona.tweetsconsumer.rest.dto;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TweetDto {

	@Id
	private Integer id = null;

	private String usuario = null;

	private String texto = null;

	private String localizacion = null;

	private Boolean validacion = null;
}
