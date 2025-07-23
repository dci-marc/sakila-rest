package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "inventory", schema = "sakila", indexes = {
    @Index(name = "idx_fk_film_id", columnList = "film_id"),
    @Index(name = "idx_store_id_film_id", columnList = "store_id, film_id")
})
@Getter
@Setter
public class Inventory {
  @Id
  @Column(name = "inventory_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "film_id", nullable = false)
  private @NotNull Film film;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "store_id", nullable = false)
  private @NotNull Store store;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;
}