package br.ufscar.pooa.cinema_api.adapters.out.database.repositories.client;

import br.ufscar.pooa.cinema_api.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCpf(String cpf);
}