package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.room;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.RoomEntity;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IRoomRepository;
import br.ufscar.pooa.cinema_api.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoomRepositoryAdapter implements IRoomRepository {
    private final RoomJpaRepository roomJpaRepository;
    private final IObjectMapper objectMapper;

    public RoomRepositoryAdapter(RoomJpaRepository roomJpaRepository, IObjectMapper objectMapper) {
        this.roomJpaRepository = roomJpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Room save(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (room.getId() != null) {
            throw new IllegalArgumentException("Room ID must be null for a new room");
        }

        RoomEntity entityToSave = objectMapper.parseObject(room, RoomEntity.class);
        RoomEntity savedEntity = roomJpaRepository.save(entityToSave);
        return objectMapper.parseObject(savedEntity, Room.class);
    }

    @Override
    public Optional<Room> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return roomJpaRepository.findById(id)
                .map(entity -> objectMapper.parseObject(entity, Room.class));
    }

    @Override
    public void delete(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        roomJpaRepository.deleteById(id);

    }
}
