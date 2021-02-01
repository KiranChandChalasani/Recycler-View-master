package com.droidbyme.recyclerviewselection.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.UiCompoment;
import com.droidbyme.recyclerviewselection.model.MultiEmployee;
import com.droidbyme.recyclerviewselection.viewtype.CallType;

import java.util.ArrayList;

public class MultipleTypeAdapter extends RecyclerView.Adapter<UiCompoment> {

    private static int TYPE_CALL = 1;
    private static int TYPE_EMAIL = 2;
    private Context context;
    private ArrayList<MultiEmployee> employees;
    private RecyclerView mRecyclerViewCarouselInfo;

    public MultipleTypeAdapter(Context context, ArrayList<MultiEmployee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public UiCompoment onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        UiCompoment viewHolder = null;
        if (viewType == TYPE_CALL) { // for call layout
            /*view = LayoutInflater.from(context).inflate(R.layout.item_call, viewGroup, false);
            return new CallViewHolder(view);*/
            viewHolder = CallType.createView(context, viewGroup, employees, mRecyclerViewCarouselInfo);
            viewHolder.setUiCompomentListenerInterface(uiCompomentListenerInterface);

        } else { // for email layout
            /*view = LayoutInflater.from(context).inflate(R.layout.item_email, viewGroup, false);
            return new EmailViewHolder(view);*/
        }
        return viewHolder;
    }

    private UiCompoment.UiCompomentListenerInterface uiCompomentListenerInterface = new UiCompoment.UiCompomentListenerInterface() {
        @Override
        public void notifyDataChanged(int position) {
            notifyItemChanged(position);
        }

        @Override
        public void notifyItemNeedToBeRemoved(final MultiEmployee carouselInfoData, final int position) {
            if (employees != null) {
                employees.remove(carouselInfoData);
                if (mRecyclerViewCarouselInfo != null) {
                    mRecyclerViewCarouselInfo.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (!mRecyclerViewCarouselInfo.isComputingLayout()) {
                                    //Log.d(TAG,"isComputingLayout called");
//                                    notifyItemRemoved(position);
//                                    if(mRecyclerViewCarouselInfo!=null) {
//                                        mRecyclerViewCarouselInfo.removeViewAt(position);
//                                    }
////                                    setCarouselInfoData(mListCarouselInfo);
//                                    notifyItemRangeChanged(position, mListCarouselInfo.size());
//                                    notifyDataSetChanged();

                                    notifyItemRangeChanged(position, employees.size());
                                    notifyItemRemoved(position);
                                } else {
                                    notifyItemNeedToBeRemoved(carouselInfoData, position);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                //Crashlytics.logException(e);
                            }
                        }
                    });
                }
            }
        }
    };

    @Override
    public void onBindViewHolder(@NonNull UiCompoment holder, int position) {
        holder.bindItemViewHolder(position);
    }

    @Override
    public int getItemViewType(int position) {
        /*if (TextUtils.isEmpty(employees.get(position).getEmail())) {
            return TYPE_CALL;
        } else {
            return TYPE_EMAIL;
        }*/
        return TYPE_CALL;
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(1, 100);
        recyclerView.setItemViewCacheSize(100);
    }

}
