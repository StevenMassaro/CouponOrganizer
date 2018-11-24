package CouponOrganizer.mapper;

import CouponOrganizer.model.Coupon;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface CouponMapper extends BaseMapper {
    String COUPON_TABLE = "coupons";
    String COUPON_SCHEMA = DATABASE + "." + COUPON_TABLE;
    String FILE_TABLE = "file";
    String FILE_SCHEMA = DATABASE + "." + FILE_TABLE;

    //    @Select("SELECT \"store\", deal, \"comment\", expirationDate FROM " + COUPON_SCHEMA)
    @Select("SELECT * FROM " + COUPON_SCHEMA)
    List<Coupon> list();

    @Select("INSERT INTO " + COUPON_SCHEMA +
            "(\"store\", deal, \"comment\", expirationDate) " +
            "VALUES(#{store}, #{deal}, #{comment}, #{expirationDate}) " +
            "RETURNING id ")
    long insert(@Param("store") String store,
                @Param("deal") String deal,
                @Param("comment") String comment,
                @Param("expirationDate") Date expirationDate);

    //    @Select("select c.id, c.store, c.deal, c.comment, c.expirationdate, f.file, f.extension " +
//            "from " + COUPON_SCHEMA + " c " +
//            "join " + FILE_SCHEMA + " f " +
//            "on c.id = f.id " +
//            "where c.id = #{id}")
    @Select("SELECT * FROM " + COUPON_SCHEMA + " WHERE id= #{id}")
    Coupon get(@Param("id") long id);
}
