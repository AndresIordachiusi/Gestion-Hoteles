package com.hotel.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.hotel.hotel.model.*;

@Entity
@Table(name = "valoracions")
public class Valoracio {



@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private int nota;

private String comentari;

private String email;

@Column (nullable = false)
private boolean llegit = false;

@Column (nullable = false)
private LocalDate data_creacio; //  = LocalDate.now();

@Column (nullable = false)
private LocalTime hora_creacio; // = LocalTime.now();


@OneToOne
private Client client;


public Valoracio() {
}


public Valoracio(Long id, int nota, String comentari, String email, boolean llegit, LocalDate data_creacio,
        LocalTime hora_creacio, Client client) {
    this.id = id;
    this.nota = nota;
    this.comentari = comentari;
    this.email = email;
    this.llegit = llegit;
    this.data_creacio = data_creacio;
    this.hora_creacio = hora_creacio;
    this.client = client;
}


public Long getId() {
    return id;
}


public void setId(Long id) {
    this.id = id;
}


public int getNota() {
    return nota;
}


public void setNota(int nota) {
    this.nota = nota;
}


public String getComentari() {
    return comentari;
}


public void setComentari(String comentari) {
    this.comentari = comentari;
}


public String getEmail() {
    return email;
}


public void setEmail(String email) {
    this.email = email;
}


public boolean isLlegit() {
    return llegit;
}


public void setLlegit(boolean llegit) {
    this.llegit = llegit;
}


public LocalDate getData_creacio() {
    return data_creacio;
}


public void setData_creacio(LocalDate data_creacio) {
    this.data_creacio = data_creacio;
}


public LocalTime getHora_creacio() {
    return hora_creacio;
}


public void setHora_creacio(LocalTime hora_creacio) {
    this.hora_creacio = hora_creacio;
}


public Client getClient() {
    return client;
}


public void setClient(Client client) {
    this.client = client;
}














    
}
