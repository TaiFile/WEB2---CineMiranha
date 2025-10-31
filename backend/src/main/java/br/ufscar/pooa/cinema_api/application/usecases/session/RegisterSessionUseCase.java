package br.ufscar.pooa.cinema_api.application.usecases.session;

import br.ufscar.pooa.cinema_api.application.dtos.session.RegisterSessionRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.session.SessionResponseDTO;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterSessionUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IMovieRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IRoomRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.ISessionRepository;
import br.ufscar.pooa.cinema_api.domain.Movie;
import br.ufscar.pooa.cinema_api.domain.Room;
import br.ufscar.pooa.cinema_api.domain.Session;
import org.springframework.stereotype.Service;

@Service
public class RegisterSessionUseCase implements IRegisterSessionUseCase {
    private final ISessionRepository sessionRepository;
    private final IRoomRepository repository;
    private final IMovieRepository movieRepository;
    private final IObjectMapper mapper;

    public RegisterSessionUseCase(ISessionRepository sessionRepository, IRoomRepository repository, IMovieRepository movieRepository, IObjectMapper mapper) {
        this.sessionRepository = sessionRepository;
        this.repository = repository;
        this.movieRepository = movieRepository;
        this.mapper = mapper;
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

        return mapper.parseObject(savedSession, SessionResponseDTO.class);
    }
}
