package com.nisum.onlineRegistro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "client")
@NamedQueries({
        @NamedQuery(name = "Client.findClientByEmail", query="select o from Client o where o.email = ?1"),
        @NamedQuery(name = "Client.findByClientId", query="select o from Client o where o.id = ?1")
})
public class Client {
    public Client() {
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="isActive")
    private String isActive;


    @OneToMany(mappedBy = "client", cascade = { CascadeType.ALL })
    private List<Phone> phones;

    //audit fields
    @Column(name="token")
    private String token;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name="created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name="modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name="last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_login;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive='" + isActive + '\'' +
                ", phones=" + phones +
                ", token='" + token + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", last_login=" + last_login +
                '}';
    }

    @Transient
    public boolean modified(Client client){
        boolean modified = false;

        if(!this.getName().equals(client.getName())){
            modified = true;
            this.setName(client.getName());
        }

        if(!this.getEmail().equals(client.getEmail())){
            modified = true;
            this.setEmail(client.getEmail());
        }

        if(!this.getPassword().equals(client.getPassword())){
            modified = true;
            this.setPassword(client.getPassword());
        }

        return modified;
    }

}
