package CouponOrganizer.service;

import CouponOrganizer.mapper.CouponMapper;
import CouponOrganizer.model.Coupon;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CouponServiceImpl {

	@Autowired
	private CouponMapper couponMapper;

	public List<Coupon> list(){
		return couponMapper.list2();
	}

	public void insert(String store,
					   String deal,
					   String comment,
					   byte[] file){
		couponMapper.insert(store, deal, comment, file);
	}

	public Resource file2() throws IOException {
		byte[] dbfile = couponMapper.file().getFile();
		File file = File.createTempFile("temp", ".pdf");
		FileUtils.writeByteArrayToFile(file, Base64.getDecoder().decode(dbfile));
		Resource response = new FileSystemResource(file);
		return response;
	}
}
