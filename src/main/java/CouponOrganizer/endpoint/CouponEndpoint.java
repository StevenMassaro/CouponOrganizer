package CouponOrganizer.endpoint;

import CouponOrganizer.model.Coupon;
import CouponOrganizer.model.pond.PondFile;
import CouponOrganizer.service.CouponServiceImpl;
import CouponOrganizer.service.CronofyServiceImpl;
import CouponOrganizer.service.FileServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Resource> getFile(@RequestParam("id") long id) throws IOException {
		Resource file = fileService.getFile(id);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/insert")
	public String insert(@RequestParam("store") String store,
                         @RequestParam("deal") String deal,
                         @RequestParam(value = "comment", required = false) String comment,
                         @RequestParam(value = "expirationDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expirationDate,
                         @RequestParam(value = "file", required = false) String file) {
        long id = couponService.insert(store, deal, comment, expirationDate);
        cronofyService.addEvent(store, deal, comment, expirationDate, id);
        if (!StringUtils.isEmpty(file)) {
            Gson gson = new Gson();
            PondFile pondFile = gson.fromJson(file, PondFile.class);
            fileService.insert(id, pondFile);
            return "File " + pondFile.getName() + " successfully uploaded. <p><a href=\"index.html\">Home</a></p>";
        }
		return "Coupon for " + store + " successfully created. <p><a href=\"index.html\">Home</a></p>";
	}

	@GetMapping("/setDateDeleted")
	public String setDateDeleted(@RequestParam("id") long id){
		couponService.setDateDeleted(id);
		return "Coupon ID " + id + " successfully marked as deleted. <p><a href=\"index.html\">Home</a></p>";

	}
}
