package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "customer", schema = "sakila", indexes = {
    @Index(name = "idx_fk_store_id", columnList = "store_id"),
    @Index(name = "idx_last_name", columnList = "last_name"),
    @Index(name = "idx_fk_address_id", columnList = "address_id")
})
@Getter
@Setter
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
}