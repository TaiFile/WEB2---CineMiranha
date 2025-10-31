package br.ufscar.pooa.cinema_api.adapters.out.mapper.mapstruct;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.MovieEntity;
import br.ufscar.pooa.cinema_api.domain.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Movie toDomain(MovieEntity entity);
}