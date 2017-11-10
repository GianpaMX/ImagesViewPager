package io.github.gianpamx.imagesviewpages;

import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImagesPagerAdapter extends PagerAdapter {
    private final List<Drawable> images = new ArrayList<>();

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = (ImageView) LayoutInflater.from(container.getContext()).inflate(R.layout.image_view, container, false);
        imageView.setImageDrawable(images.get(position));

        container.addView(imageView);

        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setImages(List<Drawable> images) {
        this.images.clear();
        this.images.addAll(images);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        ImageView imageView = (ImageView) object;
        for (int i = 0; i < images.size(); i++) {
            if (imageView.getDrawable().getConstantState().equals(images.get(i).getConstantState())) {
                return i;
            }
        }
        return POSITION_NONE;
    }
}
