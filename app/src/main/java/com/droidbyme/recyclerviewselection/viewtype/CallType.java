package com.droidbyme.recyclerviewselection.viewtype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.UiCompoment;
import com.droidbyme.recyclerviewselection.adapter.AdapterCallTypeCarousel;
import com.droidbyme.recyclerviewselection.model.MultiEmployee;

import java.util.ArrayList;

public class CallType extends UiCompoment implements View.OnClickListener {
    private Context mContext;
    private View view;
    private ArrayList<MultiEmployee> employeeList;
    static RecyclerView mRecyclerViewCallType;

    public CallType(@NonNull View itemView) {
        super(itemView);
    }

    public CallType(Context context, View view, ArrayList<MultiEmployee> employeeList, RecyclerView mRecyclerViewCarouselInfo) {
        super(view);
        this.mContext = context;
        this.view = view;
        this.employeeList = employeeList;
        this.mRecyclerViewCallType = mRecyclerViewCarouselInfo;
    }

    public static CallType createView(Context context, ViewGroup parent, ArrayList<MultiEmployee> employeeList, RecyclerView mRecyclerViewCarouselInfo) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_single_banner, parent, false);
        mRecyclerViewCallType = view.findViewById(R.id.recyclerView_callType);
        return new CallType(context, view, employeeList, mRecyclerViewCallType);
    }

    public void bindItemViewHolder(int position){
        AdapterCallTypeCarousel adapterCallTypeCarousel;
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(mContext, 0, false);
        adapterCallTypeCarousel = new AdapterCallTypeCarousel(mContext, employeeList);
        adapterCallTypeCarousel.setData(employeeList);
        adapterCallTypeCarousel.setParentPosition(position);
        mRecyclerViewCallType.setAdapter(adapterCallTypeCarousel);
        mRecyclerViewCallType.setLayoutManager(layoutManager1);
    }

    @Override
    public void onClick(View view) {

    }
}
