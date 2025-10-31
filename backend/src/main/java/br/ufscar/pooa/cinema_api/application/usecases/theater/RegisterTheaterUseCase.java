package br.ufscar.pooa.cinema_api.application.usecases.theater;

import br.ufscar.pooa.cinema_api.application.mappers.IAddressMapper;
import br.ufscar.pooa.cinema_api.application.mappers.ITheaterMapper;
import br.ufscar.pooa.cinema_api.application.dtos.theater.RegisterTheaterRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.theater.TheaterResponseDTO;
import br.ufscar.pooa.cinema_api.application.exceptions.ResourceAlreadyExistsException;
import br.ufscar.pooa.cinema_api.application.exceptions.ResourceNotFoundException;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterTheaterUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IAddressRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.ITheaterRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.Manager;
import br.ufscar.pooa.cinema_api.domain.Theater;
import br.ufscar.pooa.cinema_api.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterTheaterUseCase implements IRegisterTheaterUseCase {
    private final ITheaterRepository theaterRepository;
    private final IAddressRepository addressRepository;
    private final IUserRepository userRepository;
    private final ITheaterMapper ITheaterMapper;
    private final IAddressMapper IAddressMapper;

    public RegisterTheaterUseCase(ITheaterRepository theaterRepository,
                                  IAddressRepository addressRepository, IUserRepository userRepository,
                                  ITheaterMapper ITheaterMapper, IAddressMapper IAddressMapper) {
        this.theaterRepository = theaterRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.ITheaterMapper = ITheaterMapper;
        this.IAddressMapper = IAddressMapper;
    }

    @Override
    @Transactional
    public TheaterResponseDTO execute(String userEmail, RegisterTheaterRequestDTO requestDTO) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", userEmail)
        );

        theaterRepository.findByName(requestDTO.getName()).ifPresent(t -> {
            throw new ResourceAlreadyExistsException("Theater", "name", requestDTO.getName());
        });

        addressRepository.findByStreetAndNumber(
                requestDTO.getAddress().getStreet(), requestDTO.getAddress().getNumber()
        ).ifPresent(a -> {
            throw new ResourceAlreadyExistsException("Address", "street and number",
                    requestDTO.getAddress().getStreet() + " " + requestDTO.getAddress().getNumber());
        });

        Theater newTheater = new Theater();
        newTheater.setName(requestDTO.getName());
        newTheater.setLogoUrl(requestDTO.getLogoUrl());
        newTheater.setAddress(IAddressMapper.toAddress(requestDTO.getAddress()));
        newTheater.setManagers(List.of((Manager) user));

        Theater savedTheater = theaterRepository.save(newTheater);

        return ITheaterMapper.toTheaterResponseDTO(savedTheater);
    }
}