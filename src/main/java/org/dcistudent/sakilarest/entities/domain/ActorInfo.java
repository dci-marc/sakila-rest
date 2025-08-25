package org.dcistudent.sakilarest.entities.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.jetbrains.annotations.NotNull;

@Entity
@Immutable
@Table(name = "actor_info", schema = "sakila")
public class ActorInfo {
  @Id
  @Column(name = "actor_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long actorId;

  @Column(name = "first_name", nullable = false, length = 45)
  private @NotNull String firstName;

  @Column(name = "last_name", nullable = false, length = 45)
  private @NotNull String lastName;

  @Lob
  @Column(name = "film_info")
  private @NotNull String filmInfo;

  protected ActorInfo() {
  }

  public @NotNull Long getActorId() {
    return this.actorId;
  }

  public @NotNull String getFirstName() {
    return this.firstName;
  }

  public @NotNull String getLastName() {
    return this.lastName;
  }

  public @NotNull String getFilmInfo() {
    return this.filmInfo;
  }
}