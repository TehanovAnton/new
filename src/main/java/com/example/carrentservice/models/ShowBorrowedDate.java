package com.example.carrentservice.models;

import com.example.carrentservice.repository.BorrowedDateDAO;
import lombok.Getter;
import lombok.Setter;

public class ShowBorrowedDate {

    public ShowBorrowedDate(BorrowedDate date, Car car) {
        this.date = date;
        this.car = car;
    }

    @Getter
    @Setter
    private BorrowedDate date;

    @Getter
    @Setter
    private Car car;
}
