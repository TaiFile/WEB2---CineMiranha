package br.ufscar.pooa.cinema_api.application.ports.out.repository;

import br.ufscar.pooa.cinema_api.domain.Movie;
import java.util.List;
import java.util.Optional;

public interface IMovieRepository {

    Movie save(Movie movie);

    Optional<Movie> findById(Long id);

    Optional<Movie> findByTitle(String title);

    void delete(long id);

    List<Movie> findAll();
}
