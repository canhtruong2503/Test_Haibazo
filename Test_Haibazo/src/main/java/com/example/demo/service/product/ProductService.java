package com.example.demo.service.product;

import com.example.demo.model.*;
import com.example.demo.model.enums.ColorProduct;
import com.example.demo.model.enums.SizeProduct;
import com.example.demo.repository.*;
import com.example.demo.service.product.request.FilterRequest;
import com.example.demo.service.product.request.ProductRequest;
import com.example.demo.service.product.response.ProductResponse;
import com.example.demo.service.user.UserService;
import com.example.demo.util.AppUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final SizeRepository sizeRepository;
    private final UserService userService;
    private final ColorRepository colorRepository;
    private final CartRepository cartRepository;
    private final FavoriteProductRepository favoriteProductRepository;
    @Transactional
    public ProductResponse addProduct(ProductRequest request) {
        User user = userService.getCurrentUser();
        Product product = AppUtils.mapper.map(request, Product.class);
        product.setUser(user);
        product.setSizes(null);
        product.setColors(null);
        product.setImages(null);
        Product p = productRepository.save(product);
        updateSize(request.getSizes(), product);
        updateColor(request.getColors(), product);
        updateImage(request.getImages(), product);
        return AppUtils.mapper.map(p, ProductResponse.class);
    }
    public Page<ProductResponse> filter(String keyWord,ColorProduct  color, SizeProduct size,int styleId, BigDecimal minPrice, BigDecimal maxPrice, int categoryId, Pageable pageable) {
       Page<ProductResponse>  productResponses= productRepository.filter('%'+keyWord+'%',color,size,styleId,minPrice,maxPrice,categoryId ,pageable).map(
              e ->{
                return AppUtils.mapperProductToDto(e);
              });
    return productResponses;
    }
    public ProductResponse getProductById(int id) {
        return AppUtils.mapperProductToDto(productRepository.findById(id));
    }
    @Transactional
    public String updateProduct(ProductRequest request,int id) {
        Product product = AppUtils.mapper.map(request, Product.class);
        product.setId(id);
        if (product == null) {
            return null;
        }
        product.setImages(null);
        product.setColors(null);
        product.setSizes(null);
        updateImage(request.getImages(), product);
        updateSize(request.getSizes(),product);
        updateColor(request.getColors(),product);
        productRepository.save(product);

        return "Update completed";
    }
    public void updateSize(List<SizeProduct> sizes, Product product){
        sizeRepository.deleteByProductId(product.getId());
        for (var item : sizes
        ) {
            sizeRepository.save(new Size(item,product));
        }
    }
    public void updateColor(List<ColorProduct> colors, Product product){
        colorRepository.deleteByProductId(product.getId());
        for (var item : colors
        ) {
            colorRepository.save(new Color(item,product));
        }
    }
    public void updateImage(List<String> images, Product product){
        System.out.println(images);
        imageRepository.deleteByProductId(product.getId());
        for (var item : images
        ) {
            imageRepository.save(new Image(product,item));
        }
    }
    public Product deleteProduct(int productId){
        Product product = productRepository.findById(productId);
            product.setDeleted(true);
            productRepository.save(product);
            return product;
    }

    public String favoriteProduct(int id) {
        User user = userService.getCurrentUser();
        Product product = productRepository.findById(id);
        favoriteProductRepository.save(new FavoriteProduct(product,user));
        return "Favorite completed";
    }
    public Page<ProductResponse> getFavoriteProducts(Pageable pageable) {
        User user = userService.getCurrentUser();
        Page<ProductResponse>  productResponses= productRepository.getFavoriteList( user.getId() ,pageable).map(
                e ->{
                    return AppUtils.mapperProductToDto(e);
                });
        System.out.println(productResponses);
        return productResponses;
    }
}

