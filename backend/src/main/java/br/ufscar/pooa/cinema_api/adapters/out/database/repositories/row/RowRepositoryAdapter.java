package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.row;

import br.ufscar.pooa.cinema_api.application.ports.out.repository.IRowRepository;
import br.ufscar.pooa.cinema_api.domain.Row;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RowRepositoryAdapter implements IRowRepository {
    private final RowJpaRepository rowJpaRepository;

    public RowRepositoryAdapter(RowJpaRepository rowJpaRepository) {
        this.rowJpaRepository = rowJpaRepository;
    }

    @Override
    public Row save(Row row) {
        if (row == null) {
            throw new IllegalArgumentException("Row cannot be null");
        }
        if (row.getId() != null) {
            throw new IllegalArgumentException("Row ID must be null for a new row");
        }

        return rowJpaRepository.save(row);
    }

    @Override
    public Optional<Row> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return rowJpaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        rowJpaRepository.deleteById(id);

    }
}