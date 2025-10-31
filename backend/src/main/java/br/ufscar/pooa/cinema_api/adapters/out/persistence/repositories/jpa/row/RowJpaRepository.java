package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.row;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.RowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RowJpaRepository extends JpaRepository<RowEntity, Long> {
}
