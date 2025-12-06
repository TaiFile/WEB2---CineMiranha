package br.ufscar.pooa.cinema_api.domain.repositories.movie;

import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
}

