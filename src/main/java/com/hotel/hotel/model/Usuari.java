package com.hotel.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuaris")
public class Usuari {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String email;

    private String password;

    private boolean actiu;

    private String rol; // ADMIN_ROLE, CLIENT_ROLE, EMPLEAT_ROLE



    @OneToOne(mappedBy = "usuari")
    private Persona persona;





    public Usuari() {
    }
        
    public Usuari(Long id, String username, String email, String password, boolean actiu, String rol, Persona persona) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.actiu = actiu;
        this.rol = rol;
        this.persona = persona;
    }



        

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isActiu() {
        return actiu;
    }
    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }


    



}
