package br.ufscar.pooa.cinema_api.features.theaters.usecase;

import br.ufscar.pooa.cinema_api.domain.repositories.theater.ITheaterRepository;
import br.ufscar.pooa.cinema_api.domain.entities.Theater;
import br.ufscar.pooa.cinema_api.features.theaters.dto.TheaterResponseDTO;
import br.ufscar.pooa.cinema_api.features.theaters.mapper.ITheaterMapper;
import br.ufscar.pooa.cinema_api.utils.DistanceCalculator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetTheatersByDistanceUseCase {

    private final ITheaterRepository theaterRepository;
    private final ITheaterMapper theaterMapper;

    public GetTheatersByDistanceUseCase(ITheaterRepository theaterRepository, ITheaterMapper theaterMapper) {
        this.theaterRepository = theaterRepository;
        this.theaterMapper = theaterMapper;
    }

    @Transactional(readOnly = true)
    public List<TheaterResponseDTO> execute(Double userLatitude, Double userLongitude) {
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
            .map(theaterMapper::toTheaterResponseDTO)
            .collect(Collectors.toList());
    }
}


