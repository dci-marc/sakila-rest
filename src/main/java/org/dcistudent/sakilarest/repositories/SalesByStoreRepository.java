package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.SalesByStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesByStoreRepository extends JpaRepository<SalesByStore, String> {
}