package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.room;

import br.ufscar.pooa.cinema_api.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<Room, Long> {
}