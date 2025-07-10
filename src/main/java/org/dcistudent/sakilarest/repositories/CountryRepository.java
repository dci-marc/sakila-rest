package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}