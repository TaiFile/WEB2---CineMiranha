package br.ufscar.pooa.cinema_api.features.movies;

import br.ufscar.pooa.cinema_api.features.movies.RegisterMovieRequestDTO;
import br.ufscar.pooa.cinema_api.features.movies.MovieResponseDTO;

public interface IRegisterMovieUseCase {
    MovieResponseDTO execute(RegisterMovieRequestDTO requestDTO);
}
