package CouponOrganizer.mapper;

import CouponOrganizer.model.Coupon;
import org.apache.ibatis.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface CouponMapper {
	@Select("SELECT * FROM coupons.newtable")
	List<Coupon> list2();

	@Insert("INSERT INTO coupons.newtable" +
			"(\"store\", deal, \"comment\", \"file\")" +
			"VALUES(#{store}, #{deal}, #{comment}, #{file})")
	void insert(@Param("store") String store,
				@Param("deal") String deal,
				@Param("comment") String comment,
				@Param("file") byte[] file);

	@Select("SELECT file FROM coupons.newtable WHERE id=3")
	Coupon file();
}
