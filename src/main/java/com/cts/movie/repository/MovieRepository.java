package com.cts.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.movie.model.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
