package example.sdbi.com.bookdatademo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/15.
 */

public class MyAdatpter extends ArrayAdapter<BooklDatalog> {
    private int resourceId;
    public MyAdatpter(@NonNull Context context, @LayoutRes int resource, List<BooklDatalog> objects) {
        super(context, resource,objects);
        this.resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BooklDatalog datalog=getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.tvId= (TextView) view.findViewById(R.id.tv_id);
            viewHolder.tvCatalog= (TextView) view.findViewById(R.id.tv_catalog);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tvId.setText(datalog.getId());
        viewHolder.tvCatalog.setText(datalog.getCatalog());

        return view;
    }
    class ViewHolder{
        TextView tvId;
        TextView tvCatalog;
    }
}
