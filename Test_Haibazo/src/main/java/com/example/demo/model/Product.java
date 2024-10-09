package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private Boolean deleted = Boolean.FALSE;
    @Column(nullable = false)
    private BigDecimal price;
    @Column()
    private BigDecimal SalePrice;
    @Column
    private LocalDate discountTime;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_Id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "style_Id")
    private Style style;
    @OneToMany(mappedBy = "product",cascade =CascadeType.ALL)
   @JsonIgnore
    private List<Image> images;
    @OneToMany(mappedBy = "product",cascade =CascadeType.ALL)
     @JsonIgnore
    private List<Size> sizes;
    @OneToMany(mappedBy = "product",cascade =CascadeType.ALL)
    @JsonIgnore
    private List<Color> colors;
}
