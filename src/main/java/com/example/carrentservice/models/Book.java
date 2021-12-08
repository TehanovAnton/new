package com.example.carrentservice.models;

public class Book {
    public String getStartDate() {
        return startDate;
    }

    public Book() {
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Book(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String startDate;

    private String endDate;
}
