package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "store", schema = "sakila", indexes = {
    @Index(name = "idx_fk_address_id", columnList = "address_id"),
    @Index(name = "idx_uuid", columnList = "uuid")
}, uniqueConstraints = {
    @UniqueConstraint(name = "idx_unique_manager", columnNames = {"manager_staff_id"})
})

@NamedEntityGraph(name = "Store.eager", attributeNodes = {
    @NamedAttributeNode("uuid"),
    @NamedAttributeNode("managerStaff"),
    @NamedAttributeNode("customer"),
    @NamedAttributeNode("inventory")
})

public class Store extends AbstractUuidEntity implements Serializable {
  @Id
  @Column(name = "store_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "manager_staff_id", nullable = false)
  private @NotNull Staff managerStaff;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "address_id", nullable = false)
  private @NotNull Address address;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
  @OrderBy("id ASC")
  private @NotNull Set<Customer> customer;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
  @OrderBy("id ASC")
  private @NotNull Set<Inventory> inventory;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull Staff getManagerStaff() {
    return this.managerStaff;
  }

  public @NotNull Address getAddress() {
    return this.address;
  }

  public @NotNull Set<Customer> getCustomer() {
    return this.customer;
  }

  public @NotNull Set<Inventory> getInventory() {
    return this.inventory;
  }

  public @NotNull Instant getLastUpdate() {
    return this.lastUpdate;
  }

  public void setId(@NotNull Long id) {
    this.id = id;
  }

  public void setManagerStaff(@NotNull Staff managerStaff) {
    this.managerStaff = managerStaff;
  }

  public void setAddress(@NotNull Address address) {
    this.address = address;
  }

  public void setCustomer(@NotNull Set<Customer> customer) {
    this.customer = customer;
  }

  public void setInventory(@NotNull Set<Inventory> inventory) {
    this.inventory = inventory;
  }

  public void setLastUpdate(@NotNull Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}