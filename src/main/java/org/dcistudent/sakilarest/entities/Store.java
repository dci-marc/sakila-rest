package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "store", schema = "sakila", indexes = {
    @Index(name = "idx_fk_address_id", columnList = "address_id")
}, uniqueConstraints = {
    @UniqueConstraint(name = "idx_unique_manager", columnNames = {"manager_staff_id"})
})
@Getter
@Setter

@NamedEntityGraph(name = "Store.eager", attributeNodes = {
    @NamedAttributeNode("managerStaff"),
    @NamedAttributeNode("customer"),
    @NamedAttributeNode("inventory")
})

public class Store {
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
  private @NotNull List<Customer> customer;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
  private @NotNull List<Inventory> inventory;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;
}