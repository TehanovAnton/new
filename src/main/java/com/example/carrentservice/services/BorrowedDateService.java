package com.example.carrentservice.services;

import com.example.carrentservice.models.AvailableCarsResult;
import com.example.carrentservice.models.BorrowedDate;

import java.util.Calendar;
import java.util.List;

public interface BorrowedDateService {

    List<AvailableCarsResult> checkAvailableCarById(Calendar startDate, Calendar endDate, Long id);

    List<AvailableCarsResult> checkAvailableCars(Calendar startDate, Calendar endDate);

    List<BorrowedDate> findAll();

    BorrowedDate findByCustomerId(Long id);

    BorrowedDate findByCarId(Long id);

    void save(BorrowedDate borrowedDate);

    long countDays(BorrowedDate borrowedDate);
}
