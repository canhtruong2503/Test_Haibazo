package com.example.demo.service.cartDetail;

import com.example.demo.model.CartDetail;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.*;
import com.example.demo.service.cartDetail.response.CartDetailResponse;
import com.example.demo.service.product.response.ProductResponse;
import com.example.demo.service.user.UserService;
import com.example.demo.util.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CartDetailService {
    private final CartRepository cartRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    public CartDetailResponse addToCart(int productId , int colorId , int sizeId , int quantity ) {
        User user = userService.getCurrentUser();
        Product product = productRepository.findById(productId);
        CartDetail cartDetail= cartRepository.findByProductIdAndUserIdAndColorIdAndSizeId(productId,user.getId(),colorId,sizeId);
        if(cartDetail != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cart already exists");
        }
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + productId);
        }
        if(quantity > product.getQuantity()){
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many products");
        }

        CartDetail cartDetail1= cartRepository.save(new CartDetail(quantity,colorRepository.findById(colorId),sizeRepository.findById(sizeId),product,user));
        return AppUtils.mapper.map( cartDetail1,CartDetailResponse.class);
    }
    public Page<CartDetailResponse> getCartDetails(Pageable pageable) {
        User user = userService.getCurrentUser();
        Page<CartDetail> cartDetails = cartRepository.findByUserId(user.getId(),pageable);
        Page<CartDetailResponse>  cartDetailResponses= cartDetails.map(
                e ->{
                    return AppUtils.mapper.map(e,CartDetailResponse.class);
                });
        return cartDetailResponses;
    }
}
