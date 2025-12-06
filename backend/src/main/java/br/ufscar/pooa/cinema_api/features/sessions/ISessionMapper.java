package br.ufscar.pooa.cinema_api.features.sessions;

import br.ufscar.pooa.cinema_api.features.theaters.IRoomMapper;
import br.ufscar.pooa.cinema_api.features.sessions.SessionResponseDTO;
import br.ufscar.pooa.cinema_api.domain.entities.Session;
import br.ufscar.pooa.cinema_api.features.movies.IMovieMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IMovieMapper.class, IRoomMapper.class})
public interface ISessionMapper {

    @Mapping(source = "movie.id", target = "movieId")
    @Mapping(source = "room.id", target = "roomId")
    SessionResponseDTO toSessionResponseDTO(Session session);
}
