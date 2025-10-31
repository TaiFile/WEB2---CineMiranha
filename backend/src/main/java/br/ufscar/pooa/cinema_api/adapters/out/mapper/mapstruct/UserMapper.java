package br.ufscar.pooa.cinema_api.adapters.out.mapper.mapstruct;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.UserEntity;
import br.ufscar.pooa.cinema_api.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    User toDomain(UserEntity entity);
}