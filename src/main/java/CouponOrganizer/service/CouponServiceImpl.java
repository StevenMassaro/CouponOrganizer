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

    public void insert(String store,
                       String deal,
                       String comment,
                       Date expirationDate,
                       MultipartFile file) throws IOException {
        couponMapper.insert(store, deal, comment, expirationDate, Base64.getEncoder().encode(file.getBytes()));
    }

    public Coupon get(long id) throws IOException {
        return couponMapper.get(id);
    }

    public Resource getFile(long id) throws IOException {
        Coupon coupon = couponMapper.get(id);

        byte[] databaseFile = coupon.getFile();
        File file = File.createTempFile(coupon.getStore(), ".pdf");
        FileUtils.writeByteArrayToFile(file, Base64.getDecoder().decode(databaseFile));
        Resource response = new FileSystemResource(file);
        return response;
    }
}
