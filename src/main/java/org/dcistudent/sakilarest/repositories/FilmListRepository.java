package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.FilmList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmListRepository extends JpaRepository<FilmList, Long> {
}