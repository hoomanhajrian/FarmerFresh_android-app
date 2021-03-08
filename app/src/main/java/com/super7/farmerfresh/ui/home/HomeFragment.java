package com.super7.farmerfresh.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.ui.filter.ActivityFilter;

public class HomeFragment extends Fragment implements View.OnClickListener {

    RecyclerView rv_farm;
    AdapterHome adapterHome;
    ImageView filter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        rv_farm=view.findViewById(R.id.rv_farm);
        filter=view.findViewById(R.id.filter);
        filter.setOnClickListener(this);
        initView();
        return view;
    }

    private void initView(){
        adapterHome = new AdapterHome(getActivity());
        rv_farm.setHasFixedSize(true);
        rv_farm.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_farm.setAdapter(adapterHome);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), ActivityFilter.class));
    }
}