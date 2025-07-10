package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}