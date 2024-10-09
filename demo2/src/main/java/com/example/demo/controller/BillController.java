package com.example.demo.controller;

import com.example.demo.model.Bill;
import com.example.demo.service.bill.BillService;
import com.example.demo.service.bill.request.BillRequest;
import com.example.demo.service.bill.response.BillResponse;
import com.example.demo.service.product.request.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bill")
public class BillController {
    private BillService billService;


    @PostMapping("user/addBill")
    public BillResponse addBill(@RequestBody BillRequest request) {
        return billService.addBill(request);
    }
    @GetMapping("user/getBillList")
    public Page<BillResponse> getBillList(Pageable pageable) {
        return billService.getBillList(pageable);
    }
}
