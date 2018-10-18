package example.sdbi.com.bookdatademo;

/**
 * Created by Administrator on 2018/10/15.
 */

public class BookItem {
    private String title;
    private String tag;
    private String img;
    private String reading;
    private String bytime;

    public BookItem(String title, String tag, String img, String reading, String bytime) {
        this.title = title;
        this.tag = tag;
        this.img = img;
        this.reading = reading;
        this.bytime = bytime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getBytime() {
        return bytime;
    }

    public void setBytime(String bytime) {
        this.bytime = bytime;
    }
}
