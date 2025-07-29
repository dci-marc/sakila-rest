package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "film_list", schema = "sakila")
@Getter
public class FilmList {
  @Id
  @Column(name = "FID", columnDefinition = "int UNSIGNED")
  private @NotNull Long fid;

  @Column(name = "title")
  private @NotNull String title;

  @Lob
  @Column(name = "description")
  private @NotNull String description;

  @Column(name = "category", nullable = false, length = 25)
  private @NotNull String category;

  @Column(name = "price", precision = 4, scale = 2)
  private @NotNull BigDecimal price;

  @Column(name = "length", columnDefinition = "smallint UNSIGNED")
  private @NotNull Integer length;

  @Lob
  @Column(name = "rating")
  private @NotNull String rating;

  @Lob
  @Column(name = "actors")
  private @NotNull String actors;

  protected FilmList() {
  }
}