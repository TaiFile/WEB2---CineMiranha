package br.ufscar.pooa.cinema_api.adapters.out.notification.entities;

import br.ufscar.pooa.cinema_api.domain.Session;
import br.ufscar.pooa.cinema_api.domain.Client;
import java.time.LocalDateTime;

public class NotificationEvent {
    private final NotificationType type;
    private final String message;
    private final Session session;
    private final Client client;
    private final LocalDateTime timestamp;

    public NotificationEvent(NotificationType type, String message, Session session, Client client) {
        this.type = type;
        this.message = message;
        this.session = session;
        this.client = client;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public NotificationType getType() { return type; }
    public String getMessage() { return message; }
    public Session getSession() { return session; }
    public Client getClient() { return client; }
    public LocalDateTime getTimestamp() { return timestamp; }
}