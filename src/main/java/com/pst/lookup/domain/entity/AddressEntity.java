package com.pst.lookup.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AddressEntity {

  @Column(name = "country", length = 64)
  private String country;

  @Column(name = "city", length = 64)
  private String city;

  @Column(name = "state", length = 64)
  private String state;

  @Column(name = "street", length = 64)
  private String street;

  @Column(name = "zip_code", length = 10)
  private String zipCode;

  @Column(name = "language_id")
  private Long languageId;

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public Long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Long languageId) {
    this.languageId = languageId;
  }
}
