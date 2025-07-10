package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "customer_list", schema = "sakila")
public class CustomerList {
  @Id
  @Column(name = "ID", columnDefinition = "int UNSIGNED not null")
  private Long id;

  @Column(name = "name", length = 91)
  private String name;

  @Column(name = "address", nullable = false, length = 50)
  private String address;

  @Column(name = "`zip code`", length = 10)
  private String zipCode;

  @Column(name = "phone", nullable = false, length = 20)
  private String phone;

  @Column(name = "city", nullable = false, length = 50)
  private String city;

  @Column(name = "country", nullable = false, length = 50)
  private String country;

  @Column(name = "notes", nullable = false, length = 6)
  private String notes;

  @Column(name = "SID", columnDefinition = "int UNSIGNED not null")
  private Long sid;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getZipCode() {
    return zipCode;
  }

  public String getPhone() {
    return phone;
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public String getNotes() {
    return notes;
  }

  public Long getSid() {
    return sid;
  }

  protected CustomerList() {
  }
}