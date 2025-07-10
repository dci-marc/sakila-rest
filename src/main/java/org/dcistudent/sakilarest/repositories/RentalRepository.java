package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}