package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Movie;
import com.example.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	MovieRepository movieRepository;
	/*public List<Movie> getAllMovies(){
		return movieRepository.findAll();
	}
	
	public Movie addMovie(Movie movie){
		return movieRepository.save(movie);
	}
	
	public Optional<Movie> getMovieById(Long id){
		return movieRepository.findById(id);
	}

	@Override
	public void deleteMovieById(Long id) {
		movieRepository.deleteById(id);
		
	}
*/

	@Override
	public List<Movie> getAllMovies() {
		
		return movieRepository.findAll();
	}

	@Override
	public Movie addMovie(Movie movie) {
		System.out.println("in add service");
		return movieRepository.save(movie);
	}

	
	@Override
	public Optional<Movie> getMovieById(Integer id) {
		System.out.println("from service ------"+movieRepository.findById(id));
		return movieRepository.findById(id);
	}

	@Override
	public void deleteMovieById(Integer id) {
		movieRepository.deleteById(id);
		
	}
	
}
