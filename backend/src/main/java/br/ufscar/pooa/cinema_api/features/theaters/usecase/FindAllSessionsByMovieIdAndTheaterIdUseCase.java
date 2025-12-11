package br.ufscar.pooa.cinema_api.features.theaters.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Session;
import br.ufscar.pooa.cinema_api.domain.repositories.session.ISessionRepository;
import br.ufscar.pooa.cinema_api.features.sessions.dto.SessionResponseDTO;
import br.ufscar.pooa.cinema_api.features.sessions.mapper.ISessionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllSessionsByMovieIdAndTheaterIdUseCase {

    private final ISessionRepository sessionRepository;
    private final ISessionMapper sessionMapper;

    public FindAllSessionsByMovieIdAndTheaterIdUseCase(ISessionRepository sessionRepository, ISessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
    }

    @Transactional(readOnly = true)
    public List<SessionResponseDTO> execute(Long movieId, Long theaterId) {
        List<Session> sessions = sessionRepository.findAllByMovieIdAndTheaterId(movieId, theaterId);
        return sessions.stream()
                .map(sessionMapper::toSessionResponseDTO)
                .collect(Collectors.toList());
    }
}

