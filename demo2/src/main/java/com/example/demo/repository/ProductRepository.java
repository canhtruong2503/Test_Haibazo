package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.enums.ColorProduct;
import com.example.demo.model.enums.SizeProduct;
import com.example.demo.service.product.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT p " +
            "FROM Product as p " +
            "INNER JOIN Color c ON p.id = c.product.id " +
            "INNER JOIN Size s ON p.id = s.product.id "+
            "WHERE p.name LIKE :keyWord "+
            "AND (:minPrice is null or p.price >= :minPrice)" +
            "AND (:maxPrice is null or p.price <= :maxPrice) " +
            "AND (:color is null or c.colorProduct = :color) " +
            "AND (:size is null or s.sizeProduct = :size) " +
            "AND (:categoryId is null or p.category.id = :categoryId) " +
            "AND (:styleId is null or p.style.id = :styleId)" +
            "AND (p.deleted = false )"
    )
    Page<Product> filter(
            @Param("keyWord") String keyWord,
            @Param("color") ColorProduct color,
            @Param("size") SizeProduct size,
            @Param("styleId") Integer styleId,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("categoryId") Integer categoryId,
            Pageable pageable
    );
    Product findById(int id);
    @Query(value = "SELECT p " +
            "FROM Product as p " +
            "INNER JOIN FavoriteProduct fp ON p.id = fp.product.id " +
            "WHERE fp.user.id = :userId " +
            "and p.deleted = false "

    )
    Page<Product> getFavoriteList(@Param("userId") int userId, Pageable pageable);
}
