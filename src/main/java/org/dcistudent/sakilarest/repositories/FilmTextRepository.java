package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.FilmText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmTextRepository extends JpaRepository<FilmText, Integer> {
}