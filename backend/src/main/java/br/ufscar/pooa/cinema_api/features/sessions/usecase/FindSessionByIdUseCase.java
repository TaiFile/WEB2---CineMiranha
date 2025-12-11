package br.ufscar.pooa.cinema_api.features.sessions.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Session;
import br.ufscar.pooa.cinema_api.domain.repositories.session.ISessionRepository;
import br.ufscar.pooa.cinema_api.features.sessions.dto.SessionDetailResponseDTO;
import br.ufscar.pooa.cinema_api.features.sessions.mapper.ISessionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FindSessionByIdUseCase {

    private final ISessionRepository sessionRepository;
    private final ISessionMapper sessionMapper;

    public FindSessionByIdUseCase(ISessionRepository sessionRepository, ISessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
    }

    @Transactional(readOnly = true)
    public SessionDetailResponseDTO execute(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + sessionId));

        return sessionMapper.toSessionDetailResponseDTO(session);
    }
}

