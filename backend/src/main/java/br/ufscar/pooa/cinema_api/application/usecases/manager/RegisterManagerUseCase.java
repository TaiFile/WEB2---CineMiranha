package br.ufscar.pooa.cinema_api.application.usecases.manager;

import br.ufscar.pooa.cinema_api.application.dtos.manager.ManagerResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.manager.RegisterManagerRequestDTO;
import br.ufscar.pooa.cinema_api.application.exceptions.ResourceAlreadyExistsException;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterManagerUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IManagerRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.Manager;
import br.ufscar.pooa.cinema_api.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterManagerUseCase implements IRegisterManagerUseCase {

    private final IUserRepository userRepository;
    private final IManagerRepository managerRepository;
    private final IObjectMapper objectMapper;

    public RegisterManagerUseCase(IUserRepository userRepository, IManagerRepository managerRepository, IObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
        this.objectMapper = objectMapper;
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

        Manager newManager = objectMapper.parseObject(requestDTO, Manager.class);
        Manager savedUser = managerRepository.save(newManager);

        return objectMapper.parseObject(savedUser, ManagerResponseDTO.class);
    }
}
