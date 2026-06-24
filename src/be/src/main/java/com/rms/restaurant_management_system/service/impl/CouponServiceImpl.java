package com.rms.restaurant_management_system.service.impl;

import com.rms.restaurant_management_system.dto.request.CouponRequest;
import com.rms.restaurant_management_system.dto.response.CouponResponse;
import com.rms.restaurant_management_system.entity.Coupon;
import com.rms.restaurant_management_system.exception.BadRequestException;
import com.rms.restaurant_management_system.exception.ResourceNotFoundException;
import com.rms.restaurant_management_system.repository.CouponRepository;
import com.rms.restaurant_management_system.service.interfaces.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public List<CouponResponse> getAllCoupons() {
        return couponRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public CouponResponse createCoupon(CouponRequest request) {
        if (couponRepository.existsByCode(request.getCode())) {
            throw new BadRequestException("Coupon code already exists");
        }
        Coupon coupon = Coupon.builder()
                .code(request.getCode().toUpperCase())
                .discountPercent(request.getDiscountPercent())
                .discountAmount(request.getDiscountAmount())
                .minOrderAmount(request.getMinOrderAmount())
                .maxUses(request.getMaxUses())
                .expiresAt(request.getExpiresAt())
                .build();
        return toResponse(couponRepository.save(coupon));
    }

    @Override
    public CouponResponse validateCoupon(String code, BigDecimal orderAmount) {
        Coupon coupon = couponRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));

        if (!coupon.getIsActive()) throw new BadRequestException("Coupon is inactive");
        if (coupon.getExpiresAt() != null && coupon.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new BadRequestException("Coupon has expired");
        if (coupon.getMaxUses() != null && coupon.getUsedCount() >= coupon.getMaxUses())
            throw new BadRequestException("Coupon usage limit reached");
        if (coupon.getMinOrderAmount() != null && orderAmount.compareTo(coupon.getMinOrderAmount()) < 0)
            throw new BadRequestException("Order amount does not meet minimum requirement");

        return toResponse(coupon);
    }

    @Override
    public void deleteCoupon(Long id) {
        couponRepository.delete(couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found")));
    }

    private CouponResponse toResponse(Coupon c) {
        return CouponResponse.builder()
                .id(c.getId()).code(c.getCode())
                .discountPercent(c.getDiscountPercent())
                .discountAmount(c.getDiscountAmount())
                .minOrderAmount(c.getMinOrderAmount())
                .maxUses(c.getMaxUses()).usedCount(c.getUsedCount())
                .expiresAt(c.getExpiresAt()).isActive(c.getIsActive())
                .build();
    }
}
