package com.pay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.pay.entity.Payment;
import com.pay.status.PaymentStatus;

import feign.Param;
import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE Payment p SET p.status = :status WHERE p.id = :id")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheMode", value = "IGNORE")})
	int updateStatus(@Param("id") Long id, @Param("status") PaymentStatus status);

}
