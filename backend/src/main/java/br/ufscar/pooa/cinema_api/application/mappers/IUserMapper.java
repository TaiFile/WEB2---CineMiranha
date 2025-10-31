package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.user.RegisterUserRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.user.UserResponseDTO;
import br.ufscar.pooa.cinema_api.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserResponseDTO toUserResponseDTO(User user);
    User toUser(RegisterUserRequestDTO registerUserRequestDTO);
}