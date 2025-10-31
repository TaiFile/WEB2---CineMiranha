package br.ufscar.pooa.cinema_api.adapters.out.persistence.entities;

import br.ufscar.pooa.cinema_api.domain.enums.PaymentMethod;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Instant paymentDate;

    @Column
    private Integer priceInCents;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private SeatEntity seat;

    public TicketEntity() {}

    public Long getId() {
        return id;
    }

    public TicketEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public TicketEntity setPaymentDate(Instant paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public TicketEntity setPriceInCents(Integer priceInCents) {
        this.priceInCents = priceInCents;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public TicketEntity setPaymentMethod(PaymentMethod paymentMethods) {
        this.paymentMethod = paymentMethods;
        return this;
    }

    public SessionEntity getSession() {
        return session;
    }

    public TicketEntity setSession(SessionEntity session) {
        this.session = session;
        return this;
    }

    public ClientEntity getClient() {
        return client;
    }

    public TicketEntity setClient(ClientEntity client) {
        this.client = client;
        return this;
    }

    public SeatEntity getSeat() {
        return seat;
    }

    public TicketEntity setSeat(SeatEntity seat) {
        this.seat = seat;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getPaymentDate(), that.getPaymentDate()) && Objects.equals(getPriceInCents(), that.getPriceInCents()) && getPaymentMethod() == that.getPaymentMethod();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPaymentDate(), getPriceInCents(), getPaymentMethod());
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", priceInCents=" + priceInCents +
                ", paymentMethods=" + paymentMethod +
                '}';
    }
}
