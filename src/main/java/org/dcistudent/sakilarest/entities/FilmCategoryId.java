package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmCategoryId implements Serializable {
  @Serial
  private static final long serialVersionUID = -3732591992087887041L;
  @Column(name = "film_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long filmId;

  @Column(name = "category_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long categoryId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    FilmCategoryId entity = (FilmCategoryId) o;
    return Objects.equals(this.filmId, entity.filmId) &&
        Objects.equals(this.categoryId, entity.categoryId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.filmId, this.categoryId);
  }

  public @NotNull Long getFilmId() {
    return this.filmId;
  }

  public @NotNull Long getCategoryId() {
    return this.categoryId;
  }

  public void setFilmId(@NotNull Long filmId) {
    this.filmId = filmId;
  }

  public void setCategoryId(@NotNull Long categoryId) {
    this.categoryId = categoryId;
  }
}