package com.ade.my_commerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ade.my_commerce.R;
import com.ade.my_commerce.adapter.AdapterGridShopProductCard;
import com.ade.my_commerce.adapter.AdapterBannerDashboard;
import com.ade.my_commerce.model.ShopProduct;
import com.ade.my_commerce.utils.DataGenerator;
import com.ade.my_commerce.utils.SpacingItemDecoration;
import com.ade.my_commerce.utils.Tools;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static int LOOPS = 1000;
    public AdapterBannerDashboard adapter;
    public ViewPager pager;
    public static int count = 3;
    public static int FIRST_PAGE = 3;

    private LinearLayout parent_view;

    private RecyclerView recyclerView;
    private AdapterGridShopProductCard mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initViewPager();
        initComponent();
    }

    private void initToolbar(){
        Tools.setSystemBarColor(this,R.color.white);
        Tools.setSystemBarLight(this);

    }

    private void initViewPager(){
        pager = (ViewPager) findViewById(R.id.myviewpager);

        //set page margin between pages for viewpager
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = (int)((metrics.widthPixels / 8.5) * 2);
        pager.setPageMargin(-pageMargin);

        adapter = new AdapterBannerDashboard(this, getSupportFragmentManager());
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        pager.addOnPageChangeListener(adapter);
        pager.setCurrentItem(FIRST_PAGE);
        pager.setOffscreenPageLimit(3);
    }
    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 8), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        List<ShopProduct> items = DataGenerator.getShoppingProduct(this);

        //set data and list adapter
        mAdapter = new AdapterGridShopProductCard(this, items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterGridShopProductCard.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ShopProduct obj, int position) {
                Intent intent = new Intent(MainActivity.this,DetailProduct.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnMoreButtonClickListener(new AdapterGridShopProductCard.OnMoreButtonClickListener() {
            @Override
            public void onItemClick(View view, ShopProduct obj, MenuItem item) {
                Toast.makeText(getApplicationContext(), obj.title + " (" + item.getTitle() + ") clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,DetailProduct.class);
                startActivity(intent);
            }
        });

    }

}
