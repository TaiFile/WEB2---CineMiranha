package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.row.RowDTO;
import br.ufscar.pooa.cinema_api.domain.Row;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ISeatMapper.class})
public interface IRowMapper {
    RowDTO toRowDTO(Row row);
}
