package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.row;

import br.ufscar.pooa.cinema_api.domain.Row;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RowJpaRepository extends JpaRepository<Row, Long> {
}
