package CouponOrganizer.endpoint;

import CouponOrganizer.model.Coupon;
import CouponOrganizer.service.CouponServiceImpl;
import CouponOrganizer.service.FileServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class CouponEndpoint {

	@Autowired
	private CouponServiceImpl couponService;

	@Autowired
    private FileServiceImpl fileService;

	@GetMapping("/list")
	public List<Coupon> list(){
		return couponService.list();
	}

	@GetMapping("/listDeleted")
	public List<Coupon> listDeleted(){
		return couponService.listDeleted();
	}

	@GetMapping("/get")
	public ResponseEntity<Resource> getFile(@RequestParam("id") long id) throws IOException {
		Resource file = fileService.getFile(id);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/insert")
	public String insert(@RequestParam("store") String store,
					  @RequestParam("deal") String deal,
					  @RequestParam("comment") String comment,
					  @RequestParam("expirationDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date expirationDate,
					  @RequestParam("file") MultipartFile file,
					  RedirectAttributes redirectAttributes) throws IOException {
		long id = couponService.insert(store, deal, comment, expirationDate);
		fileService.insert(id, file, FilenameUtils.getExtension(file.getOriginalFilename()));

		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");


		return "File " + file.getOriginalFilename() + " successfully uploaded. <p><a href=\"index.html\">Home</a></p>";
	}

	@DeleteMapping("/setDateDeleted")
	public void setDateDeleted(@RequestParam("id") long id){
		couponService.setDateDeleted(id);
	}
}
