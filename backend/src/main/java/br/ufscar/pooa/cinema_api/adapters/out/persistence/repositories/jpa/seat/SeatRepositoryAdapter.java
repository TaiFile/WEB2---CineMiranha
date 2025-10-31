package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.seat;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.SeatEntity;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper; // 1. Usar a interface do Mapper
import br.ufscar.pooa.cinema_api.application.ports.out.repository.ISeatRepository;
import br.ufscar.pooa.cinema_api.domain.Seat;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SeatRepositoryAdapter implements ISeatRepository {
    private final SeatJpaRepository seatJpaRepository;
    private final IObjectMapper objectMapper;

    public SeatRepositoryAdapter(SeatJpaRepository seatJpaRepository, IObjectMapper objectMapper) {
        this.seatJpaRepository = seatJpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Seat save(Seat seat) {
        SeatEntity seatEntity = objectMapper.parseObject(seat, SeatEntity.class);
        SeatEntity savedEntity = seatJpaRepository.save(seatEntity);
        return objectMapper.parseObject(savedEntity, Seat.class);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatJpaRepository.findById(id)
                .map(entity -> objectMapper.parseObject(entity, Seat.class));
    }

    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        seatJpaRepository.deleteById(id);
    }
}