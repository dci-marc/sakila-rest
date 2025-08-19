package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmActorId implements Serializable {
  @Serial
  private static final long serialVersionUID = 5914215412321227295L;
  @Column(name = "actor_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long actorId;

  @Column(name = "film_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long filmId;

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
    return Objects.hash(this.actorId, this.filmId);
  }

  public @NotNull Long getActorId() {
    return this.actorId;
  }

  public @NotNull Long getFilmId() {
    return this.filmId;
  }

  public void setActorId(@NotNull Long actorId) {
    this.actorId = actorId;
  }

  public void setFilmId(@NotNull Long filmId) {
    this.filmId = filmId;
  }
}