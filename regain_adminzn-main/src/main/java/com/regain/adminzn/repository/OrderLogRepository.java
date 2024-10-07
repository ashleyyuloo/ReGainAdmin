package com.regain.adminzn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.regain.adminzn.model.OrderLog;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLog, Integer> {

    Optional<OrderLog> findByOrderIdAndPreviousStatus(int orderId, String previousStatus);
}
