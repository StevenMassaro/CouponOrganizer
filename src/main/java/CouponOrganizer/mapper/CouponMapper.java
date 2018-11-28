package CouponOrganizer.mapper;

import CouponOrganizer.model.Coupon;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface CouponMapper extends BaseMapper {
    String COUPON_TABLE = "coupons";
    String COUPON_SCHEMA = DATABASE + "." + COUPON_TABLE;

    @Select("SELECT * FROM " + COUPON_SCHEMA + " WHERE dateDeleted is NULL")
    List<Coupon> list();

    @Select("SELECT * FROM " + COUPON_SCHEMA + " WHERE dateDeleted is not NULL")
    List<Coupon> listDeleted();

    @Select("INSERT INTO " + COUPON_SCHEMA +
            "(\"store\", deal, \"comment\", expirationDate) " +
            "VALUES(#{store}, #{deal}, #{comment}, #{expirationDate}) " +
            "RETURNING id ")
    long insert(@Param("store") String store,
                @Param("deal") String deal,
                @Param("comment") String comment,
                @Param("expirationDate") Date expirationDate);

    @Select("SELECT * FROM " + COUPON_SCHEMA + " WHERE id= #{id}")
    Coupon get(@Param("id") long id);

    @Delete("UPDATE " + COUPON_SCHEMA + " SET dateDeleted = #{dateDeleted} WHERE id = #{id}")
    void setDateDeleted(@Param("dateDeleted") Date dateDeleted,
                        @Param("id") long id);
}