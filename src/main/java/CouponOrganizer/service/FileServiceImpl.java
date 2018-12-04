package CouponOrganizer.service;

import CouponOrganizer.mapper.CouponMapper;
import CouponOrganizer.mapper.FileMapper;
import CouponOrganizer.model.Coupon;
import CouponOrganizer.model.Metafile;
import CouponOrganizer.model.pond.PondFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class FileServiceImpl {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private FileMapper fileMapper;

    private String temporaryDirectory = System.getProperty("java.io.tmpdir");

    public void insert(long id,
                       PondFile pondFile) {
        fileMapper.insert(id, pondFile.getType(), pondFile.getSize(), pondFile.getName(), pondFile.getData().getBytes());
    }

    public Resource getFile(long id) throws IOException {
        Coupon coupon = couponMapper.get(id);
        Metafile metafile = fileMapper.get(id);

        byte[] databaseFile = metafile.getFile();
        File file = new File(temporaryDirectory + File.separator + metafile.getFilename());
        file.deleteOnExit();
        FileUtils.writeByteArrayToFile(file, Base64.getDecoder().decode(databaseFile));
        Resource response = new FileSystemResource(file);
        return response;
    }
}
