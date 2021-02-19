package com.savannahinformatics.SavannahService.repository;

import com.savannahinformatics.SavannahService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
