package CouponOrganizer.mapper;

import CouponOrganizer.model.Coupon;
import org.apache.ibatis.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Mapper
public interface CouponMapper {
	String DATABASE = "coupons";
	String TABLE = "coupons";
	String SCHEMA = DATABASE + "." + TABLE;

	@Select("SELECT \"store\", deal, \"comment\", expirationDate FROM " + SCHEMA)
	List<Coupon> list();

	@Insert("INSERT INTO " + SCHEMA +
			"(\"store\", deal, \"comment\", expirationDate, \"file\")" +
			"VALUES(#{store}, #{deal}, #{comment}, #{expirationDate}, #{file})")
	void insert(@Param("store") String store,
				@Param("deal") String deal,
				@Param("comment") String comment,
				@Param("expirationDate") Date expirationDate,
				@Param("file") byte[] file);

	@Select("SELECT * FROM " + SCHEMA + " WHERE id= #{id}")
	Coupon get(@Param("id") long id);
}
