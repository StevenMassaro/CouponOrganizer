package CouponOrganizer.service;

import CouponOrganizer.mapper.CouponMapper;
import CouponOrganizer.model.Coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl {

    @Autowired
    private CouponMapper couponMapper;

    public List<Coupon> list() {
        return couponMapper.list();
    }

    public List<Coupon> listDeleted() {
        return couponMapper.listDeleted();
    }

    /**
     *
     * @param store
     * @param deal
     * @param comment
     * @param expirationDate
     * @return id of the coupon that was inserted/created
     */
    public long insert(String store,
                       String deal,
                       String comment,
                       Date expirationDate) {
        return couponMapper.insert(store, deal, comment, expirationDate);
    }

    public Coupon get(long id) {
        return couponMapper.get(id);
    }

    public void setDateDeleted(long id) {
        couponMapper.setDateDeleted(new Date(), id);
    }
}
