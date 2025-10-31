package br.ufscar.pooa.cinema_api.adapters.out.mapper.mapstruct;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.GenreEntity;
import br.ufscar.pooa.cinema_api.domain.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    
    @Mapping(target = "movies", ignore = true)
    Genre toDomain(GenreEntity entity);
}