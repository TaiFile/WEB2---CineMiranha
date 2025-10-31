package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.movie;

import br.ufscar.pooa.cinema_api.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieJpaRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
}