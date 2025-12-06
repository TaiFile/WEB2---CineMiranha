package br.ufscar.pooa.cinema_api.domain.repositories.genre;

import br.ufscar.pooa.cinema_api.domain.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreJpaRepository extends JpaRepository<Genre, Long> {

}
