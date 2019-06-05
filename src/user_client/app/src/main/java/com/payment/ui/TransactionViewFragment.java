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
import com.payment.model.Card;
import com.payment.model.viewmodel.RegistrationViewModel;
import com.payment.util.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransactionViewFragment extends Fragment {

    private FragmentTransactionViewBinding binding;
    private View view;
    private RegistrationViewModel viewModel;
    private List<Card> cardList;
    private Card selectedCard = null;

    public TransactionViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_view, container, false);
        view = binding.getRoot();
        viewModel = ViewModelProviders.of(requireActivity()).get(RegistrationViewModel.class);
        binding.setLifecycleOwner(this);
        cardList = new ArrayList<>();
        setCardInfo();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPagerAdapter adapter = new ViewPagerAdapter(cardList);
        binding.transactionViewPager.setAdapter(adapter);
    }

    private void setCardInfo(){
        if (viewModel.cardLiveData.getValue()!= null){
            cardList.add(viewModel.cardLiveData.getValue());
        }
    }
}
