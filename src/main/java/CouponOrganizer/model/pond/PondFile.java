package CouponOrganizer.model.pond;

public class PondFile {
    private String id;
    private String name;
    private String type;
    private long size;
    private PondMetadata metadata;
    private String data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setSize(int size) {
        this.size = size;
    }

    public PondMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PondMetadata metadata) {
        this.metadata = metadata;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}