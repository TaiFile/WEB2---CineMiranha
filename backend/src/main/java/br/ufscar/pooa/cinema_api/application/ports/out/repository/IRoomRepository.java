package br.ufscar.pooa.cinema_api.application.ports.out.repository;

import br.ufscar.pooa.cinema_api.domain.Room;

import java.util.Optional;

public interface IRoomRepository {
    Room save(Room room);

    Optional<Room> findById(Long id);

    void delete(Long id);
}
