package com.digital.model;

import com.digital.model.common.BaseEntity;

import javax.persistence.*;

/**
 * @author babacoul
 */
@Entity
public class ConnectionEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(name = "user_agent", nullable = false, updatable = false)
    private String userAgent;

    private String device;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User user;

    public ConnectionEvent() {

    }

    public ConnectionEvent(User user, String username, String userAgent, String device) {
        this.username = username;
        this.userAgent = userAgent;
        this.user = user;
        this.device = device;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
