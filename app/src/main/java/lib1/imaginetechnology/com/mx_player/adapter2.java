package lib1.imaginetechnology.com.mx_player;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.ThumbnailUtils.createVideoThumbnail;
import static android.provider.Telephony.Mms.Addr.ADDRESS;

public class adapter2 extends BaseAdapter {
    Main2Activity main2Activity;
    ArrayList tmp;
    public adapter2(Main2Activity main2Activity, ArrayList tmp) {
        this.main2Activity=main2Activity;
        this.tmp=tmp;
    }

    @Override
    public int getCount() {
        return tmp.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) main2Activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.mylayout2,parent,false);

        ImageView imageView=convertView.findViewById(R.id.imageView);


        TextView textView=convertView.findViewById(R.id.textView2);
        String str[]=tmp.get(position).toString().split("/");
        textView.setText(str[str.length-1].toString());
        Bitmap bitmap= ThumbnailUtils.createVideoThumbnail(tmp.get(position).toString(),MediaStore.Video.Thumbnails.MINI_KIND);
        imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
