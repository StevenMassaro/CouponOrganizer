package CouponOrganizer.service;

import CouponOrganizer.mapper.CouponMapper;
import CouponOrganizer.mapper.FileMapper;
import CouponOrganizer.model.Coupon;
import CouponOrganizer.model.Metafile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class FileServiceImpl {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private FileMapper fileMapper;

    public void insert(long id,
                       MultipartFile file,
                       String extension) throws IOException {
        fileMapper.insert(id, Base64.getEncoder().encode(file.getBytes()), extension);
    }

    public Resource getFile(long id) throws IOException {
        Coupon coupon = couponMapper.get(id);
        Metafile metafile = fileMapper.get(id);

        byte[] databaseFile = metafile.getFile();
        File file = File.createTempFile(coupon.getStore(), "." + metafile.getExtension());
        file.deleteOnExit();
        FileUtils.writeByteArrayToFile(file, Base64.getDecoder().decode(databaseFile));
        Resource response = new FileSystemResource(file);
        return response;
    }
}
