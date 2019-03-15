package CouponOrganizer.endpoint;

import CouponOrganizer.model.Coupon;
import CouponOrganizer.model.Metafile;
import CouponOrganizer.service.CouponServiceImpl;
import CouponOrganizer.service.CronofyServiceImpl;
import CouponOrganizer.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class CouponEndpoint {

	@Autowired
	private CouponServiceImpl couponService;

	@Autowired
    private FileServiceImpl fileService;

	@Autowired
	private CronofyServiceImpl cronofyService;

	@GetMapping("/list")
	public List<Coupon> list(){
		return couponService.list();
	}

	@GetMapping("/listDeleted")
	public List<Coupon> listDeleted(){
		return couponService.listDeleted();
	}

	@GetMapping("/get")
    public ResponseEntity<Resource> getFile(@RequestParam("id") long id) {
        Metafile metafile = fileService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metafile.getFilename() + "\"")
                .contentType(metafile.getMediaType())
                .body(new ByteArrayResource(metafile.getDecodedFile()));
	}

	@PostMapping("/insert")
	public String insert(@RequestParam("store") String store,
                         @RequestParam("deal") String deal,
                         @RequestParam(value = "comment", required = false) String comment,
                         @RequestParam(value = "expirationDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expirationDate,
                         @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        long id = couponService.insert(store, deal, comment, expirationDate);
        cronofyService.addEvent(store, deal, comment, expirationDate, id);
        if (file != null && !file.isEmpty()) {
            fileService.insert(id, file);
            return "File " + file.getOriginalFilename() + " successfully uploaded. <p><a href=\"index.html\">Home</a></p>";
        }
		return "Coupon for " + store + " successfully created. <p><a href=\"index.html\">Home</a></p>";
	}

	@GetMapping("/setDateDeleted")
	public String setDateDeleted(@RequestParam("id") long id){
        Coupon coupon = couponService.get(id);
		couponService.setDateDeleted(id);
        if (coupon.getExpirationDate() != null) {
            cronofyService.deleteEvent(coupon.getStore(), id);
        }
		return "Coupon ID " + id + " successfully marked as deleted. <p><a href=\"index.html\">Home</a></p>";

	}
}
