package br.ufscar.pooa.cinema_api.features.theaters;

import br.ufscar.pooa.cinema_api.features.theaters.RowDTO;
import br.ufscar.pooa.cinema_api.domain.entities.Row;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ISeatMapper.class})
public interface IRowMapper {
    RowDTO toRowDTO(Row row);
}
