package com.payment.util.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.payment.R;
import com.payment.databinding.ViewpagerItemBinding;
import com.payment.model.Method;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Method> mItems;
    private boolean imageCheck = true;
    private String payMethodNum;
    private String payMethodType;

    public ViewPagerAdapter(List<Method> list){
        mItems = list;
        mItems.add(null);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_addcard, parent, false);
            return new AddCardViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item, parent, false);
            return new CardViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 2){
            ((CardViewHolder)holder).binding.setCardViewModel(mItems.get(position));
            ((CardViewHolder)holder).binding.cardSelectedImageView.setOnClickListener(v -> {
                if (!imageCheck){
                    ((CardViewHolder)holder).binding.cardSelectedImageView.setImageResource(R.drawable.not_checked_24dp);
                    imageCheck = true;
                }else{
                    ((CardViewHolder)holder).binding.cardSelectedImageView.setImageResource(R.drawable.on_checked_24dp);
                    payMethodNum = mItems.get(position).getPaymentMethodNum();
                    payMethodType = mItems.get(position).getPaymentMethodType();
                    Log.e("adapter",""+payMethodType+" , "+payMethodNum);
                    imageCheck = false;
                }
            });
        }
    }

    public String getPayMethodNum(){
        return payMethodNum;
    }

    public String getPayMethodType(){
        return payMethodType;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mItems.size() - 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        if (mItems == null)
            return 0;
        return mItems.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ViewpagerItemBinding binding;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    class AddCardViewHolder extends RecyclerView.ViewHolder {
        public AddCardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
