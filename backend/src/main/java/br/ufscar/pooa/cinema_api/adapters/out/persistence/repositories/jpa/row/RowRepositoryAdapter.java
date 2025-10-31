package br.ufscar.pooa.cinema_api.adapters.out.persistence.repositories.jpa.row;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.RowEntity;
import br.ufscar.pooa.cinema_api.application.ports.out.mapper.IObjectMapper;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IRowRepository;
import br.ufscar.pooa.cinema_api.domain.Row;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RowRepositoryAdapter implements IRowRepository {
    private final RowJpaRepository rowJpaRepository;
    private final IObjectMapper objectMapper;

    public RowRepositoryAdapter(RowJpaRepository rowJpaRepository, IObjectMapper objectMapper) {
        this.rowJpaRepository = rowJpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Row save(Row row) {
        if (row == null) {
            throw new IllegalArgumentException("Row cannot be null");
        }
        if (row.getId() != null) {
            throw new IllegalArgumentException("Row ID must be null for a new row");
        }

        RowEntity entityToSave = objectMapper.parseObject(row, RowEntity.class);
        RowEntity savedEntity = rowJpaRepository.save(entityToSave);
        return objectMapper.parseObject(savedEntity, Row.class);
    }

    @Override
    public Optional<Row> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return rowJpaRepository.findById(id)
                .map(entity -> objectMapper.parseObject(entity, Row.class));
    }

    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        rowJpaRepository.deleteById(id);

    }
}
