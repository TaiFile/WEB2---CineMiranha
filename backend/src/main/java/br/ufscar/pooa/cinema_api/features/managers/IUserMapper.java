package br.ufscar.pooa.cinema_api.features.managers;

import br.ufscar.pooa.cinema_api.features.admins.RegisterUserRequestDTO;
import br.ufscar.pooa.cinema_api.features.admins.UserResponseDTO;
import br.ufscar.pooa.cinema_api.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserResponseDTO toUserResponseDTO(User user);
    User toUser(RegisterUserRequestDTO registerUserRequestDTO);
}
