package com.nisum.onlineRegistro.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "phone")
public class Phone {

    public Phone() {
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name="id")
    private String id;

    @Column(name="number")
    private String number;

    @Column(name="citycode")
    private String citycode;

    @Column(name="contrycode")
    private String contrycode;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getContrycode() {
        return contrycode;
    }

    public void setContrycode(String contrycode) {
        this.contrycode = contrycode;
    }

    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", citycode='" + citycode + '\'' +
                ", contrycode='" + contrycode + '\'' +
                ", client=" + client +
                '}';
    }

    @Transient
    public boolean modified(Phone phone){
        boolean modified = false;

        if(!this.getNumber().equals(phone.getNumber())){
            modified = true;
            this.setNumber(phone.getNumber());
        }

        if(!this.getCitycode().equals(phone.getCitycode())){
            modified = true;
            this.setCitycode(phone.getCitycode());
        }

        if(!this.getContrycode().equals(phone.getContrycode())){
            modified = true;
            this.setContrycode(phone.getContrycode());
        }

        return modified;
    }

}
