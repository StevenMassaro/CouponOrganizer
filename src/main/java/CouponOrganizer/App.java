package CouponOrganizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
//public class App implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

//	final private CouponMapper cityMapper;
//
//	public App(CouponMapper cityMapper) {
//		this.cityMapper = cityMapper;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(this.cityMapper.list());
//	}
}
