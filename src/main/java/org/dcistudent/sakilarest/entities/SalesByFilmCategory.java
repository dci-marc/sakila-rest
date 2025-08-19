package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "sales_by_film_category", schema = "sakila")
public class SalesByFilmCategory {
  @Id
  @Column(name = "category", nullable = false, length = 25)
  private @NotNull String category;

  @Column(name = "total_sales", precision = 27, scale = 2)
  private @NotNull BigDecimal totalSales;

  protected SalesByFilmCategory() {
  }

  public @NotNull String getCategory() {
    return this.category;
  }

  public @NotNull BigDecimal getTotalSales() {
    return this.totalSales;
  }
}