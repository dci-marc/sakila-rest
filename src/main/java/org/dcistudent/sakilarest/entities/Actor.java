package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "actor", schema = "sakila", indexes = {
    @Index(name = "idx_actor_last_name", columnList = "last_name")
})
@Getter
@Setter
public class Actor {
  @Id
  @Column(name = "actor_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "first_name", nullable = false, length = 45)
  private @NotNull String firstName;

  @Column(name = "last_name", nullable = false, length = 45)
  private @NotNull String lastName;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;
}