package com.example.carrentservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rents {
    public Rents(Integer userId, Date rentDay, Integer leaseDuration) {
        this.userId = userId;
        this.rentDay = rentDay;
        this.leaseDuration = leaseDuration;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private Integer userId;

    @Getter
    @Setter
    private Date rentDay;

    @Getter
    @Setter
    private Integer leaseDuration;
}
