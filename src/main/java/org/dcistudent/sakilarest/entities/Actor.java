package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "actor", schema = "sakila", indexes = {
    @Index(name = "idx_actor_last_name", columnList = "last_name")
})
public class Actor {
  @Id
  @Column(name = "actor_id", columnDefinition = "int UNSIGNED not null")
  private Long id;

  @Column(name = "first_name", nullable = false, length = 45)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 45)
  private String lastName;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private Instant lastUpdate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Instant getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

}