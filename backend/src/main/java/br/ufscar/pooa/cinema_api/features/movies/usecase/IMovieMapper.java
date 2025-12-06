package br.ufscar.pooa.cinema_api.features.movies.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.features.movies.dto.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.movies.dto.RegisterMovieRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IGenreMapper.class})
public interface IMovieMapper {

    MovieResponseDTO toMovieResponseDTO(Movie movie);

    Movie toMovie(RegisterMovieRequestDTO registerMovieRequestDTO);
}
