package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.jetbrains.annotations.NotNull;

@Entity
@Immutable
@Table(name = "actor_info", schema = "sakila")
@Getter
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
}