package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.domain.Theater;

import java.util.List;

public interface IGetTheatersByDistanceUseCase {
    List<Theater> execute(Double userLatitude, Double userLongitude);
}
