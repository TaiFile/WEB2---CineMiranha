package br.ufscar.pooa.cinema_api.features._shared.gateways.repository;

import br.ufscar.pooa.cinema_api.domain.entities.Seat;
import java.util.Optional;

public interface ISeatRepository {

    Seat save(Seat seat);

    Optional<Seat> findById(Long id);

    void delete(Long id);
}
