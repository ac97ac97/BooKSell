package example.sdbi.com.bookdatademo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2018/10/15.
 */

    public class MyAdapterItem extends ArrayAdapter<BookItem> {
    private int resourceId;
    public MyAdapterItem(@NonNull Context context, @LayoutRes int resource, List<BookItem> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BookItem bookItem = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivBook= (ImageView) view.findViewById(R.id.iv_book);
            viewHolder.tvReading= (TextView) view.findViewById(R.id.tv_book_person);
            viewHolder.tvTag= (TextView) view.findViewById(R.id.tv_book_tag);
            viewHolder.tvTitle= (TextView) view.findViewById(R.id.tv_book_title);
            viewHolder.tvTime= (TextView) view.findViewById(R.id.tv_book_time);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(getContext()).load(bookItem.getImg()).into(viewHolder.ivBook);
//        viewHolder.ivBook.setImageURI(Uri.parse(bookItem.getImg()));
        viewHolder.tvTitle.setText(bookItem.getTitle());
        viewHolder.tvTag.setText(bookItem.getTag());
        viewHolder.tvReading.setText(bookItem.getReading());
        viewHolder.tvTime.setText(bookItem.getBytime());

        return view;
    }

    //    private String title;
//    private String tag;
//    private String img;
//    private String reading;
//    private String bytime;
    class ViewHolder {
        TextView tvTitle;
        TextView tvTag;
        TextView tvReading;
        TextView tvTime;
        ImageView ivBook;
    }
}
