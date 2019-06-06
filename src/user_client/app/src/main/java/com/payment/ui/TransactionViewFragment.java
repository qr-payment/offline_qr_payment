package com.payment.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.payment.R;
import com.payment.databinding.FragmentTransactionViewBinding;
import com.payment.model.PaymentMethods;
import com.payment.model.viewmodel.RegistrationViewModel;
import com.payment.model.viewmodel.TransactionViewModel;
import com.payment.util.adapter.ViewPagerAdapter;

public class TransactionViewFragment extends Fragment {

    private FragmentTransactionViewBinding binding;
    private View view;
    private RegistrationViewModel viewModel;
    private TransactionViewModel transactionViewModel;
    private PaymentMethods cardList;
    private ViewPagerAdapter adapter;

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
        transactionViewModel.transactionLiveData.getValue().getUrl();
    }
}
