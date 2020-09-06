package com.example.securityDemo.Models.Database;

import com.sun.istack.NotNull;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "persistent_logins")
public class RememberMePersistent {

    @NotNull
    private String username;

    @Id
    @NotNull
    private String series;

    @NotNull
    private String token;

    @NotNull
    private Timestamp last_used;

    private String device;

    public RememberMePersistent(String username, String series, String token) {
        this.username = username;
        this.series = series;
        this.token = token;
        this.last_used = new Timestamp(System.currentTimeMillis());
    }

    public RememberMePersistent() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getLast_used() {
        return last_used;
    }

    public void setLast_used(Timestamp last_used) {
        this.last_used = last_used;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
