package br.ufscar.pooa.cinema_api.domain.repositories.room;

import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IRoomRepository;
import br.ufscar.pooa.cinema_api.domain.entities.Room;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoomRepositoryAdapter implements IRoomRepository {
    private final RoomJpaRepository roomJpaRepository;

    public RoomRepositoryAdapter(RoomJpaRepository roomJpaRepository) {
        this.roomJpaRepository = roomJpaRepository;
    }

    @Override
    public Room save(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (room.getId() != null) {
            throw new IllegalArgumentException("Room ID must be null for a new room");
        }

        return roomJpaRepository.save(room);
    }

    @Override
    public Optional<Room> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return roomJpaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        roomJpaRepository.deleteById(id);

    }
}
