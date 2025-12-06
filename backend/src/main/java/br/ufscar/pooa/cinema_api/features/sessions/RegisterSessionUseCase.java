package br.ufscar.pooa.cinema_api.features.sessions;

import br.ufscar.pooa.cinema_api.features.sessions.ISessionMapper;
import br.ufscar.pooa.cinema_api.features.sessions.RegisterSessionRequestDTO;
import br.ufscar.pooa.cinema_api.features.sessions.SessionResponseDTO;
import br.ufscar.pooa.cinema_api.features.sessions.IRegisterSessionUseCase;
import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IMovieRepository;
import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IRoomRepository;
import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.ISessionRepository;
import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.domain.entities.Room;
import br.ufscar.pooa.cinema_api.domain.entities.Session;
import org.springframework.stereotype.Service;

@Service
public class RegisterSessionUseCase implements IRegisterSessionUseCase {
    private final ISessionRepository sessionRepository;
    private final IRoomRepository repository;
    private final IMovieRepository movieRepository;
    private final ISessionMapper ISessionMapper;

    public RegisterSessionUseCase(ISessionRepository sessionRepository, IRoomRepository repository, IMovieRepository movieRepository, ISessionMapper ISessionMapper) {
        this.sessionRepository = sessionRepository;
        this.repository = repository;
        this.movieRepository = movieRepository;
        this.ISessionMapper = ISessionMapper;
    }

    @Override
    public SessionResponseDTO execute(RegisterSessionRequestDTO requestDTO) {
        Room room = repository.findById(requestDTO.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found."));

        Movie movie = movieRepository.findById(requestDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found."));

        Session session = new Session();
        session.setMovie(movie);
        session.setRoom(room);
        session.setFormat(requestDTO.getFormat());
        session.setDate(requestDTO.getDate());
        session.setSubtitle(requestDTO.getSubtitle());
        session.setPriceInCents(requestDTO.getPriceInCents());

        Session savedSession = sessionRepository.save(session);

        return ISessionMapper.toSessionResponseDTO(savedSession);
    }
}
