package br.ufscar.pooa.cinema_api.application.ports.out.repository;

import br.ufscar.pooa.cinema_api.domain.Seat;
import java.util.Optional;

public interface ISeatRepository {

    Seat save(Seat seat);

    Optional<Seat> findById(Long id);

    void delete(Long id);
}
