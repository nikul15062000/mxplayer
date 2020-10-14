package lib1.imaginetechnology.com.mx_player;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    ArrayList file,tmp;
    String folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.list2);
        file = new ArrayList();
        tmp = new ArrayList();
        file = getIntent().getStringArrayListExtra("file");
        folder = getIntent().getStringExtra("folder");


        Log.d("FoldeePath",file.get(2).toString());

        for (int i = 0; i < file.size(); i++)
        {
            if(file.get(i).toString().contains(folder))
            {
                tmp.add(file.get(i));
            }
        }

        adapter2 a1=new adapter2(this,tmp);
        listView.setAdapter(a1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("vedio",tmp.get(position).toString());
                intent.putExtra("total",tmp.size());
                startActivity(intent);
            }
        });







    }
}
