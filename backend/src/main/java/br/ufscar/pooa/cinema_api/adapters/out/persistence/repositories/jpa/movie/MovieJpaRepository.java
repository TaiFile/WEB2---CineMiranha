package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.movie;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {
    Optional<MovieEntity> findByTitle(String title);
}