package com.super7.farmerfresh.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.super7.farmerfresh.network.ApiClient;
import com.super7.farmerfresh.network.ApiInterface;
import com.super7.farmerfresh.network.model.FarmListResponse;
import com.super7.farmerfresh.ui.filter.ActivityFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {

    RecyclerView rv_farm;
    AdapterHome adapterHome;
    ImageView filter;
    List<FarmListResponse> farmList;

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
        adapterHome = new AdapterHome(getActivity(),farmList);
        rv_farm.setHasFixedSize(true);
        rv_farm.setLayoutManager(new LinearLayoutManager(getActivity()));
        getFarmsResponse();

    }

    private void getFarmsResponse() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<FarmListResponse>> call = apiService.getFarms();
        call.enqueue(new Callback<List<FarmListResponse>>() {
            @Override
            public void onResponse(Call<List<FarmListResponse>> call, Response<List<FarmListResponse>> response) {
                farmList = response.body();
                Log.d("TAG","Response = "+farmList);
                adapterHome.setFarmList(farmList);
                rv_farm.setAdapter(adapterHome);
            }

            @Override
            public void onFailure(Call<List<FarmListResponse>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), ActivityFilter.class));
    }
}