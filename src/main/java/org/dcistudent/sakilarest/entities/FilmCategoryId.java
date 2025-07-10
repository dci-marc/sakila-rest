package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmCategoryId implements Serializable {
  private static final long serialVersionUID = -3732591992087887041L;
  @Column(name = "film_id", columnDefinition = "int UNSIGNED not null")
  private Long filmId;

  @Column(name = "category_id", columnDefinition = "int UNSIGNED not null")
  private Long categoryId;

  public Long getFilmId() {
    return filmId;
  }

  public void setFilmId(Long filmId) {
    this.filmId = filmId;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

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
    return Objects.hash(filmId, categoryId);
  }

}