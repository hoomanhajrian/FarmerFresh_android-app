package com.super7.farmerfresh.ui.order.pending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.super7.farmerfresh.R;


public class FragmentPendingOrders extends Fragment {
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
        adapterPendingOrder = new AdapterPendingOrder(getActivity());
        rv_pending_order.setHasFixedSize(true);
        rv_pending_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_pending_order.setAdapter(adapterPendingOrder);
    }
}
