package com.digital.model;

import javax.persistence.Index;
import javax.persistence.Table;

import com.digital.model.common.BaseEntity;
import com.digital.model.common.Person;
import com.digital.model.tchat.Message;
import com.digital.util.EncryptionUtility;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author babacoul
 */
@Entity
@Table(name = "user_account",
        indexes = {@Index(name = "i_user_account_email", columnList = "email", unique = true),
                @Index(name = "i_user_account_api_key", columnList = "apiKey", unique = true)})
public class User extends Person implements UserDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseEntity.class);

    private static DatabaseReader databaseReader;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @Column(name = "created_by")
    @CreatedBy
    protected String createdBy;

    @Column(name = "updated_by")
    @LastModifiedBy
    protected String updatedBy;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;

    @Column(name = "created_ip")
    protected String createdIp;

    @Column(name = "updated_ip")
    protected String updatedIp;

    @Column(name = "created_country")
    protected String createdCountry;

    @Column(name = "updated_country")
    protected String updatedCountry;

    @Column(name = "created_country_iso")
    protected String createdCountryISO;

    @Column(name = "updated_country_iso")
    protected String updatedCountryISO;

    public User(String username, String password, Date createdDate) {
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    protected void onPrePersist() {
        try {
            Optional<HttpServletRequest> requestOptional = Optional.ofNullable(
                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                            .getRequest());

            requestOptional.ifPresent(request -> {
                createdIp = updatedIp = getClientIP(request);
                getClientCountry()
                        .ifPresent(country -> {
                            createdCountry = updatedCountry = country.getName();
                            createdCountryISO = updatedCountryISO = country.getIsoCode();
                            createdDate = updatedDate = new Date();
                        });
            });
        } catch (IllegalStateException e) {
            LOGGER.error(
                    String.format("IllegalStateException --> %s", Arrays.toString(e.getStackTrace())));
        }
        apiKey = EncryptionUtility.sha256From(UUID.randomUUID().toString()).orElse("");

    }

    @PreUpdate
    protected void onPreUpdate() {
        try {
            Optional<HttpServletRequest> requestOptional = Optional.ofNullable(
                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                            .getRequest());

            requestOptional.ifPresent(request -> {
                updatedIp = request.getRemoteAddr();
                getClientCountry()
                        .ifPresent(country -> {
                            updatedCountry = country.getName();
                            updatedCountryISO = country.getIsoCode();
                        });
            });
        } catch (IllegalStateException e) {
            LOGGER.error("IllegalStateException / onPreUpdate", Arrays.toString(e.getStackTrace()));
        }

    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    private Optional<Country> getClientCountry() {
        Country country = null;

        if (databaseReader == null) {
            File resource = new File("src/main/resources/geolite2-country.mmdb");
            try {
                databaseReader = new DatabaseReader.Builder(resource).build();
            } catch (IOException e) {
                LOGGER.error(
                        String.format("DatabaseReader Construction --> %s",
                                Arrays.toString(e.getStackTrace())));
            }
        }

        if (databaseReader != null) {
            try {
                CountryResponse countryResponse = databaseReader.country(InetAddress.getByName(createdIp));
                country = countryResponse.getCountry();
            } catch (IOException | GeoIp2Exception e) {
                LOGGER.error(
                        String.format("IOException | GeoIp2Exception --> %s",
                                Arrays.toString(e.getStackTrace())));
            }
        }

        return Optional.ofNullable(country);
    }


    @OneToMany(mappedBy = "souscripteur",fetch = FetchType.LAZY)
    private Collection<Contrat> contrats;

    @OneToMany(mappedBy = "expediteur",fetch = FetchType.LAZY)
    private Collection<Message> messagesEnvoyes;

    @OneToMany(mappedBy = "destinataire",fetch = FetchType.LAZY)
    private Collection<Message> messagesRecu;

    @Column(name = "nbreMessageNonLu",nullable = false,columnDefinition = "int default 0")
    private int messageNonLu;

    public int getMessageNonLu() {
        for (Message m: this.getMessagesEnvoyes()){
            if (!m.isState()){
                messageNonLu ++;
            }
        }
        return messageNonLu;
    }

    //@NotNull
    //@Pattern(regexp = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
  /*  @Column(nullable = true)
    private String email;
    */

    private String username;

    @Column(nullable = false, length = 1000)
    private String password;

    private String state; //statut

    @Transient
    private String passwordConfirmation;

    @Column(nullable = false, updatable = false)
    private String apiKey;

    private String confirmationToken;

    private String changePasswordToken;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date activationDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date changePasswordDate;

    @Column(nullable = false)
    private boolean locked;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private boolean expired;

    @Column(nullable = false)
    private boolean credentialsExpired;

    @Transient
    @JsonIgnore
    private Collection<GrantedAuthority> authorities;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = ConnectionEvent.class, mappedBy = "user")
    @JsonIgnore
    private Set<ConnectionEvent> connectionEvents;

    public User() {
        System.out.println("appelle du constructeur user sans parametre ");
        System.out.println("initialisation des date");
        createdDate = updatedDate = new Date();
        System.out.println("createdDate :"+createdDate);
        System.out.println("updatedDate :"+updatedDate);
    }

    public User(String firstname, String lastname, String email, String password) {
        super(firstname, lastname);
        /*        this.email = email;*/
        createdDate = updatedDate = new Date();
        this.password = password;
    }

    public User(String civility, String firstName, String lastName, Date birthDate, String phone, String email, String password, String cpassword) {
        this.civilite = civility;
        this.firstname = firstName;
        this.lastname = lastName;
        this.birthdate = birthDate;
        this.phone = phone;
        this.password = password;
        createdDate = updatedDate = new Date();
/*
               this.email = email;
*/
// this.password =password;
        this.passwordConfirmation = cpassword;
    }

    @JsonIgnore
    public Collection<Contrat> getContrats() {
        return contrats;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setContrats(Collection<Contrat> contrats) {
        this.contrats = contrats;
    }

    public User(String firstname, String lastname, String email, String password,
                Set<Role> roles) {
        this(firstname, lastname, email, password);
        this.roles = roles;
    }

    /**
     * ----------- Getter & Setters Methods -----------
     */


/*
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void cleanConfirmationToken() {
        this.confirmationToken = null;
    }

    public String generateConfirmationToken() {
        confirmationToken = EncryptionUtility.sha256From(UUID.randomUUID().toString()).orElse("");
        return confirmationToken;
    }

    public String getChangePasswordToken() {
        return changePasswordToken;
    }

    public void cleanChangePasswordToken() {
        this.changePasswordToken = null;
    }

    public String generateChangePasswordToken() {
        changePasswordToken = EncryptionUtility.sha256From(UUID.randomUUID().toString()).orElse("");
        return changePasswordToken;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getChangePasswordDate() {
        return changePasswordDate;
    }

    public void setChangePasswordDate(Date changePasswordDate) {
        this.changePasswordDate = changePasswordDate;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<ConnectionEvent> getConnectionEvents() {
        return connectionEvents;
    }

    public void setConnectionEvents(Set<ConnectionEvent> connectionEvents) {
        this.connectionEvents = connectionEvents;
    }

    public Collection<Message> getMessagesEnvoyes() {
        return messagesEnvoyes;
    }

    public void setMessagesEnvoyes(Collection<Message> messagesEnvoyes) {
        this.messagesEnvoyes = messagesEnvoyes;
    }

    public Collection<Message> getMessagesRecu() {
        return messagesRecu;
    }

    public void setMessagesRecu(Collection<Message> messagesRecu) {
        this.messagesRecu = messagesRecu;
    }

    /**
     * ----------- Implementation Methods -----------
     */



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = new HashSet<>();
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
        }
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override public String getUsername() {
        return username;
    }

    @Override public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}

