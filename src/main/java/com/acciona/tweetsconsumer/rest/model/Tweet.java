package com.acciona.tweetsconsumer.rest.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Access(value = AccessType.FIELD)
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = null;

	private String usuario = null;

	@Column(length=500)
	private String texto = null;

	private String localizacion = null;

	private Boolean validacion = null;
}
