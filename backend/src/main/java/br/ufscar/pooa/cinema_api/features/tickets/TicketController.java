package br.ufscar.pooa.cinema_api.features.tickets;

import br.ufscar.pooa.cinema_api.features.tickets.RegisterTicketRequestDTO;
import br.ufscar.pooa.cinema_api.features.tickets.TicketResponseDTO;
import br.ufscar.pooa.cinema_api.features.tickets.IRegisterTicketUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final IRegisterTicketUseCase ticketUseCase;

    public TicketController(IRegisterTicketUseCase ticketUseCase) {
        this.ticketUseCase = ticketUseCase;
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody RegisterTicketRequestDTO registerRequestBody) {
        TicketResponseDTO response = ticketUseCase.execute(registerRequestBody);
        return ResponseEntity.created(URI.create("/tickets/" + response.getId())).body(response);
    }
}
