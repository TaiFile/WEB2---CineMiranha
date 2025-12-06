package br.ufscar.pooa.cinema_api.features._shared.gateways.repository;

import br.ufscar.pooa.cinema_api.domain.entities.Session;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ISessionRepository {
    Session save(Session session);

    Optional<Session> findById(Long id);

    List<Session> findAllByDateBetween(LocalDateTime start, LocalDateTime end);

    void delete(Long id);
}
