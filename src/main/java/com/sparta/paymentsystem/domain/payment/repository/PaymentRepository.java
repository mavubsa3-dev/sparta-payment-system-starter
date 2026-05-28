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

    // 주문 단건 상세 조회 : orderId만으로 조회
    @Query("SELECT p FROM Payment p JOIN FETCH p.order WHERE p.order.id = :orderId")
    Optional<Payment> findByOrderIdWithOrder(@Param("orderId") Long orderId);

    // Payment 조회 시 연관된 Order를 fetch join 으로 함께 로딩 (N+1 방지)
    @Query("SELECT p FROM Payment p JOIN FETCH p.order WHERE p.id = :paymentId")
    Optional<Payment> findByIdWithOrder(@Param("paymentId") Long paymentId);
}
