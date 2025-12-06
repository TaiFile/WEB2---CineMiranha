package br.ufscar.pooa.cinema_api.features.tickets;

import br.ufscar.pooa.cinema_api.features.tickets.RegisterTicketRequestDTO;
import br.ufscar.pooa.cinema_api.features.tickets.TicketResponseDTO;

public interface IRegisterTicketUseCase {
    TicketResponseDTO execute(RegisterTicketRequestDTO requestDTO);

}
