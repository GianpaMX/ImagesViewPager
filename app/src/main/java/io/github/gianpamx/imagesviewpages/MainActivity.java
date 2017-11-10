package io.github.gianpamx.imagesviewpages;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImagesPagerAdapter imagesPagerAdapter;
    private ViewPager imagesViewPager;
    private List<Drawable> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        images = getImages();

        imagesPagerAdapter = new ImagesPagerAdapter();
        imagesPagerAdapter.setImages(images);

        imagesViewPager = findViewById(R.id.imagesViewPager);
        imagesViewPager.setAdapter(imagesPagerAdapter);
    }

    @NonNull
    private List<Drawable> getImages() {
        List<Drawable> drawables = new ArrayList<>();
        try {
            AssetManager am = getAssets();
            String[] images = am.list("viewpager-images");

            InputStream is;
            for (String file : images) {
                is = am.open("viewpager-images/" + file);

                Drawable d = Drawable.createFromStream(is, null);
                drawables.add(d);

                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawables;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int currentItem = imagesViewPager.getCurrentItem();
        if (item.getItemId() == R.id.delete && currentItem < images.size()) {
            images.remove(currentItem);
            imagesPagerAdapter.setImages(images);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
