package br.ufscar.pooa.cinema_api.application.usecases.user;

import br.ufscar.pooa.cinema_api.application.mappers.IUserMapper;
import br.ufscar.pooa.cinema_api.application.dtos.user.RegisterUserRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.user.UserResponseDTO;
import br.ufscar.pooa.cinema_api.application.exceptions.ResourceAlreadyExistsException;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterUserUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUserUseCase implements IRegisterUserUseCase {
    private final IUserRepository userRepository;
    private final IUserMapper IUserMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(IUserRepository userRepository, IUserMapper IUserMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.IUserMapper = IUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO execute(RegisterUserRequestDTO requestDTO) {
        Optional<User> userExists = userRepository.findByEmail(requestDTO.getEmail());

        if (userExists.isPresent()) {
            throw new ResourceAlreadyExistsException("User", "email", requestDTO.getEmail());
        }

        User newUser = IUserMapper.toUser(requestDTO);
        newUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        User savedUser = userRepository.save(newUser);

        return IUserMapper.toUserResponseDTO(savedUser);
    }
}