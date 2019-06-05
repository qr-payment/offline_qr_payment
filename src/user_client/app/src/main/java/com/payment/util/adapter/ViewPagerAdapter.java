package com.payment.util.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.payment.R;
import com.payment.databinding.ViewpagerItemBinding;
import com.payment.model.Card;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Card> mItems;

    public ViewPagerAdapter(List<Card> mItems) {
        this.mItems = mItems;
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
        }
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
