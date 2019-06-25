package com.payment.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.payment.R;
import com.payment.databinding.FragmentTransactionViewBinding;
import com.payment.model.PaymentMethods;
import com.payment.model.TransactionRequest;
import com.payment.model.viewmodel.TransactionViewModel;
import com.payment.util.adapter.ViewPagerAdapter;

import java.text.NumberFormat;

public class TransactionViewFragment extends Fragment{

    private FragmentTransactionViewBinding binding;
    private View view;
    private TransactionViewModel transactionViewModel;
    private PaymentMethods cardList;
    private ViewPagerAdapter adapter;
    private TransactionRequest transactionRequest;

    public TransactionViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_view, container, false);
        view = binding.getRoot();
        transactionViewModel = ViewModelProviders.of(requireActivity()).get(TransactionViewModel.class);
        binding.setLifecycleOwner(this);
        transactionViewModel.serverChecker.setValue(false);
        cardList = new PaymentMethods();
        transactionRequest = new TransactionRequest();
        binding.shoppingInfoImgView.setBackgroundColor(Color.rgb(69,78,151));
        binding.shoppingInfoImgView2.setBackgroundColor(Color.rgb(69,78,151));
        binding.shoppingInfoImgView3.setBackgroundColor(Color.rgb(221,53,46));

        if (transactionViewModel.cardInfo.getValue() != null){
            cardList.setMethods(transactionViewModel.cardInfo.getValue().getMethods());
            adapter = new ViewPagerAdapter(cardList.getMethods());
            binding.transactionViewPager.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("transaction url",""+transactionViewModel.transactionLiveData.getValue().getUrl());

        binding.merchantNameTextView.setText(transactionViewModel.transactionLiveData.getValue().getMerchantName());
        String amount = NumberFormat.getInstance().format(transactionViewModel.transactionLiveData.getValue().getAmount());
        binding.resultAmountTextView.setText(amount+" 원");
        binding.resultAmountTextView2.setText(amount+" 원");
        binding.amountTextView.setText(amount+" 원");
        binding.productNameTextView.setText(transactionViewModel.transactionLiveData.getValue().getProductName());

        transactionRequest.setAmount(transactionViewModel.transactionLiveData.getValue().getAmount());
        transactionRequest.setCount(transactionViewModel.transactionLiveData.getValue().getCount());
        transactionRequest.setProductName(transactionViewModel.transactionLiveData.getValue().getProductName());



        binding.expandButton1.setOnClickListener(v -> {
            if (binding.expandableLayout.isExpanded()) {
                binding.arrowImage1.setImageResource(R.drawable.down_arrow);
                binding.expandableLayout.collapse();
            } else {
                binding.arrowImage1.setImageResource(R.drawable.up_arrow);
                binding.expandableLayout.expand();
            }
        });

        binding.expandButton2.setOnClickListener(v -> {
            if (binding.expandableLayout2.isExpanded()){
                binding.arrowImage2.setImageResource(R.drawable.down_arrow);
                binding.expandableLayout2.collapse();
            }else{
                binding.arrowImage2.setImageResource(R.drawable.up_arrow);
                binding.expandableLayout2.expand();
            }
        });

        binding.expandButton3.setOnClickListener(v -> {
            if (binding.expandableLayout3.isExpanded()){
                binding.arrowImage3.setImageResource(R.drawable.down_arrow);
                binding.expandableLayout3.collapse();
            }else{
                binding.arrowImage3.setImageResource(R.drawable.up_arrow);
                binding.expandableLayout3.expand();
            }
        });

        //결제하기버튼
        binding.sendTransaction.setOnClickListener(v -> {
            if (adapter.getPayMethodNum() != null && adapter.getPayMethodType() != null){
                transactionViewModel.recycleFragment.setValue(true);

                transactionRequest.setMethodNum(adapter.getPayMethodNum());
                transactionRequest.setMethodType(adapter.getPayMethodType());

                transactionViewModel.setTransactionRequest(transactionRequest);

                getFragmentManager().beginTransaction()
                        .add(R.id.main_container_view,new TransactionPWFragment())
                        .addToBackStack(null)
                        .commit();
            }else{
                Toast.makeText(getActivity(), "거래 수단을 선택해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
