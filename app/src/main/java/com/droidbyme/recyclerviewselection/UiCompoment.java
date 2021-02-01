package com.droidbyme.recyclerviewselection;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.droidbyme.recyclerviewselection.model.MultiEmployee;

/**
 * Created by apalya on 11/24/2016.
 */

public abstract class UiCompoment extends RecyclerView.ViewHolder {

    public int position;

    public interface UiCompomentListenerInterface{
        void notifyDataChanged(int position);
        void notifyItemNeedToBeRemoved(MultiEmployee carouselInfoData, int position);
    }

    public UiCompomentListenerInterface uiCompomentListenerInterface;

    public void setUiCompomentListenerInterface(UiCompomentListenerInterface uiCompomentListenerInterface) {
        this.uiCompomentListenerInterface = uiCompomentListenerInterface;
    }

    public UiCompoment(View itemView) {
        super(itemView);
            }

    public  abstract void bindItemViewHolder(int position);

    public void notifyItemChanged(){
        if(uiCompomentListenerInterface != null){
            uiCompomentListenerInterface.notifyDataChanged(position);
        }
    }
    public void notifyItemRemoved(MultiEmployee carouselInfoData){
        if(uiCompomentListenerInterface != null){
            uiCompomentListenerInterface.notifyItemNeedToBeRemoved(carouselInfoData,position);
        }
    }
}
