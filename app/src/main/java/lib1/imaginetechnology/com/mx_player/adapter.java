package lib1.imaginetechnology.com.mx_player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends BaseAdapter {
    Context mainActivity;
    ArrayList folder;
    public adapter(Context mainActivity, ArrayList folder) {
        this.mainActivity=mainActivity;
        this.folder=folder;
    }

    @Override
    public int getCount() {
        return folder.size();
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
        LayoutInflater inflater= (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=inflater.inflate(R.layout.mylayout,parent,false);

        TextView textView=convertView.findViewById(R.id.textView);

        String str[]=folder.get(position).toString().split("/");

        textView.setText(str[str.length-1].toString());

        return convertView;
    }
}
