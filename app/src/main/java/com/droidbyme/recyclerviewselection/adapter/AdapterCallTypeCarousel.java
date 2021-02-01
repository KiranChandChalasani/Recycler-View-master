package com.droidbyme.recyclerviewselection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.model.MultiEmployee;

import java.util.List;

public class AdapterCallTypeCarousel extends RecyclerView.Adapter<AdapterCallTypeCarousel.CarouselDataViewHolder> {
    private final Context mContext;
    List<MultiEmployee> employeeList;
    private int mParentPosition;

    public AdapterCallTypeCarousel(Context context, List<MultiEmployee> employeeList) {
        this.mContext = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public CarouselDataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CarouselDataViewHolder customItemViewHolder;
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_single_banner, viewGroup, false);
        customItemViewHolder = new CarouselDataViewHolder(view);
        return customItemViewHolder;
    }

    public void setData(List<MultiEmployee> multiEmployees) {
        if (multiEmployees == null) {
            return;
        }
        //notifyDataSetChanged();
    }

    public void setParentPosition(int mParentPosition) {
        this.mParentPosition = mParentPosition;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCallTypeCarousel.CarouselDataViewHolder carouselDataViewHolder, int position) {
        setCallDetails(carouselDataViewHolder, employeeList.get(position));
    }

    void setCallDetails(final CarouselDataViewHolder holder, MultiEmployee employee) {
        Glide.with(mContext)
                .load(employee.getImage())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .into(holder.bannerImage);
        //holder.txtName.setText(employee.getName());
        //holder.txtAddress.setText(employee.getAddress());
    }

    private static final int TYPE_ITEM = 1;

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        if (employeeList == null) return 0;
        return employeeList.size();
    }

    public class CarouselDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //TextView txtName;
        //TextView txtAddress;
        ImageView bannerImage;

        public CarouselDataViewHolder(@NonNull View itemView) {
            super(itemView);
            //txtName = itemView.findViewById(R.id.txtName);
            //txtAddress = itemView.findViewById(R.id.txtAddress);
            bannerImage = itemView.findViewById(R.id.bannerImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
