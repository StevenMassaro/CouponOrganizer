package CouponOrganizer.service;

import CouponOrganizer.mapper.FileMapper;
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
    private FileMapper fileMapper;

    private String temporaryDirectory = System.getProperty("java.io.tmpdir");

    public void insert(long id, MultipartFile multipartFile) throws IOException {
        fileMapper.insert(id, multipartFile.getContentType(), multipartFile.getSize(),
                multipartFile.getOriginalFilename(), Base64.getEncoder().encode(multipartFile.getBytes()));
    }

    public Resource getFile(long id) throws IOException {
        Metafile metafile = fileMapper.get(id);

        byte[] databaseFile = metafile.getFile();
        File file = new File(temporaryDirectory + File.separator + metafile.getFilename());
        file.deleteOnExit();
        FileUtils.writeByteArrayToFile(file, Base64.getDecoder().decode(databaseFile));
        Resource response = new FileSystemResource(file);
        return response;
    }
}
