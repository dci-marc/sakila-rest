package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "film_list", schema = "sakila")
public class FilmList {
  @Column(name = "FID", columnDefinition = "int UNSIGNED")
  private Long fid;

  @Column(name = "title")
  private String title;

  @Lob
  @Column(name = "description")
  private String description;

  @Column(name = "category", nullable = false, length = 25)
  private String category;

  @Column(name = "price", precision = 4, scale = 2)
  private BigDecimal price;

  @Column(name = "length", columnDefinition = "smallint UNSIGNED")
  private Integer length;

  @Lob
  @Column(name = "rating")
  private String rating;

  @Lob
  @Column(name = "actors")
  private String actors;

  public Long getFid() {
    return fid;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getCategory() {
    return category;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Integer getLength() {
    return length;
  }

  public String getRating() {
    return rating;
  }

  public String getActors() {
    return actors;
  }

  protected FilmList() {
  }
}