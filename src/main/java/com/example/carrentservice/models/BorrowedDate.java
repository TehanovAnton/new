package com.example.carrentservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "borrowed_date")
public class BorrowedDate implements Serializable {
    private static final long serialVersionUID = -1713505055304086201L;

    public BorrowedDate() {
        super();
    }

    public BorrowedDate(Calendar startDate, Calendar endDate, Car car, Customer customer) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
        this.customer = customer;
    }

    @Id
    @Column(name = "borrowed_date_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Column(name = "start_date")
    @Getter
    @Setter
    private Calendar startDate;

    @Column(name = "end_date")
    @Getter
    @Setter
    private Calendar endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @Getter
    @Setter
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @Getter
    @Setter
    private Customer customer;

    public String startDateFormatted() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(startDate.getTime());
    }

    public String endDateFormatted() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(endDate.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedDate that = (BorrowedDate) o;
        return Objects.equals(id, that.id) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(car, that.car) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, car, customer);
    }
}
