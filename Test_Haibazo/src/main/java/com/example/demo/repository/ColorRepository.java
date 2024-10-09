package com.example.demo.repository;

import com.example.demo.model.Color;
import com.example.demo.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    Color findById(int id);
    void deleteByProductId(int productId);
}
