package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "address", schema = "sakila", indexes = {
    @Index(name = "idx_fk_city_id", columnList = "city_id")
})
@Getter
@Setter
public class Address {
  @Id
  @Column(name = "address_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "name", nullable = false, length = 50)
  private @NotNull String name;

  @Column(name = "address2", length = 50)
  private @NotNull String address2;

  @Column(name = "district", nullable = false, length = 20)
  private @NotNull String district;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "city_id", nullable = false)
  private @NotNull City city;

  @Column(name = "postal_code", length = 10)
  private @NotNull String postalCode;

  @Column(name = "phone", nullable = false, length = 20)
  private @NotNull String phone;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;
}