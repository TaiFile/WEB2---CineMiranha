package br.ufscar.pooa.cinema_api.domain;

import br.ufscar.pooa.cinema_api.domain.enums.PaymentMethod;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

public class Ticket {
	private Long id;
	private Instant paymentDate;
	private Integer priceInCents;
	private PaymentMethod paymentMethod;
	private Session session;
	private Client client;
	private Seat seat;

	public Ticket() {
	}

	public Ticket(Instant paymentDate, Integer priceInCents, Session session, Client client, Seat seat, PaymentMethod paymentMethod) {
		this.paymentDate = paymentDate;
		this.priceInCents = priceInCents;
		this.session = session;
		this.client = client;
		this.seat = seat;
		this.paymentMethod = paymentMethod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Instant paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getPriceInCents() {
		return priceInCents;
	}

	public void setPriceInCents(Integer priceInCents) {
		this.priceInCents = priceInCents;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Ticket ticket = (Ticket) o;
		return Objects.equals(paymentDate, ticket.paymentDate) && Objects.equals(priceInCents, ticket.priceInCents) && Objects.equals(session, ticket.session) && Objects.equals(client, ticket.client) && Objects.equals(seat, ticket.seat) && Objects.equals(paymentMethod, ticket.paymentMethod);
	}

	@Override
	public int hashCode() {
		return Objects.hash(paymentDate, priceInCents, session, client, seat, paymentMethod);
	}

	@Override
	public String toString() {
		return "Ticket{" +
				"id=" + id +
				", paymentDate=" + paymentDate +
				", priceInCents=" + priceInCents +
				", paymentMethod=" + paymentMethod +
				'}';
	}
}