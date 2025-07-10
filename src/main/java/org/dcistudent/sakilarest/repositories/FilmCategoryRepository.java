package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.FilmCategory;
import org.dcistudent.sakilarest.entities.FilmCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {
}