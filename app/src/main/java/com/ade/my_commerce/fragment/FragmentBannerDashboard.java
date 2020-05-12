package com.ade.my_commerce.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ade.my_commerce.activity.MainActivity;
import com.ade.my_commerce.R;
import com.ade.my_commerce.utils.CarouselLinearLayout;


public class FragmentBannerDashboard extends Fragment {

    private static final String POSITON = "position";
    private static final String SCALE = "scale";

    private int screenWidth;
    private int screenHeight;

    private int[] imageArray = new int[]{R.drawable.ic_phone_confirmed, R.drawable.ic_phone_confirmed,
            R.drawable.ic_phone_confirmed};

    private String[] nameArray = new String[]{
            "Beli Pulsa/Kuota",
            "Beli Kebutuhan Dapur",
            "Beli Baju/Celana"
    };

    public static Fragment newInstance(MainActivity context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt(POSITON, pos);
        b.putFloat(SCALE, scale);

        return Fragment.instantiate(context, FragmentBannerDashboard.class.getName(), b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        final int postion = this.getArguments().getInt(POSITON);
        float scale = this.getArguments().getFloat(SCALE);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int)(screenWidth / 1.1), (int)(screenHeight / 2));
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_banner_dashboard, container, false);

        LinearLayout layoutContent = (LinearLayout) linearLayout.findViewById(R.id.layout_content);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.images);
        TextView name = (TextView) linearLayout.findViewById(R.id.name);
        CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);

        layoutContent.setLayoutParams(layoutParams);
        imageView.setImageResource(imageArray[postion]);
        name.setText(nameArray[postion]);

        root.setScaleBoth(scale);

        return linearLayout;
    }

    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }
}
