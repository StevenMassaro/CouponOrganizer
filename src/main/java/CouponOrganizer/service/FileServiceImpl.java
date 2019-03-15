package CouponOrganizer.service;

import CouponOrganizer.mapper.FileMapper;
import CouponOrganizer.model.Metafile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class FileServiceImpl {

    @Autowired
    private FileMapper fileMapper;

    private String temporaryDirectory = System.getProperty("java.io.tmpdir");

    public void insert(long id, MultipartFile multipartFile) throws IOException {
        fileMapper.insert(id, multipartFile.getContentType(), multipartFile.getSize(),
                multipartFile.getOriginalFilename(), Base64.getEncoder().encode(multipartFile.getBytes()));
    }

    public Metafile getFile(long id) {
        return fileMapper.get(id);
    }
}
