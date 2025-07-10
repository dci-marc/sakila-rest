package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}