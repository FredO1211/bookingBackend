package com.github.fredO1211.BookingBackend.messageprovider;

public final class MessageProvider {
    //General
    public static final String START_IN_PAST_MSG = "Start of booking cannot be in the past";
    public static final String END_IN_PAST_MSG = "End of booking cannot be in the past";
    public static final String START_BEFORE_END_MSG = "Start of booking cannot be before end";
    public static final String EMPTY_NAME_MSG = "Name cannot be empty";
    public static final String ID_DOES_NOT_EXIST_MSG = "Current id doesn't exists";
    //Booking
    public static final String BOOKING_NEGATIVE_GUEST_COUNT_MSG = "Count of guests cannot be negative!";
    public static final String BOOKING_DISCOUNT_HIGHER_THAN_STAY_COST_MSG = "Discount value cannot be higher than cost of stay!";
    public static final String BOOKING_ADVANCE_HIGHER_THAN_STAY_COST_MSG = "Advance value cannot be higher than cost of stay!";
    //Exceptions
    public static final String INSUFFICIENT_DATA_EXCEPTION_MSG = "The data provided is insufficient to create the object";
    public static final String UNAVAILABLE_CODE_EXCEPTION_MSG = "Current code is already in use!";
    public static final String UNAVAILABLE_DATE_EXCEPTION_MSG = "Current term is already taken";
    public static final String UNAVAILABLE_NAME_EXCEPTION_MSG = "Current facility name is already exists!";
    //Facility
    public static final String FACILITY_NEGATIVE_RENT_AMOUNT_MSG = "Rent amount cannot be negative!";
    //Payment
    public static final String PAYMENT_DEADLINE_IN_PAST_MSG = "Payment deadline cannot be set in the past!";
    public static final String PAYMENT_EMPTY_CODE_MSG = "Payment code cannot be empty";
    public static final String PAYMENT_NEGATIVE_NIGHT_COST_MSG = "Cost per night cannot be negative!";
    public static final String PAYMENT_NEGATIVE_DISCOUNT_MSG = "Discount cannot be negative!";
    public static final String PAYMENT_NEGATIVE_ADVANCE_MSG = "Advance size cannot be negative!";
    public static final String INVALID_EMAIL_FORMAT_MSG = "Invalid format of email";
    public static final String GUEST_NOT_EXISTS_MSG = "Current guest doesn't exist!";
    public static final String GUEST_INCORRECT_DATA_WITH_ID = "Current id exists but other data doesn't match";
    public static final String PAYMENT_NOT_FOUND = "Payment with the given id wasn't found.";
    public static final String NO_MAILS_FOUND_MSG = "No e-mails in the database.";
    //Mails
    public static final String MAIL_MSG = "Potwierdzam rezerwację.";
    public static final String MAIL_TITLE = "Potwierdzenie rezerwacji.";

    public static final String USER_NOT_FOUND_MSG = "Current user does not exist";
}
