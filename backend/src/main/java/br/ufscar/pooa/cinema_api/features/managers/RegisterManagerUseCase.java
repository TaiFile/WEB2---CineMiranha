package br.ufscar.pooa.cinema_api.features.managers;

import br.ufscar.pooa.cinema_api.features._shared.exceptions.ResourceAlreadyExistsException;
import br.ufscar.pooa.cinema_api.features.managers.IManagerMapper;
import br.ufscar.pooa.cinema_api.features.managers.ManagerResponseDTO;
import br.ufscar.pooa.cinema_api.features.managers.RegisterManagerRequestDTO;
import br.ufscar.pooa.cinema_api.features.managers.IRegisterManagerUseCase;
import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IManagerRepository;
import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.entities.Manager;
import br.ufscar.pooa.cinema_api.domain.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterManagerUseCase implements
    br.ufscar.pooa.cinema_api.features.managers.IRegisterManagerUseCase {

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
