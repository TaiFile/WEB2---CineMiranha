package br.ufscar.pooa.cinema_api.features.clients.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Client;
import br.ufscar.pooa.cinema_api.domain.entities.User;
import br.ufscar.pooa.cinema_api.domain.repositories.client.IClientRepository;
import br.ufscar.pooa.cinema_api.domain.repositories.user.IUserRepository;
import br.ufscar.pooa.cinema_api.features._shared.exceptions.ResourceAlreadyExistsException;
import br.ufscar.pooa.cinema_api.features.clients.dto.ClientResponseDTO;
import br.ufscar.pooa.cinema_api.features.clients.dto.RegisterClientRequestDTO;
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
