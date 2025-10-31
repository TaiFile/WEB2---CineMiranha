package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.session;

import br.ufscar.pooa.cinema_api.application.ports.out.repository.ISessionRepository;
import br.ufscar.pooa.cinema_api.domain.Session;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class SessionRepositoryAdapter implements ISessionRepository {
    private final SessionJpaRepository sessionJpaRepository;

    public SessionRepositoryAdapter(SessionJpaRepository sessionJpaRepository) {
        this.sessionJpaRepository = sessionJpaRepository;
    }

    @Override
    public Session save(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        if (session.getId() != null) {
            throw new IllegalArgumentException("Session ID must be null for a new session");
        }

        return sessionJpaRepository.save(session);
    }

    @Override
    public Optional<Session> findById(Long id) {
        return sessionJpaRepository.findById(id);
    }

    @Override
    public List<Session> findAllByDateBetween(LocalDateTime start, LocalDateTime end) {
        return sessionJpaRepository.findAllByDateBetween(start, end);
    }

    @Override
    public void delete(Long id) {
        sessionJpaRepository.deleteById(id);
    }
}