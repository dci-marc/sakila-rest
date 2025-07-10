package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}