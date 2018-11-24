package CouponOrganizer.service;

import CouponOrganizer.mapper.CouponMapper;
import CouponOrganizer.model.Coupon;
import org.apache.commons.io.FileUtils;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl {

    @Autowired
    private CouponMapper couponMapper;

    public List<Coupon> list() {
        return couponMapper.list();
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
}
