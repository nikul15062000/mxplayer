package lib1.imaginetechnology.com.mx_player;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList file, folder;
    private InterstitialAd interstitialAd;
    Intent intent;
    String[] permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permission, 0);
        }
        listView = findViewById(R.id.list1);
        file = new ArrayList();
        folder = new ArrayList();

        getvideo(Environment.getExternalStorageDirectory().toString());

        adapter a = new adapter(this, folder);
        listView.setAdapter(a);

        interstitialAd = new InterstitialAd(this, "298920774237342_310125323116887");
        // Set listeners for the Interstitial Ad
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                startActivity(intent);
            }
        });

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("folder", folder.get(position).toString());
                intent.putExtra("file", file);
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(intent);
                }
            }
        });

    }

    void getvideo(String path) {
        File f = new File(path);
        File[] f1 = f.listFiles();

        for (int i = 0; i < f1.length; i++) {
            if (f1[i].isFile()) {
                if (f1[i].getAbsolutePath().toString().endsWith(".mp4")) {
                    file.add(f1[i].getAbsolutePath());
                    if (!folder.contains(f1[i].getParent())) {
                        folder.add(f1[i].getParent());
                        Log.d("hello2",f1[i].getParent().toString());
                    }
                }
            }
            if (f1[i].isDirectory()) {
                getvideo(f1[i].getAbsolutePath());
            }
        }


    }

}
