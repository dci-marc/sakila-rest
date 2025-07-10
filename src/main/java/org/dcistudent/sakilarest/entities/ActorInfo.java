package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "actor_info", schema = "sakila")
public class ActorInfo {
  @Column(name = "actor_id", columnDefinition = "int UNSIGNED not null")
  private Long actorId;

  @Column(name = "first_name", nullable = false, length = 45)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 45)
  private String lastName;

  @Lob
  @Column(name = "film_info")
  private String filmInfo;

  public Long getActorId() {
    return actorId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFilmInfo() {
    return filmInfo;
  }

  protected ActorInfo() {
  }
}