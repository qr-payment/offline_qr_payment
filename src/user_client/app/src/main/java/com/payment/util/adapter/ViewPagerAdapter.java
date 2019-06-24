package com.payment.util.adapter;

import android.graphics.Color;
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
    private static final int ADD_CARD_VIEW_TYPE = 1;
    private static final int CARD_VIEW_VIEW_TYPE = 2;
    private static final String TAG = "ViewPagerAdapter";

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
        if (viewType == ADD_CARD_VIEW_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_addcard, parent, false);
            return new AddCardViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item, parent, false);
            return new CardViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == CARD_VIEW_VIEW_TYPE){
            Log.e("cardName",""+mItems.get(position).getBankName()+"position->"+position+","+holder.getAdapterPosition());
            ((CardViewHolder)holder).binding.setCardViewModel(mItems.get(position));
            setCardColor(holder, mItems.get(position).getBankName());
            ((CardViewHolder)holder).binding.cardSelectedImageView.setOnClickListener(v -> {
                if (!imageCheck){
                    ((CardViewHolder)holder).binding.cardSelectedImageView.setImageResource(R.drawable.not_checked_24dp);
                    imageCheck = true;
                }else{
                    ((CardViewHolder)holder).binding.cardSelectedImageView.setImageResource(R.drawable.on_checked_24dp);
                    payMethodNum = mItems.get(position).getPaymentMethodNum();
                    payMethodType = mItems.get(position).getPaymentMethodType();

                    Log.e(TAG,""+payMethodType+" , "+payMethodNum);
                    imageCheck = false;
                }
            });
        }
    }

    private void setCardColor(RecyclerView.ViewHolder holder, String cardName){
        Log.e("carname",""+cardName);
        switch (cardName){
            case "신한":
                ((CardViewHolder)holder).binding.cardFormCardView.setBackgroundResource(R.color.ShinHan_card);
                break;
            case "삼성":
                ((CardViewHolder)holder).binding.cardFormCardView.setBackgroundResource(R.color.SamSung_card);
                break;
            case "현대":
                ((CardViewHolder)holder).binding.cardFormCardView.setBackgroundResource(R.color.HyunDai_card);
                break;
            case "BC":
                ((CardViewHolder)holder).binding.cardFormCardView.setBackgroundResource(R.color.BC_card);
                break;
            case "KB국민":
                ((CardViewHolder)holder).binding.cardFormCardView.setBackgroundResource(R.color.KB_card);
                ((CardViewHolder)holder).binding.bankNameTextView.setTextColor(Color.BLACK);
                ((CardViewHolder)holder).binding.paymentTypeTextView.setTextColor(Color.BLACK);
                ((CardViewHolder)holder).binding.cardPaymentMethodNumTextView.setTextColor(Color.BLACK);
                break;
            case "하나":
                ((CardViewHolder)holder).binding.cardFormCardView.setBackgroundResource(R.color.Hana_card);
                break;
            case "롯데":
                ((CardViewHolder)holder).binding.cardFormCardView.setBackgroundResource(R.color.Lotte_card);
                break;
            case "농협":
                ((CardViewHolder)holder).binding.cardFormCardView.setBackgroundResource(R.color.NH_card);
                break;
            case "시티":
                ((CardViewHolder)holder).binding.cardFormCardView.setBackgroundResource(R.color.Citi_bank);
                break;
            case "카카오뱅크":
                ((CardViewHolder)holder).binding.cardFormCardView.setBackgroundResource(R.color.Kakao_bank);
                ((CardViewHolder)holder).binding.bankNameTextView.setTextColor(Color.BLACK);
                ((CardViewHolder)holder).binding.paymentTypeTextView.setTextColor(Color.BLACK);
                ((CardViewHolder)holder).binding.cardPaymentMethodNumTextView.setTextColor(Color.BLACK);
                break;
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
            return ADD_CARD_VIEW_TYPE;
        } else {
            return CARD_VIEW_VIEW_TYPE;
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
        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    class AddCardViewHolder extends RecyclerView.ViewHolder {
        AddCardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
