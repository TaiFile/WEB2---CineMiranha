package br.ufscar.pooa.cinema_api.features.movies;

import br.ufscar.pooa.cinema_api.features.movies.GenreDTO;
import br.ufscar.pooa.cinema_api.domain.entities.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IGenreMapper {
    br.ufscar.pooa.cinema_api.features.movies.GenreDTO toGenreDTO(Genre genre);
}
