package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.room;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, Long> {
}