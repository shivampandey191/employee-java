package com.graphhene.densenium;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment{

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.dashboard_fragment, container, false);

        models = new ArrayList<>();
        models.add(new Model(R.raw.lottie1,"Title 1","DESCRIPTI  DH  DHVN DH DUYV CJV SVG B DYV V DHV DFDH FJV  FY HV DFHVD DSHVDS FDJVDS DSY"));
        models.add(new Model(R.raw.lottie2,"Title 2","DESCRIPTI  DH  DHVN DH DUYV CJV SVG B DYV V DHV DFDH FJV  FY HV DFHVD DSHVDS FDJVDS DSY"));
        models.add(new Model(R.raw.lottie3,"Title 3","DESCRIPTI  DH  DHVN DH DUYV CJV SVG B DYV V DHV DFDH FJV  FY HV DFHVD DSHVDS FDJVDS DSY"));
        models.add(new Model(R.raw.lottie4,"Title 4","DESCRIPTI  DH  DHVN DH DUYV CJV SVG B DYV V DHV DFDH FJV  FY HV DFHVD DSHVDS FDJVDS DSY"));
        models.add(new Model(R.raw.lottie5,"Title 5","DESCRIPTI  DH  DHVN DH DUYV CJV SVG B DYV V DHV DFDH FJV  FY HV DFHVD DSHVDS FDJVDS DSY"));


        adapter = new Adapter(models,getContext());

        viewPager = root.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);
        Integer[] colors_temp= {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5)
        };

        colors =colors_temp;
        
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < (adapter.getCount()-1)&& position < (colors.length-1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }
                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return root;
    }
}
