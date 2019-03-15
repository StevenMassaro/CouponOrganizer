package CouponOrganizer.model;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import java.util.Base64;

public class Metafile {

    private long id;
    private String type;
    private long size;
    private String filename;
    private byte[] file;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFile() {
        return file;
    }

    public byte[] getDecodedFile() {
        return Base64.getDecoder().decode(file);
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public MediaType getMediaType() {
        String extension = FilenameUtils.getExtension(filename);
        if (StringUtils.containsIgnoreCase(extension, "pdf")) {
            return MediaType.APPLICATION_PDF;
        } else if (StringUtils.containsIgnoreCase(extension, "txt")) {
            return MediaType.TEXT_PLAIN;
        } else if (StringUtils.containsIgnoreCase(extension, "png")) {
            return MediaType.IMAGE_PNG;
        } else if (StringUtils.containsIgnoreCase(extension, "jpg") || StringUtils.containsIgnoreCase(extension, "jpeg")) {
            return MediaType.IMAGE_JPEG;
        } else if (StringUtils.containsIgnoreCase(extension, "gif")) {
            return MediaType.IMAGE_GIF;
        } else return MediaType.ALL;
    }
}
