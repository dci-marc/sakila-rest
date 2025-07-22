package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmActorId implements Serializable {
  @Serial
  private static final long serialVersionUID = 5914215412321227295L;
  @Column(name = "actor_id", columnDefinition = "int UNSIGNED not null")
  private Long actorId;

  @Column(name = "film_id", columnDefinition = "int UNSIGNED not null")
  private Long filmId;

  public Long getActorId() {
    return actorId;
  }

  public void setActorId(Long actorId) {
    this.actorId = actorId;
  }

  public Long getFilmId() {
    return filmId;
  }

  public void setFilmId(Long filmId) {
    this.filmId = filmId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    FilmActorId entity = (FilmActorId) o;
    return Objects.equals(this.actorId, entity.actorId) &&
        Objects.equals(this.filmId, entity.filmId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actorId, filmId);
  }

}