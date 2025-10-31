package br.ufscar.pooa.cinema_api.application.ports.out.mapper;

import java.util.List;

public interface IObjectMapper {
    public <O, D> D parseObject(O origin, Class<D> destination);

    public <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination);
}
