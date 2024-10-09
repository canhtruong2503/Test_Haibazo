package com.example.demo.model;

import com.example.demo.model.enums.ColorProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ColorProduct colorProduct;

    public Color(ColorProduct color, Product product) {
        this.colorProduct = color;
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_Id")
    private Product product;
}
