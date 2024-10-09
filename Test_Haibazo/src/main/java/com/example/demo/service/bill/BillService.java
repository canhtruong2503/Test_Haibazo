package com.example.demo.service.bill;

import com.example.demo.model.Bill;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.ColorRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SizeRepository;
import com.example.demo.service.bill.request.BillRequest;
import com.example.demo.service.bill.response.BillResponse;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.product.response.ProductResponse;
import com.example.demo.service.user.UserService;
import com.example.demo.util.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    public BillResponse addBill(BillRequest request) {
        User user = userService.getCurrentUser();
        Product product = productRepository.findById(request.getProductId());
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setProduct(product);
        bill.setPrice((product.getPrice().subtract(product.getPrice().multiply(product.getSalePrice().divide(BigDecimal.valueOf(100))))).multiply(BigDecimal.valueOf(request.getQuantity())));
        bill.setQuantity(request.getQuantity());
        bill.setProductColor(colorRepository.findById(request.getColorId()));
        bill.setProductSize(sizeRepository.findById(request.getSizeId()));
        billRepository.save(bill);
        return AppUtils.mapper.map(bill, BillResponse.class);
    }
    public Page<BillResponse> getBillList (Pageable pageable) {
        User user = userService.getCurrentUser();
        Page<Bill> billPage = billRepository.findByUserId(user.getId(),pageable);
        Page<BillResponse>  billResponses= billPage.map(
                e ->{
                    return AppUtils.mapper.map(e, BillResponse.class);
                });
        return billResponses;
    }
}
