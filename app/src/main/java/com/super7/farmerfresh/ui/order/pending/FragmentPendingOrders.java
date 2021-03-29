package com.super7.farmerfresh.ui.order.pending;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.ApiClient;
import com.super7.farmerfresh.network.ApiInterface;
import com.super7.farmerfresh.network.model.CartListResponse;
import com.super7.farmerfresh.network.model.OrderPendingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentPendingOrders extends Fragment {
    List<OrderPendingResponse> pendingList;
    RecyclerView rv_pending_order;
    AdapterPendingOrder adapterPendingOrder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pending_order,container,false);
        rv_pending_order=view.findViewById(R.id.rv_pending_order);
        initView();
        return view;
    }

    private void initView(){
        adapterPendingOrder = new AdapterPendingOrder(getActivity(),pendingList);
        rv_pending_order.setHasFixedSize(true);
        rv_pending_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        getPendingOrdersResponse();
    }

    private void getPendingOrdersResponse() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<OrderPendingResponse>> call = apiService.getPendingOrders("1");
        call.enqueue(new Callback<List<OrderPendingResponse>>() {
            @Override
            public void onResponse(Call<List<OrderPendingResponse>> call, Response<List<OrderPendingResponse>> response) {
                pendingList = response.body();
                adapterPendingOrder.setPendingList(pendingList);
                rv_pending_order.setAdapter(adapterPendingOrder);
            }

            @Override
            public void onFailure(Call<List<OrderPendingResponse>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}
