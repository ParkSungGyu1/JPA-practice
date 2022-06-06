package com.spring.jpaapp.model;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //어딘가에 내장될 수 있다.
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address(){}
    //setter는 제거하고 생성자에서 초기화한다.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
