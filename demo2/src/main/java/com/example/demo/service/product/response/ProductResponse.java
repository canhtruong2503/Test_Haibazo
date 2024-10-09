package com.example.demo.service.product.response;

import com.example.demo.model.*;
import com.example.demo.model.enums.ColorProduct;
import com.example.demo.model.enums.SizeProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal salePrice;
    private LocalDate discountTime;
    private Category category;
    private List<ImgResponse> images;
    private List<SizeResponse> sizes;
    private List<ColorResponse> colors;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImgResponse{
        private int id;
        private String path;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ColorResponse{
        private int id;
        private ColorProduct colorProduct;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SizeResponse{
        private int id;
        private SizeProduct sizeProduct;
    }
}

