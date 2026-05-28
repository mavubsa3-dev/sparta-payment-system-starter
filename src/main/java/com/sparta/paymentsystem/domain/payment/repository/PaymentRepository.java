package com.sparta.paymentsystem.domain.payment.repository;

import com.sparta.paymentsystem.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p.id FROM Payment p WHERE p.order.id = :orderId")
    Optional<Long> findIdByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT p.order.id, p.id FROM Payment p WHERE p.order.id IN :orderIds")
    List<Object[]> findIdsByOrderIds(@Param("orderIds") List<Long> orderIds);
}
