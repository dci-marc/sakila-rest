package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.FilmActor;
import org.dcistudent.sakilarest.entities.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
}