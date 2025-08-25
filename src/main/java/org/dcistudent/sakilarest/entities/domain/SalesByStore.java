package org.dcistudent.sakilarest.entities.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "sales_by_store", schema = "sakila")
public class SalesByStore {
  @Id
  @Column(name = "store", nullable = false, length = 101)
  private @NotNull String store;

  @Column(name = "manager", nullable = false, length = 91)
  private @NotNull String manager;

  @Column(name = "total_sales", precision = 27, scale = 2)
  private @NotNull BigDecimal totalSales;

  protected SalesByStore() {
  }

  public @NotNull String getStore() {
    return this.store;
  }

  public @NotNull String getManager() {
    return this.manager;
  }

  public @NotNull BigDecimal getTotalSales() {
    return this.totalSales;
  }
}