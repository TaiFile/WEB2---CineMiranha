package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.session.SessionResponseDTO;
import br.ufscar.pooa.cinema_api.domain.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IMovieMapper.class, IRoomMapper.class})
public interface ISessionMapper {

    @Mapping(source = "movie.id", target = "movieId")
    @Mapping(source = "room.id", target = "roomId")
    SessionResponseDTO toSessionResponseDTO(Session session);
}
