package br.ufscar.pooa.cinema_api.application.ports.out.repository;

import br.ufscar.pooa.cinema_api.domain.Row;

import java.util.Optional;

public interface IRowRepository {
    Row save(Row row);
    Optional<Row> findById(Long id);

    void delete(Long id);
}
