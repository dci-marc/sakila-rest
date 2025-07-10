package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}