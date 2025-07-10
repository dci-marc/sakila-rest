package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.SalesByFilmCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesByFilmCategoryRepository extends JpaRepository<SalesByFilmCategory, String> {
}