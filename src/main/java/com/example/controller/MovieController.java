package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Movie;
import com.example.entity.Response;
import com.example.exceptions.MovieNotFound;
import com.example.service.MovieService;
import com.example.util.PayloadValidator;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {
	@Autowired
	MovieService service;

	@GetMapping("/test")
	public void display() {
		System.out.println("hello sb!!!");
	}

	/*@PostMapping(value = "/movie/add")
	public String addMovie(@RequestBody Movie movie) {
		service.addMovie(movie);
		return "Movie added";
	}*/
	
	
	@RequestMapping(value = "/amovie", method = RequestMethod.POST)
   	public ResponseEntity<Movie> saveToDo(@RequestBody Movie movie) throws MovieNotFound{
    	System.out.println("Payload to save " + movie);
    	
		return new ResponseEntity<Movie>(service.addMovie(movie), HttpStatus.OK);
   	}

	@RequestMapping(value="/movie", method=RequestMethod.GET,produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Movie>> getAllMovies(){
    	//logger.info("Returning all the ToDo´s");
		return new ResponseEntity<List<Movie>>(service.getAllMovies(), HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Response> removeToDoById(@PathVariable("id") Integer id) throws MovieNotFound{
	    	//logger.info("ToDo id to remove " + id);
	    	Movie movie = service.getMovieById(id).get();
	    	System.out.println("from controller ------"+movie);
	    	if (movie == null || movie.getId() <= 0){
	            throw new MovieNotFound("Movie to delete doesn´t exist");
	    	}
	    	service.deleteMovieById(id);
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Movie has been deleted"), HttpStatus.OK);
		}
	
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET,produces = { "application/json", "application/xml" })
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") Integer id) throws MovieNotFound{
    	//logger.info("ToDo id to return " + id);
		System.out.println("in get by id");
    	Movie movie =service.getMovieById(id).get();
    	System.out.println("from controller ------"+movie);
    	if (movie == null || movie.getId() <= 0){
            throw new MovieNotFound("Movie doesn´t exist");
    	}
		return new ResponseEntity<Movie>(service.getMovieById(id).get(), HttpStatus.OK);
	}
	
	/*@GetMapping(value="/movie/{id}")
	public Movie getMoviebyId(@PathVariable Integer id){
		return service.getMovieById(id).orElseThrow(()->new MovieNotFound("Movie with "+id + "not found"));
	}*/
	/*@PutMapping("/movie/{id}")
	public String updateMovieById(@PathVariable Integer id,@RequestBody Movie movie){
		Movie movie1=service.getMovieById(id).orElseThrow(()->new MovieNotFound("Movie with "+id + "not found"));
		movie1.setName(movie.getName());
		movie1.setGenre(movie.getGenre());
		service.addMovie(movie1);
		return "Updated movie : "+id;
	}*/
	
	
	
	 @RequestMapping(value = "/movie", method = RequestMethod.PATCH)
	   	public ResponseEntity<Movie>  updateMovie(@RequestBody Movie payload) throws MovieNotFound{
	    	//logger.info("Payload to update " + payload);
		 Movie movie = service.getMovieById(payload.getId()).get();
	    	if (movie == null || movie.getId() <= 0){
	            throw new MovieNotFound("Movie to update doesn´t exist");
	    	}
			return new ResponseEntity<Movie>(service.addMovie(payload), HttpStatus.OK);
	   	}
}
