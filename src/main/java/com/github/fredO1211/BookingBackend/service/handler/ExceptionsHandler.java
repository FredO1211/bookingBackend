package com.github.fredO1211.BookingBackend.service.handler;

import com.github.fredO1211.BookingBackend.service.dto.ErrorDTO;
import com.github.fredO1211.BookingBackend.service.exception.EntityNotFoundException;
import com.github.fredO1211.BookingBackend.service.exception.IncorrectInputDataException;
import com.github.fredO1211.BookingBackend.service.exception.NoMailsFoundException;
import com.github.fredO1211.BookingBackend.service.exception.UnavailableCodeException;
import com.github.fredO1211.BookingBackend.service.exception.UnavailableDateException;
import com.github.fredO1211.BookingBackend.service.exception.UnavailableNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException(IncorrectInputDataException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException(UnavailableCodeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException(UnavailableNameException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException(UnavailableDateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException(NoMailsFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
    }
}
