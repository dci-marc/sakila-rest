package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.CustomerList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerListRepository extends JpaRepository<CustomerList, Long> {
}