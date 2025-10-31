package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.seat;

import br.ufscar.pooa.cinema_api.application.ports.out.repository.ISeatRepository;
import br.ufscar.pooa.cinema_api.domain.Seat;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SeatRepositoryAdapter implements ISeatRepository {
    private final SeatJpaRepository seatJpaRepository;

    public SeatRepositoryAdapter(SeatJpaRepository seatJpaRepository) {
        this.seatJpaRepository = seatJpaRepository;
    }

    @Override
    public Seat save(Seat seat) {
        return seatJpaRepository.save(seat);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatJpaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        seatJpaRepository.deleteById(id);
    }
}
