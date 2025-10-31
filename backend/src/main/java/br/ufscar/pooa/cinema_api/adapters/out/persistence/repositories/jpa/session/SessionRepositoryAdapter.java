package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.session;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.SessionEntity;
import br.ufscar.pooa.cinema_api.adapters.out.mapper.mapstruct.SessionMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.ISessionRepository;
import br.ufscar.pooa.cinema_api.domain.Session;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class SessionRepositoryAdapter implements ISessionRepository {
    private final SessionJpaRepository sessionJpaRepository;
    private final IObjectMapper objectMapper;
    private final SessionMapper sessionMapper;

    public SessionRepositoryAdapter(SessionJpaRepository sessionJpaRepository, IObjectMapper objectMapper, SessionMapper sessionMapper) {
        this.sessionJpaRepository = sessionJpaRepository;
        this.objectMapper = objectMapper;
        this.sessionMapper = sessionMapper;
    }

    @Override
    public Session save(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        if (session.getId() != null) {
            throw new IllegalArgumentException("Session ID must be null for a new session");
        }

        SessionEntity sessionEntity = objectMapper.parseObject(session, SessionEntity.class);
        SessionEntity savedEntity = sessionJpaRepository.save(sessionEntity);

        return sessionMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Session> findById(Long id) {
        Optional<SessionEntity> entityOptional = sessionJpaRepository.findById(id);

        return entityOptional.map(sessionMapper::toDomain);
    }

    @Override
    public List<Session> findAllByDateBetween(LocalDateTime start, LocalDateTime end) {
        List<SessionEntity> sessionEntities = sessionJpaRepository.findAllByDateBetween(start, end);
        return sessionEntities.stream()
                .map(sessionMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Long id) {
        sessionJpaRepository.deleteById(id);
    }
}
