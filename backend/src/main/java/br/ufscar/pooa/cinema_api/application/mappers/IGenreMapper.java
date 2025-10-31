package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.genre.GenreDTO;
import br.ufscar.pooa.cinema_api.domain.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IGenreMapper {
    GenreDTO toGenreDTO(Genre genre);
}