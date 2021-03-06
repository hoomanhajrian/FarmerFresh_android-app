package com.super7.farmerfresh.ui.order;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.ui.order.pending.FragmentPendingOrders;

public class OrderFragment extends Fragment {
    RecyclerView rv_farm;
    TextView btn_pending,btn_completed;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_orders,container,false);
        rv_farm=view.findViewById(R.id.rv_farm);
        btn_pending=view.findViewById(R.id.btn_pending);
        btn_completed=view.findViewById(R.id.btn_completed);

        replaceFragment(new FragmentPendingOrders());
        btn_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new FragmentPendingOrders());
                btn_pending.setBackground(getResources().getDrawable(R.color.Pri_Green_1));
                btn_completed.setBackground(getResources().getDrawable(R.drawable.bg_round_green_border));
                btn_pending.setTextColor(getResources().getColor(R.color.back_white));
                btn_completed.setTextColor(getResources().getColor(R.color.Sec_black));
            }
        });
        btn_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new FragmentPendingOrders());
                btn_completed.setBackground(getResources().getDrawable(R.color.Pri_Green_1));
                btn_pending.setBackground(getResources().getDrawable(R.drawable.bg_round_green_border));
                btn_pending.setTextColor(getResources().getColor(R.color.Sec_black));
                btn_completed.setTextColor(getResources().getColor(R.color.back_white));
            }
        });
        return view;
    }
    private void replaceFragment(Fragment fragment){

        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.order_container,fragment);
        transaction.commitAllowingStateLoss();
    }

    private void bgRound(View view,int color){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
//        gradientDrawable.setStroke(3,color);
//        gradientDrawable.setCornerRadii(new float[] { 10, 10, 10, 10, 0, 0, 0, 0 });
//        gradientDrawable.setCornerRadius(25);
//or setCornerRadius(gradientDrawable, 20f, 40f, 60f, 80f);
        view.setBackground(gradientDrawable);
    }
}
