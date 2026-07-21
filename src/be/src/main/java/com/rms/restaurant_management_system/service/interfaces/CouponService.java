package com.rms.restaurant_management_system.service.interfaces;

import com.rms.restaurant_management_system.dto.request.CouponRequest;
import com.rms.restaurant_management_system.dto.response.CouponResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CouponService {
    List<CouponResponse> getAllCoupons();
    CouponResponse createCoupon(CouponRequest request);
    CouponResponse validateCoupon(String code, BigDecimal orderAmount);
    void deleteCoupon(Long id);
}
