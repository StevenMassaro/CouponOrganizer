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
            " (id, \"file\", extension)" +
            "VALUES (#{id}, #{file}, #{extension})")
    void insert(@Param("id") long id,
                @Param("file") byte[] file,
                @Param("extension") String extension);

    @Select("SELECT * FROM " + SCHEMA + " WHERE id = #{id}")
    Metafile get(@Param("id") long id);
}
