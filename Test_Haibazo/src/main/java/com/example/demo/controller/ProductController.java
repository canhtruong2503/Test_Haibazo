package com.example.demo.controller;

import com.example.demo.model.CartDetail;
import com.example.demo.model.Product;
import com.example.demo.model.enums.ColorProduct;
import com.example.demo.model.enums.SizeProduct;
import com.example.demo.service.cartDetail.CartDetailService;
import com.example.demo.service.cartDetail.response.CartDetailResponse;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.product.request.FilterRequest;
import com.example.demo.service.product.request.ProductRequest;
import com.example.demo.service.product.response.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final CartDetailService cartDetailService;

    @PostMapping("admin/addProduct")
    public ProductResponse addProduct(@RequestBody ProductRequest request) {
       return productService.addProduct(request);
    }
    @GetMapping("user/filter")
    public Page<ProductResponse> findAll(@RequestParam String keyWord,
                                         @RequestParam ColorProduct color,
                                         @RequestParam SizeProduct size,
                                         @RequestParam int styleId,
                                         @RequestParam BigDecimal minPrice,
                                         @RequestParam BigDecimal maxPrice,
                                         @RequestParam int categoryId
                                         , Pageable pageable) {
        return productService.filter(keyWord,color,size,styleId,minPrice,maxPrice,categoryId, pageable);
    }
    @GetMapping("user/findById/{id}")
    public ProductResponse findById(@PathVariable int id) {
        return productService.getProductById(id);
    }
    @PostMapping("admin/updateProduct/{id}")
    public String updateProduct(@PathVariable Integer id, @RequestBody ProductRequest request) {
        return productService.updateProduct(request,id);
    }
    @DeleteMapping("admin/deleteProduct/{id}")
    public Product deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("user/favoriteProduct/{id}")
    public String favoriteProduct(@PathVariable Integer id) {
      return   productService.favoriteProduct(id);
    }
    @GetMapping("user/getFavoriteList")
    public Page<ProductResponse> getFavoriteList(Pageable pageable) {
         return productService.getFavoriteProducts(pageable );
    }
}
