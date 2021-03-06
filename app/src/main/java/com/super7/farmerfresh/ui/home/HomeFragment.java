package com.super7.farmerfresh.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.super7.farmerfresh.R;

public class HomeFragment extends Fragment {

    RecyclerView rv_farm;
    AdapterHome adapterHome;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        rv_farm=view.findViewById(R.id.rv_farm);
        initView();
        return view;
    }

    private void initView(){
        adapterHome = new AdapterHome(getActivity());
        rv_farm.setHasFixedSize(true);
        rv_farm.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_farm.setAdapter(adapterHome);
    }
}