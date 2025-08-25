package org.dcistudent.sakilarest.entities.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "nicer_but_slower_film_list", schema = "sakila")
public class NicerButSlowerFilmList {
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

  protected NicerButSlowerFilmList() {
  }

  public @NotNull Long getFid() {
    return this.fid;
  }

  public @NotNull String getTitle() {
    return this.title;
  }

  public @NotNull String getDescription() {
    return this.description;
  }

  public @NotNull String getCategory() {
    return this.category;
  }

  public @NotNull BigDecimal getPrice() {
    return this.price;
  }

  public @NotNull Integer getLength() {
    return this.length;
  }

  public @NotNull String getRating() {
    return this.rating;
  }

  public @NotNull String getActors() {
    return this.actors;
  }
}