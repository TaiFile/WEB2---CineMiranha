package br.ufscar.pooa.cinema_api.application.usecases.client;

import br.ufscar.pooa.cinema_api.application.dtos.client.ClientResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.client.RegisterClientRequestDTO;
import br.ufscar.pooa.cinema_api.application.exceptions.ResourceAlreadyExistsException;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterClientUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IClientRepository;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IUserRepository;
import br.ufscar.pooa.cinema_api.domain.Client;
import br.ufscar.pooa.cinema_api.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterClientUseCase implements IRegisterClientUseCase {

    private final IUserRepository userRepository;
    private final IClientRepository clientRepository;
    private final IObjectMapper objectMapper;

    public RegisterClientUseCase(IUserRepository userRepository, IClientRepository clientRepository, IObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.objectMapper = objectMapper;
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

        Client newClient = objectMapper.parseObject(requestDTO, Client.class);
        Client savedUser = clientRepository.save(newClient);

        return objectMapper.parseObject(savedUser, ClientResponseDTO.class);
    }
}