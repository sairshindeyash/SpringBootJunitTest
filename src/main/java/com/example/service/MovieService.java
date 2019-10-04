package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Movie;

public interface MovieService {
	public List<Movie> getAllMovies();
	public Movie addMovie(Movie movie);
	
	
	void deleteMovieById(Integer id);
	Optional<Movie> getMovieById(Integer id);
}
