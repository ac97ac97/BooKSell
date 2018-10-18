package example.sdbi.com.bookdatademo;

/**
 * Created by Administrator on 2018/10/15.
 */

public class BooklDatalog {
    private String id;
    private String catalog;

    public BooklDatalog(String id, String catalog) {
        this.id = id;
        this.catalog = catalog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
