package com.example.web;

import static org.hamcrest.Matchers.hasSize;
/*import static org.junit.Assert.assertThat;*/
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import com.example.controller.MovieController;
import com.example.demo.SbJunitApplication;
import com.example.entity.Movie;
import com.example.service.MovieService;

@RunWith(SpringRunner.class)
/* @ContextConfiguration(classes = SbJunitApplication.class) */
@WebMvcTest(MovieController.class)
/* @SpringBootTest */
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private MovieController movieController;

	@MockBean
	MovieService movieService;

	@Test
	public void testAddMovie() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Movie movieToAdd = new Movie("gravity", "science");

		when(movieService.addMovie(any(Movie.class))).thenReturn(movieToAdd);

		ResponseEntity<Movie> responseEntity = movieController.saveToDo(movieToAdd);
		System.out.println("res ent==" + responseEntity);
		assertEquals(200, responseEntity.getStatusCodeValue());

	}
	
	@Test
	public void testGetAllMovies() {
		List<Movie> mList=new ArrayList<>();
		mList.add(new Movie(1, "a", "a1"));
		mList.add(new Movie(2, "b", "b1"));
		
		when(movieService.getAllMovies()).thenReturn(mList);
		
		
	}
}
