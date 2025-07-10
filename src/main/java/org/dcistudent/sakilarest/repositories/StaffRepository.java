package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}