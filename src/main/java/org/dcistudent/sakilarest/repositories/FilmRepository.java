package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}