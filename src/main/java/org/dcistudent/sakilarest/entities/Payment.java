package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payment", schema = "sakila", indexes = {
    @Index(name = "idx_fk_customer_id", columnList = "customer_id"),
    @Index(name = "idx_fk_staff_id", columnList = "staff_id")
})
public class Payment {
  @Id
  @Column(name = "payment_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  private @NotNull Customer customer;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "staff_id", nullable = false)
  private @NotNull Staff staff;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "rental_id")
  private @NotNull Rental rental;

  @Column(name = "amount", nullable = false, precision = 5, scale = 2)
  private @NotNull BigDecimal amount;

  @Column(name = "payment_date", nullable = false)
  private @NotNull Instant paymentDate;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update")
  private @NotNull Instant lastUpdate;

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull Customer getCustomer() {
    return this.customer;
  }

  public @NotNull Staff getStaff() {
    return this.staff;
  }

  public @NotNull Rental getRental() {
    return this.rental;
  }

  public @NotNull BigDecimal getAmount() {
    return this.amount;
  }

  public @NotNull Instant getPaymentDate() {
    return this.paymentDate;
  }

  public @NotNull Instant getLastUpdate() {
    return this.lastUpdate;
  }

  public void setId(@NotNull Long id) {
    this.id = id;
  }

  public void setCustomer(@NotNull Customer customer) {
    this.customer = customer;
  }

  public void setStaff(@NotNull Staff staff) {
    this.staff = staff;
  }

  public void setRental(@NotNull Rental rental) {
    this.rental = rental;
  }

  public void setAmount(@NotNull BigDecimal amount) {
    this.amount = amount;
  }

  public void setPaymentDate(@NotNull Instant paymentDate) {
    this.paymentDate = paymentDate;
  }

  public void setLastUpdate(@NotNull Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}