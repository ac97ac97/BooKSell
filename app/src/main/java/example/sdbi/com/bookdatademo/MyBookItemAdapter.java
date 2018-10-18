package example.sdbi.com.bookdatademo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2018/10/16.
 */

public class MyBookItemAdapter extends BaseAdapter {
    private Context context;
    private List<BookItem> list;

    public MyBookItemAdapter(Context context, List<BookItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        BookItem bookItem = list.get(i);
        View view;
        ViewHolder holder;
        if (contentView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.bookitem, viewGroup, false);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) view.findViewById(R.id.tv_book_title);
            holder.tvTime = (TextView) view.findViewById(R.id.tv_book_time);
            holder.tvReading = (TextView) view.findViewById(R.id.tv_book_person);
            holder.tvTag = (TextView) view.findViewById(R.id.tv_book_tag);
            holder.ivBook = (ImageView) view.findViewById(R.id.iv_book);
            view.setTag(holder);
        } else {
            view = contentView;
            holder = (ViewHolder) view.getTag();
        }
//        holder.ivBook.setImageURI(Uri.parse(bookItem.getImg()));
        holder.tvReading.setText(bookItem.getReading());
        holder.tvTag.setText(bookItem.getTag());
        holder.tvTime.setText(bookItem.getBytime());
        holder.tvTitle.setText(bookItem.getTitle());
        return view;
    }

    public static Bitmap getBitmap(String path) throws IOException {

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        return null;
    }

    class ViewHolder {
        TextView tvTitle;
        TextView tvTag;
        TextView tvReading;
        TextView tvTime;
        ImageView ivBook;
    }

}
