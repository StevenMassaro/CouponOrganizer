package CouponOrganizer.service;

import CouponOrganizer.model.ApiResponse;
import CouponOrganizer.model.Coupon;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActionsServiceImpl {

    @Autowired
    private CouponServiceImpl couponService;

    @Autowired
    private CronofyServiceImpl cronofyService;

    public ApiResponse updateCronofy() {
        List<Coupon> coupons = couponService.list();
        List<Long> updatedEvents = new ArrayList<>();
        for (Coupon coupon : coupons) {
            try {
                cronofyService.addEvent(coupon.getStore(), coupon.getDeal(), coupon.getComment(), coupon.getExpirationDate(), coupon.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            updatedEvents.add(coupon.getId());
        }
        return new ApiResponse(true, "Updated events in Cronofy for coupons: " + StringUtils.join(updatedEvents, ", "));
    }
}
