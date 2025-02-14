package com.example.demo.repository;

import com.example.demo.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    Size findById(int id);
    void deleteByProductId(int productId);
}
