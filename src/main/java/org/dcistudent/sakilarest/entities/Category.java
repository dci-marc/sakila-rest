package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "category", schema = "sakila")
@Getter
@Setter
public class Category {
  @Id
  @Column(name = "category_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "name", nullable = false, length = 25)
  private @NotNull String name;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;
}