package com.example.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.entity.Movie;
import com.example.repository.MovieRepository;
import com.example.service.MovieServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceTest {

	
	@Mock
	private MovieRepository movieRepository;
	
	@InjectMocks
	private MovieServiceImpl movieServiceImpl;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllMovies() {
		List<Movie> movieList=new ArrayList<Movie>();
		movieList.add(new Movie(1, "chichore", "drama"));

		movieList.add(new Movie(2,"ddlj","romantic"));
		movieList.add(new Movie(3,"Mission Mangal","Science Fiction"));
		movieList.add(new Movie(5,"naal","drama"));
		movieList.add(new Movie(6,"Hrudayantar","Family"));
		
		
		when(movieRepository.findAll()).thenReturn(movieList);
		
		List<Movie> mList=movieServiceImpl.getAllMovies();
		assertEquals(5, mList.size());
	}
	
	@Test
	public void testMovieById() {
		Movie movie=new Movie(1,"chichore", "drama");
		
		when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
		
		Movie actual=movieServiceImpl.getMovieById(1).get();
		assertEquals(1, actual.getId().intValue());
		assertEquals("chichore", actual.getName());
		assertEquals("drama", actual.getGenre());
		
	}
	
	@Test
	public void testAddMovie() {
		Movie movie=new Movie(6, "goolabjam", "cooking");
		when(movieRepository.save(movie)).thenReturn(movie);
		
		Movie actual=movieServiceImpl.addMovie(movie);
		
		assertEquals(6, actual.getId().intValue());
		assertEquals("goolabjam", actual.getName());
		assertEquals("cooking", actual.getGenre());
	}
	
	@Test
	public void deleteTest() {
		Movie movie=new Movie(6, "goolabjam", "cooking");
		movieServiceImpl.deleteMovieById(movie.getId());
		verify(movieRepository,times(1)).deleteById(6);
	}
}
