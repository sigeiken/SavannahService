package com.savannahinformatics.SavannahService.service;

import com.savannahinformatics.SavannahService.request.OrderRequest;

public interface OrderService {
    void addOrder(OrderRequest orderRequest) throws Exception;
}
