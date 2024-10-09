package com.example.demo.service.bill.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillRequest {
    private int quantity;
    private int productId;
    private int colorId;
    private int sizeId;
}
