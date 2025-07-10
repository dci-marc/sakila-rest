package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}