package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "film_text", schema = "sakila", indexes = {
    @Index(name = "idx_title_description", columnList = "title, description")
})
@Getter
@Setter
public class FilmText {
  @Id
  @Column(name = "film_id", nullable = false)
  private @NotNull Integer id;

  @Column(name = "title", nullable = false)
  private @NotNull String title;

  @Lob
  @Column(name = "description")
  private @NotNull String description;
}