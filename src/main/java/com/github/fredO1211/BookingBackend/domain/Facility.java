package com.github.fredO1211.BookingBackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@EqualsAndHashCode
@Table(name = "facilities")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    @EqualsAndHashCode.Exclude
    @ApiModelProperty(hidden = true)
    private Long id;
    @NotBlank(message = MessageProvider.EMPTY_NAME_MSG)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facility")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Booking> bookings;
    @PositiveOrZero(message = MessageProvider.FACILITY_NEGATIVE_RENT_AMOUNT_MSG)
    private Integer basicRentAmount;

    public Facility(@NotBlank String name, @PositiveOrZero int basicRentAmount) {
        this.name = name;
        this.basicRentAmount = basicRentAmount;
    }
}
