package com.github.fredO1211.BookingBackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    @ApiModelProperty(hidden = true)
    private Long id;
    @NotBlank(message = MessageProvider.PAYMENT_EMPTY_CODE_MSG)
    private String code;
    @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_NIGHT_COST_MSG)
    private int costPerNight;
    @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_DISCOUNT_MSG)
    private int discount;
    @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_ADVANCE_MSG)
    private int advanceSize;
    @Value("false")
    private boolean isAdvancePaid;
    @Future(message = MessageProvider.PAYMENT_DEADLINE_IN_PAST_MSG)
    private LocalDate deadlineForPayment;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Payment(@NotBlank(message = MessageProvider.PAYMENT_EMPTY_CODE_MSG) String code,
                   @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_NIGHT_COST_MSG) int costPerNight,
                   @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_DISCOUNT_MSG) int discount,
                   @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_ADVANCE_MSG) int advanceSize,
                   LocalDate deadlineForPayment) {
        this.code = code;
        this.costPerNight = costPerNight;
        this.discount = discount;
        this.advanceSize = advanceSize;
        this.deadlineForPayment = deadlineForPayment;
    }
}
