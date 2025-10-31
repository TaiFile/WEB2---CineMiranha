package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.session;

import br.ufscar.pooa.cinema_api.domain.Session;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessionJpaRepository extends JpaRepository<Session, Long> {
    @Override
    @EntityGraph(attributePaths = {"room", "room.rows", "room.rows.seats", "tickets"})
    Optional<Session> findById(@NonNull Long id);

    @EntityGraph(attributePaths = {"movie", "room", "room.rows", "room.rows.seats", "tickets"})
    List<Session> findAllByDateBetween(LocalDateTime start, LocalDateTime end);
}
