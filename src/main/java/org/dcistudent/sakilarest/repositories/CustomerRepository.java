package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}