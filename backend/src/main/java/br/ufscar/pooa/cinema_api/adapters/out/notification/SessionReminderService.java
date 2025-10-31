package br.ufscar.pooa.cinema_api.adapters.out.notification;

import br.ufscar.pooa.cinema_api.adapters.out.notification.entities.NotificationEvent;
import br.ufscar.pooa.cinema_api.adapters.out.notification.entities.NotificationType;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.ISessionRepository;
import br.ufscar.pooa.cinema_api.domain.Session;
import br.ufscar.pooa.cinema_api.domain.Ticket;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SessionReminderService {
    private final ISessionRepository sessionRepository;
    private final SessionNotificationManager notificationManager;

    public SessionReminderService(ISessionRepository sessionRepository,
                                  SessionNotificationManager notificationManager) {
        this.sessionRepository = sessionRepository;
        this.notificationManager = notificationManager;
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void checkUpcomingSessions() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourLater = now.plusHours(1);

        List<Session> upcomingSessions = sessionRepository
                .findAllByDateBetween(oneHourLater, oneHourLater.plusMinutes(1));

        if (upcomingSessions.isEmpty()) {
            System.out.println("Sem lembretes para enviar.");
            System.out.println();
            return;
        }

        upcomingSessions.forEach(session -> System.out.println(createReminderMessage(session)));
        System.out.println();

        for (Session session : upcomingSessions) {
            sendSessionReminders(session);
        }
    }

    private void sendSessionReminders(Session session) {
        List<Ticket> tickets = session.getTickets();

        System.out.println(session);

        for (Ticket ticket : tickets) {
            if (ticket.getClient() != null) {
                String message = createReminderMessage(session);
                NotificationEvent event = new NotificationEvent(
                        NotificationType.SESSION_REMINDER,
                        message,
                        session,
                        ticket.getClient()
                );

                System.out.println("Notificando observers...");
                notificationManager.notifyObservers(event);
            }
        }
    }

    private String createReminderMessage(Session session) {
        return String.format(
                "Lembrete: Sua sessão do filme '%s' começará em 1 hora (%s). " +
                        "Sala: %s. Não se esqueça!",
                session.getMovie().getTitle(),
                session.getDate().toString(),
                session.getRoom().getName()
        );
    }
}