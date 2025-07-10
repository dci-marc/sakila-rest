package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}