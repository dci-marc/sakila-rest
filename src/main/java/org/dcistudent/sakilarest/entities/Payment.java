package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
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
}