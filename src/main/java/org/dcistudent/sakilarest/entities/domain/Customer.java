package org.dcistudent.sakilarest.entities.domain;

import jakarta.persistence.*;
import org.dcistudent.sakilarest.entities.shared.AbstractUuidEntity;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "customer", schema = "sakila", indexes = {
    @Index(name = "idx_fk_store_id", columnList = "store_id"),
    @Index(name = "idx_last_name", columnList = "last_name"),
    @Index(name = "idx_fk_address_id", columnList = "address_id"),
    @Index(name = "idx_uuid", columnList = "uuid")
})
public class Customer extends AbstractUuidEntity implements Serializable {
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

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull Store getStore() {
    return this.store;
  }

  public @NotNull String getFirstName() {
    return this.firstName;
  }

  public @NotNull String getLastName() {
    return this.lastName;
  }

  public @NotNull String getEmail() {
    return this.email;
  }

  public @NotNull Address getAddress() {
    return this.address;
  }

  public @NotNull Boolean getActive() {
    return this.active;
  }

  public @NotNull Instant getCreateDate() {
    return this.createDate;
  }

  public @NotNull Instant getLastUpdate() {
    return this.lastUpdate;
  }

  public void setId(@NotNull Long id) {
    this.id = id;
  }

  public void setStore(@NotNull Store store) {
    this.store = store;
  }

  public void setFirstName(@NotNull String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(@NotNull String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(@NotNull String email) {
    this.email = email;
  }

  public void setAddress(@NotNull Address address) {
    this.address = address;
  }

  public void setActive(@NotNull Boolean active) {
    this.active = active;
  }

  public void setCreateDate(@NotNull Instant createDate) {
    this.createDate = createDate;
  }

  public void setLastUpdate(@NotNull Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}