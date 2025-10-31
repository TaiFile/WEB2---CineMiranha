package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.seat;

import br.ufscar.pooa.cinema_api.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findById(Long id);

}
