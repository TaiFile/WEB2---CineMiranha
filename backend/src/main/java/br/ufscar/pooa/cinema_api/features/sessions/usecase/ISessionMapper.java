package br.ufscar.pooa.cinema_api.features.sessions.usecase;

import br.ufscar.pooa.cinema_api.features.rooms.usecase.IRoomMapper;
import br.ufscar.pooa.cinema_api.domain.entities.Session;
import br.ufscar.pooa.cinema_api.features.movies.usecase.IMovieMapper;
import br.ufscar.pooa.cinema_api.features.sessions.dto.SessionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IMovieMapper.class, IRoomMapper.class})
public interface ISessionMapper {

    @Mapping(source = "movie.id", target = "movieId")
    @Mapping(source = "room.id", target = "roomId")
    SessionResponseDTO toSessionResponseDTO(Session session);
}
