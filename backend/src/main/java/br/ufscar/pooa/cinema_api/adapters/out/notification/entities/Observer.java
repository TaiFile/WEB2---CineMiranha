package br.ufscar.pooa.cinema_api.adapters.out.notification.entities;

public interface Observer {
    void update(NotificationEvent event);
}