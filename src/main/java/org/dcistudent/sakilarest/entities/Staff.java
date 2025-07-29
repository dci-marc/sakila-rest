package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "staff", schema = "sakila", indexes = {
    @Index(name = "idx_fk_address_id", columnList = "address_id"),
    @Index(name = "idx_fk_store_id", columnList = "store_id")
})
@Getter
@Setter
public class Staff {
  @Id
  @Column(name = "staff_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "first_name", nullable = false, length = 45)
  private @NotNull String firstName;

  @Column(name = "last_name", nullable = false, length = 45)
  private @NotNull String lastName;

  @Column(name = "address_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long addressId;

  @Column(name = "picture")
  private byte @NotNull [] picture;

  @Column(name = "email", length = 50)
  private @NotNull String email;

  @Column(name = "store_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long storeId;

  @ColumnDefault("1")
  @Column(name = "active", nullable = false)
  private @NotNull Boolean active = false;

  @Column(name = "username", nullable = false, length = 16)
  private @NotNull String username;

  @Column(name = "password", length = 40)
  private @NotNull String password;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;
}