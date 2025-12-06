package br.ufscar.pooa.cinema_api.features._shared.gateways.repository;

import br.ufscar.pooa.cinema_api.domain.entities.Room;

import java.util.Optional;

public interface IRoomRepository {
    Room save(Room room);

    Optional<Room> findById(Long id);

    void delete(Long id);
}
