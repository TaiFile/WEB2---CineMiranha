package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.genre;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {

}
