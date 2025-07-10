package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "category", schema = "sakila")
public class Category {
  @Id
  @Column(name = "category_id", columnDefinition = "int UNSIGNED not null")
  private Long id;

  @Column(name = "name", nullable = false, length = 25)
  private String name;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private Instant lastUpdate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Instant getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

}