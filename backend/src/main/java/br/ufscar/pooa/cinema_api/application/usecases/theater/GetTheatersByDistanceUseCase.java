package br.ufscar.pooa.cinema_api.application.usecases.theater;

import br.ufscar.pooa.cinema_api.application.ports.in.IGetTheatersByDistanceUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.ITheaterRepository;
import br.ufscar.pooa.cinema_api.domain.Theater;
import br.ufscar.pooa.cinema_api.utils.DistanceCalculator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class GetTheatersByDistanceUseCase implements IGetTheatersByDistanceUseCase {

    private final ITheaterRepository theaterRepository;

    public GetTheatersByDistanceUseCase(ITheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @Override
    public List<Theater> execute(Double userLatitude, Double userLongitude) {
        List<Theater> theaters = theaterRepository.findAll();

        theaters.forEach(theater -> {
            double distance = DistanceCalculator.equirectangularDistance(
                userLatitude,
                userLongitude,
                theater.getAddress().getLatitude(),
                theater.getAddress().getLongitude()
            );
            theater.setDistance(distance);
        });

        return theaters.stream()
            .sorted(Comparator.comparing(Theater::getDistance))
            .collect(Collectors.toList());
    }
}
