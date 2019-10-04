package com.example.util;

import com.example.entity.Movie;

public class PayloadValidator {
	public static boolean validateCreatePayload(Movie movie){
		System.out.println("in Pay Val");
		if (movie.getId() > 0){
			System.out.println("in if Pay Val");
			return false;
		}
		return true;
	}

}
