package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}