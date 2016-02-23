package checkbooks.entity;

/**
 * Created by pavel on 24.06.15.
 */
public class ClientPageData {

    private int id;
    private int pageCount;
    private String imageBase64;

    public ClientPageData() {
    }

    public ClientPageData(int id, int pageCount, String imageBase64) {
        this.id = id;
        this.pageCount = pageCount;
        this.imageBase64 = imageBase64;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    @Override
    public String toString() {
        return "ClientPageData{" +
                "id=" + id +
                ", pageCount=" + pageCount +
                ", imageBase64='" + imageBase64 + '\'' +
                '}';
    }
}
