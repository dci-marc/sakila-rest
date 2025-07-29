package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "city", schema = "sakila", indexes = {
    @Index(name = "idx_fk_country_id", columnList = "country_id")
})
@Getter
@Setter
public class City {
  @Id
  @Column(name = "city_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "name", nullable = false, length = 50)
  private @NotNull String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "country_id", nullable = false)
  private @NotNull Country country;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;
}