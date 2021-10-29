package com.github.fredO1211.BookingBackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    @ApiModelProperty(hidden = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;
    @Future(message = MessageProvider.START_IN_PAST_MSG)
    private LocalDate startOfBooking;
    @Future(message = MessageProvider.END_IN_PAST_MSG)
    private LocalDate endOfBooking;
    private String description;
    private int countOfGuests;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    public Booking(Guest guest, LocalDate startOfBooking, LocalDate endOfBooking, String description, Payment payment, Facility facility) {
        this.guest = guest;
        this.startOfBooking = startOfBooking;
        this.endOfBooking = endOfBooking;
        this.description = description;
        this.payment = payment;
        this.facility = facility;
    }

    @JsonIgnore
    public int getStayLength() {
        return (int) startOfBooking.datesUntil(endOfBooking).count();
    }
}
