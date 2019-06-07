package com.payment.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.payment.R;
import com.payment.databinding.FragmentTransactionViewBinding;
import com.payment.model.PaymentMethods;
import com.payment.model.TransactionRequest;
import com.payment.model.User;
import com.payment.model.viewmodel.RegistrationViewModel;
import com.payment.model.viewmodel.TransactionViewModel;
import com.payment.util.adapter.ViewPagerAdapter;

public class TransactionViewFragment extends Fragment{

    private FragmentTransactionViewBinding binding;
    private View view;
    private RegistrationViewModel viewModel;
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
        viewModel = ViewModelProviders.of(requireActivity()).get(RegistrationViewModel.class);
        transactionViewModel = ViewModelProviders.of(requireActivity()).get(TransactionViewModel.class);
        binding.setLifecycleOwner(this);
        transactionViewModel.serverChecker.setValue(false);
        cardList = new PaymentMethods();
        transactionRequest = new TransactionRequest();

        if (transactionViewModel.cardInfo.getValue() != null){
            cardList.setMethods(transactionViewModel.cardInfo.getValue().getMethods());
            adapter = new ViewPagerAdapter(cardList.getMethods());
            binding.transactionViewPager.setAdapter(adapter);
        }

        binding.transactionViewPager.getAdapter().getItemCount();

        transactionViewModel.user.observe(requireActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user.getTransactionPw() != null){
                    transactionRequest.setTransactionPw(user.getTransactionPw());
                    Log.e("결제하기",""+transactionViewModel.user.getValue().getTransactionPw());
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("transaction url",""+transactionViewModel.transactionLiveData.getValue().getUrl());

        binding.merchantNameTextView.setText(transactionViewModel.transactionLiveData.getValue().getMerchantName());
        binding.resultAmountTextView.setText(Integer.toString(transactionViewModel.transactionLiveData.getValue().getAmount()));
        binding.amountTextView.setText(Integer.toString(transactionViewModel.transactionLiveData.getValue().getAmount()));
        binding.productNameTextView.setText(transactionViewModel.transactionLiveData.getValue().getProductName());


        //결제하기버튼
        binding.sendTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactionViewModel.recycleFragment.setValue(true);
                getFragmentManager().beginTransaction()
                        .add(R.id.main_container_view,new TransactionPWFragment())
                        .commit();
                transactionRequest.setAmount(transactionViewModel.transactionLiveData.getValue().getAmount());
                transactionRequest.setCount(transactionViewModel.transactionLiveData.getValue().getCount());
                transactionRequest.setProductName(transactionViewModel.transactionLiveData.getValue().getProductName());

                if (adapter.getPayMethodNum() != null && adapter.getPayMethodType() != null){
                    transactionRequest.setMethodNum(adapter.getPayMethodNum());
                    transactionRequest.setMethodType(adapter.getPayMethodType());
                    Log.e("fragment",""+adapter.getPayMethodType() + " , "+adapter.getPayMethodNum());
                }

                transactionViewModel.transactionRequestMutableLiveData.setValue(transactionRequest);

                Log.e("결제하기2",""+transactionRequest.getMethodNum()+" , "+transactionRequest.getMethodType()+" , "+transactionRequest.getProductName());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("fragment","onresume");
    }
}
