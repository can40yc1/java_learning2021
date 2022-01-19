package ru.mantis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String userName;
    @Column(name = "email")
    private String email;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id && Objects.equals(userName, userData.userName) && Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
