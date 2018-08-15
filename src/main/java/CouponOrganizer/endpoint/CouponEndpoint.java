package CouponOrganizer.endpoint;

import CouponOrganizer.model.Coupon;
import CouponOrganizer.service.CouponServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
public class CouponEndpoint {

	@Autowired
	private CouponServiceImpl couponService;

	@GetMapping("/list")
	public List<Coupon> list(){
		return couponService.list();
	}

	@GetMapping("/test")
	public String test(){
		return "test";
	}

	@GetMapping("/file")
	public ResponseEntity<Resource> file() throws IOException {
		Resource file = couponService.file2();
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/")
	public String putin(@RequestParam("store") String store,
					  @RequestParam("deal") String deal,
					  @RequestParam("comment") String comment,
					  @RequestParam("file") MultipartFile file,
					  RedirectAttributes redirectAttributes) throws IOException {
		System.out.println(Arrays.toString(file.getBytes()));

		couponService.insert(store, deal, comment, Base64.getEncoder().encode(file.getBytes()));

		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}
}
