package com.digital.model.common;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author babacoul
 */
@MappedSuperclass
public abstract class BaseEntity extends AuditableEntity<String> {

  private static final Logger LOGGER = LoggerFactory.getLogger(BaseEntity.class);

  private static DatabaseReader databaseReader;

  public BaseEntity() {

  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

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

            });
      });
    } catch (IllegalStateException e) {
      LOGGER.error(
          String.format("IllegalStateException --> %s", Arrays.toString(e.getStackTrace())));
    }
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
}
