package com.example.demo.repository;

import com.example.demo.model.CartDetail;
import com.example.demo.model.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartDetail, Integer> {
    CartDetail findByProductIdAndUserIdAndColorIdAndSizeId(Integer productId, Integer userId, Integer colorId, Integer sizeId);
    Page<CartDetail> findByUserId(Integer userId, Pageable pageable);
}
