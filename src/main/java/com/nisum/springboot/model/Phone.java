package com.nisum.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "number")
    private String number;
    @Column(name = "cityCode")
    private String cityCode;
    @Column(name = "countryCode")
    private String countryCode;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
