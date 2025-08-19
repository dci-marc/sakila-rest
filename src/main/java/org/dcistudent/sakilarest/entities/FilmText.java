package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "film_text", schema = "sakila", indexes = {
    @Index(name = "idx_title_description", columnList = "title, description")
})
public class FilmText {
  @Id
  @Column(name = "film_id", nullable = false)
  private @NotNull Integer id;

  @Column(name = "title", nullable = false)
  private @NotNull String title;

  @Lob
  @Column(name = "description")
  private @NotNull String description;

  public @NotNull Integer getId() {
    return this.id;
  }

  public @NotNull String getTitle() {
    return this.title;
  }

  public @NotNull String getDescription() {
    return this.description;
  }

  public void setId(@NotNull Integer id) {
    this.id = id;
  }

  public void setTitle(@NotNull String title) {
    this.title = title;
  }

  public void setDescription(@NotNull String description) {
    this.description = description;
  }
}