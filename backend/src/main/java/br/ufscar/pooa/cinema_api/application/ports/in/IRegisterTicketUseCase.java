package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.application.dtos.ticket.RegisterTicketRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.ticket.TicketResponseDTO;

public interface IRegisterTicketUseCase {
    TicketResponseDTO execute(RegisterTicketRequestDTO requestDTO);

}
