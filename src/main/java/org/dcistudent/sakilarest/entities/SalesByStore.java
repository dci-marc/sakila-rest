package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "sales_by_store", schema = "sakila")
public class SalesByStore {
  @Id
  @Column(name = "store", nullable = false, length = 101)
  private String store;

  @Column(name = "manager", nullable = false, length = 91)
  private String manager;

  @Column(name = "total_sales", precision = 27, scale = 2)
  private BigDecimal totalSales;

  public String getStore() {
    return store;
  }

  public String getManager() {
    return manager;
  }

  public BigDecimal getTotalSales() {
    return totalSales;
  }

  protected SalesByStore() {
  }
}