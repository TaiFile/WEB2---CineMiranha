package br.ufscar.pooa.cinema_api.application.usecases.manager;

import br.ufscar.pooa.cinema_api.application.mappers.IManagerMapper;
import br.ufscar.pooa.cinema_api.application.dtos.manager.ManagerResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.manager.RegisterManagerRequestDTO;
import br.ufscar.pooa.cinema_api.application.exceptions.ResourceAlreadyExistsException;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterManagerUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IManagerRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.Manager;
import br.ufscar.pooa.cinema_api.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterManagerUseCase implements IRegisterManagerUseCase {

    private final IUserRepository userRepository;
    private final IManagerRepository managerRepository;
    private final IManagerMapper IManagerMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterManagerUseCase(IUserRepository userRepository, IManagerRepository managerRepository, IManagerMapper IManagerMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
        this.IManagerMapper = IManagerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ManagerResponseDTO execute(RegisterManagerRequestDTO requestDTO) {
        Optional<User> userExists = userRepository.findByEmail(requestDTO.getEmail());
        if (userExists.isPresent()) {
            throw new ResourceAlreadyExistsException("User", "email", requestDTO.getEmail());
        }

        Optional<Manager> managerExists = managerRepository.findByCpf(requestDTO.getCpf());
        if (managerExists.isPresent()) {
            throw new ResourceAlreadyExistsException("Manager", "CPF", requestDTO.getCpf());
        }

        Manager newManager = IManagerMapper.toManager(requestDTO);
        newManager.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        Manager savedUser = managerRepository.save(newManager);

        return IManagerMapper.toManagerResponseDTO(savedUser);
    }
}