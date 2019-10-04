package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Movie")
public class Movie {
	@Id
	@Column
	@GeneratedValue
	Integer id;
	@NotEmpty(message="name can not be empty")
	@Column
	String name;
	@NotEmpty(message="genre can not be empty")
	@Column
	String genre;
	public Movie() {
		super();
	}
	public Movie(Integer mId, String name, String genre) {
		super();
		this.id = mId;
		this.name = name;
		this.genre = genre;
	}
	
	
	public Movie(@NotEmpty(message = "name can not be empty") String name,
			@NotEmpty(message = "genre can not be empty") String genre) {
		super();
		this.name = name;
		this.genre = genre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer mId) {
		this.id = mId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", genre=" + genre + "]";
	}
	
	
}
