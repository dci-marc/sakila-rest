package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "customer", schema = "sakila", indexes = {
    @Index(name = "idx_fk_store_id", columnList = "store_id"),
    @Index(name = "idx_last_name", columnList = "last_name"),
    @Index(name = "idx_fk_address_id", columnList = "address_id")
})
public class Customer {
  @Id
  @Column(name = "customer_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "store_id", nullable = false)
  private @NotNull Store store;

  @Column(name = "first_name", nullable = false, length = 45)
  private @NotNull String firstName;

  @Column(name = "last_name", nullable = false, length = 45)
  private @NotNull String lastName;

  @Column(name = "email", length = 50)
  private @NotNull String email;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "address_id", nullable = false)
  private @NotNull Address address;

  @ColumnDefault("1")
  @Column(name = "active", nullable = false)
  private @NotNull Boolean active = false;

  @Column(name = "create_date", nullable = false)
  private @NotNull Instant createDate;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update")
  private @NotNull Instant lastUpdate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public Instant getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

}