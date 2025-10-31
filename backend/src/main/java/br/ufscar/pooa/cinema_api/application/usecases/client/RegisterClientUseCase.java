package br.ufscar.pooa.cinema_api.application.usecases.client;

import br.ufscar.pooa.cinema_api.application.dtos.client.ClientResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.client.RegisterClientRequestDTO;
import br.ufscar.pooa.cinema_api.application.exceptions.ResourceAlreadyExistsException;
import br.ufscar.pooa.cinema_api.application.mappers.IClientMapper;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterClientUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IClientRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.Client;
import br.ufscar.pooa.cinema_api.domain.User;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterClientUseCase implements IRegisterClientUseCase {

    private final IUserRepository userRepository;
    private final IClientRepository clientRepository;
    private final IClientMapper IClientMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterClientUseCase(
        IUserRepository userRepository,
        IClientRepository clientRepository,
        IClientMapper IClientMapper,
        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.IClientMapper = IClientMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientResponseDTO execute(RegisterClientRequestDTO requestDTO) {
        Optional<User> userExists = userRepository.findByEmail(requestDTO.getEmail());
        if (userExists.isPresent()) {
            throw new ResourceAlreadyExistsException("User", "email", requestDTO.getEmail());
        }

        Optional<Client> clientExists = clientRepository.findByCpf(requestDTO.getCpf());
        if (clientExists.isPresent()) {
            throw new ResourceAlreadyExistsException("Client", "CPF", requestDTO.getCpf());
        }

        Client newClient = IClientMapper.toClient(requestDTO);
        newClient.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        Client savedUser = clientRepository.save(newClient);

        return IClientMapper.toClientResponseDTO(savedUser);
    }
}
