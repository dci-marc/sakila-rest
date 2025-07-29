package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "rental", schema = "sakila", indexes = {
    @Index(name = "idx_fk_inventory_id", columnList = "inventory_id"),
    @Index(name = "idx_fk_customer_id", columnList = "customer_id"),
    @Index(name = "idx_fk_staff_id", columnList = "staff_id")
}, uniqueConstraints = {
    @UniqueConstraint(name = "rental_date", columnNames = {"rental_date", "inventory_id", "customer_id"})
})
@Getter
@Setter
public class Rental {
  @Id
  @Column(name = "rental_id", nullable = false)
  private @NotNull Integer id;

  @Column(name = "rental_date", nullable = false)
  private @NotNull Instant rentalDate;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "inventory_id", nullable = false)
  private @NotNull Inventory inventory;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  private @NotNull Customer customer;

  @Column(name = "return_date")
  private @NotNull Instant returnDate;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "staff_id", nullable = false)
  private @NotNull Staff staff;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;
}