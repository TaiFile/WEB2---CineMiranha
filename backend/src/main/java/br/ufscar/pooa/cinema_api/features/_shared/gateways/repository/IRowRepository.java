package br.ufscar.pooa.cinema_api.features._shared.gateways.repository;

import br.ufscar.pooa.cinema_api.domain.entities.Row;

import java.util.Optional;

public interface IRowRepository {
    Row save(Row row);
    Optional<Row> findById(Long id);

    void delete(Long id);
}
