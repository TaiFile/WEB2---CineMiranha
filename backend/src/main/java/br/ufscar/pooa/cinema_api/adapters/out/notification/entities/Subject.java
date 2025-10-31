package br.ufscar.pooa.cinema_api.adapters.out.notification.entities;

import java.util.List;

public interface Subject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers(NotificationEvent event);
    List<Observer> getObservers();
}