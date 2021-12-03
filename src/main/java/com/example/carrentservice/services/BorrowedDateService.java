package com.example.carrentservice.services;

import com.example.carrentservice.models.AvailableCarsResult;
import com.example.carrentservice.models.BorrowedDate;
import com.example.carrentservice.models.Car;
import com.example.carrentservice.repository.BorrowedDateDAO;
import com.example.carrentservice.repository.CarDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BorrowedDateService{

    private BorrowedDateDAO borrowedDateDAO;

    private CarDAO carDAO;

    public BorrowedDateService(BorrowedDateDAO borrowedDateDAO, CarDAO carDAO) {
        this.borrowedDateDAO = borrowedDateDAO;
        this.carDAO = carDAO;
    }

    
    public BorrowedDate findByCustomerId(Long id) {
        return borrowedDateDAO.findByCustomerId(id);
    }

    
    public BorrowedDate findByCarId(Long id) {
        return borrowedDateDAO.findByCarId(id);
    }

    
    public List<BorrowedDate> findAll() {
        return borrowedDateDAO.findAll();
    }

    
    public List<BorrowedDate> findAllByCustomerId(Long id) { return borrowedDateDAO.findAllByCustomerId(id); }

    
    public void save(BorrowedDate borrowedDate) {
        borrowedDateDAO.save(borrowedDate);
    }

    
    public List<AvailableCarsResult> checkAvailableCars(Calendar startDate, Calendar endDate) {
        List<Long> carsId = borrowedDateDAO.checkAvailableCars(startDate, endDate);
        List<AvailableCarsResult> availableFromBorrowed = AvailableCarsList(carsId);

        List<Long> newCarsId = carDAO.newCarsId();
        List<AvailableCarsResult> availableFromNew = AvailableCarsList(newCarsId);

        return availableFromNew.size() != 0 ? availableFromNew : availableFromBorrowed;
    }

    
    public List<AvailableCarsResult> checkAvailableCarById(Calendar startDate, Calendar endDate, Long id) {
        List<Long> borrowedCarsId = borrowedDateDAO.checkAvailableCarById(startDate, endDate, id);
        List<AvailableCarsResult> availableFromBorrowed = AvailableCarsList(borrowedCarsId);

        List<Long> newCarsId = carDAO.newCarsId();
        List<AvailableCarsResult> availableFromNew = AvailableCarsList(newCarsId);

        return availableFromNew.size() != 0 ? availableFromNew : availableFromBorrowed;
    }

    private List<AvailableCarsResult> AvailableCarsList(List<Long> cars_id) {
        List<AvailableCarsResult> AvailableCars = new ArrayList<AvailableCarsResult>();

        for(Integer i = 0; i < cars_id.size(); i++) {
            Car c = carDAO.findById(cars_id.get(i)).get();
            AvailableCarsResult a = new AvailableCarsResult(Long.valueOf(i), c.getId(), c.getName(), c.getDescription(), c.getPrice());
            AvailableCars.add(a);
        }

        return AvailableCars;
    }

    
    public long countDays(BorrowedDate borrowedDate) {
        long days;
        Calendar start = borrowedDate.getStartDate();
        Calendar end = borrowedDate.getEndDate();
        days = daysBetween(start, end);
        return days;
    }

    private long daysBetween(Calendar startDate, Calendar endDate) {
        endDate.add(Calendar.DATE, 1);
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }
}
