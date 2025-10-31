package br.ufscar.pooa.cinema_api.application.usecases.user;

import br.ufscar.pooa.cinema_api.application.dtos.user.RegisterUserRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.user.UserResponseDTO;
import br.ufscar.pooa.cinema_api.application.exceptions.ResourceAlreadyExistsException;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterUserUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUserUseCase implements IRegisterUserUseCase {
    private final IUserRepository userRepository;
    private final IObjectMapper objectMapper;

    public RegisterUserUseCase(IUserRepository userRepository, IObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public UserResponseDTO execute(RegisterUserRequestDTO requestDTO) {
        Optional<User> userExists = userRepository.findByEmail(requestDTO.getEmail());

        if (userExists.isPresent()) {
            throw new ResourceAlreadyExistsException("User", "email", requestDTO.getEmail());
        }

        User newUser = objectMapper.parseObject(requestDTO, User.class);

        User savedUser = userRepository.save(newUser);

        return objectMapper.parseObject(savedUser, UserResponseDTO.class);
    }
}
