package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.genre;

import br.ufscar.pooa.cinema_api.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreJpaRepository extends JpaRepository<Genre, Long> {

}
