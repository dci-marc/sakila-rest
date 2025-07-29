package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.jetbrains.annotations.NotNull;

@Entity
@Immutable
@Table(name = "customer_list", schema = "sakila")
@Getter
public class CustomerList {
  @Id
  @Column(name = "ID", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "name", length = 91)
  private @NotNull String name;

  @Column(name = "address", nullable = false, length = 50)
  private @NotNull String address;

  @Column(name = "`zip code`", length = 10)
  private @NotNull String zipCode;

  @Column(name = "phone", nullable = false, length = 20)
  private @NotNull String phone;

  @Column(name = "city", nullable = false, length = 50)
  private @NotNull String city;

  @Column(name = "country", nullable = false, length = 50)
  private @NotNull String country;

  @Column(name = "notes", nullable = false, length = 6)
  private @NotNull String notes;

  @Column(name = "SID", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long sid;

  protected CustomerList() {
  }
}