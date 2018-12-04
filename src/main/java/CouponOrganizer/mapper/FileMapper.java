package CouponOrganizer.mapper;

import CouponOrganizer.model.Metafile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper extends BaseMapper {

    String TABLE = "file";
    String SCHEMA = DATABASE + "." + TABLE;

    @Insert("INSERT INTO " + SCHEMA +
            " (id, type, size, filename, \"file\")" +
            "VALUES (#{id}, #{type}, #{size}, #{filename}, #{file})")
    void insert(@Param("id") long id,
                @Param("type") String type,
                @Param("size") long size,
                @Param("filename") String filename,
                @Param("file") byte[] file);

    @Select("SELECT * FROM " + SCHEMA + " WHERE id = #{id}")
    Metafile get(@Param("id") long id);
}
