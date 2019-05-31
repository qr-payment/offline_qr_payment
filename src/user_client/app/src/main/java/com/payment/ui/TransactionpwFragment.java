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
import com.payment.databinding.FragmentTransactionPwBinding;
import com.payment.model.TransactionViewModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionpwFragment extends Fragment {

    private FragmentTransactionPwBinding binding;
    private List<String> checkPasswordList;

    public TransactionpwFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_pw, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkPasswordList = new ArrayList<>();
        binding = DataBindingUtil.bind(view);
        TransactionViewModel viewModel = ViewModelProviders.of(requireActivity()).get(TransactionViewModel.class);
        binding.setLifecycleOwner(requireActivity());
        binding.setTranViewModel(viewModel);

        binding.resetTransactionBtn.setOnClickListener(v -> viewModel.shuffleList());

        binding.transactionDeleteBtn.setOnClickListener(v -> {
            viewModel.buttonState.setValue(false);
            viewModel.deletePassword();
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        binding.transactionNumP0.setOnClickListener(v -> {
            viewModel.buttonState.setValue(true);
            viewModel.setPassword(binding.transactionNumP0.getText().toString());
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        binding.transactionNumP1.setOnClickListener(v -> {
            viewModel.buttonState.setValue(true);
            viewModel.setPassword(binding.transactionNumP1.getText().toString());
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        binding.transactionNumP2.setOnClickListener(v -> {
            viewModel.buttonState.setValue(true);
            viewModel.setPassword(binding.transactionNumP2.getText().toString());
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        binding.transactionNumP3.setOnClickListener(v -> {
            viewModel.buttonState.setValue(true);
            viewModel.setPassword(binding.transactionNumP3.getText().toString());
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        binding.transactionNumP4.setOnClickListener(v -> {
            viewModel.buttonState.setValue(true);
            viewModel.setPassword(binding.transactionNumP4.getText().toString());
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        binding.transactionNumP5.setOnClickListener(v -> {
            viewModel.buttonState.setValue(true);
            viewModel.setPassword(binding.transactionNumP5.getText().toString());
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        binding.transactionNumP6.setOnClickListener(v -> {
            viewModel.buttonState.setValue(true);
            viewModel.setPassword(binding.transactionNumP6.getText().toString());
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        binding.transactionNumP7.setOnClickListener(v -> {
            viewModel.buttonState.setValue(true);
            viewModel.setPassword(binding.transactionNumP7.getText().toString());
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        binding.transactionNumP8.setOnClickListener(v -> {
            viewModel.buttonState.setValue(true);
            viewModel.setPassword(binding.transactionNumP8.getText().toString());
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        binding.transactionNumP9.setOnClickListener(v -> {
            viewModel.buttonState.setValue(true);
            viewModel.setPassword(binding.transactionNumP9.getText().toString());
            setImage(viewModel.transactionPasswordLength.getValue(), viewModel.buttonState.getValue());
        });

        viewModel.transactionPasswordLength.observe(requireActivity(), integer -> {
            if (integer == 4) {
                binding.transactionTitle.setText(getString(R.string.transaction_re_check));
                binding.transactionImageView1.setImageResource(R.drawable.dot2_24dp);
                binding.transactionImageView2.setImageResource(R.drawable.dot2_24dp);
                binding.transactionImageView3.setImageResource(R.drawable.dot2_24dp);
                binding.transactionImageView4.setImageResource(R.drawable.dot2_24dp);
                checkPasswordList.add(viewModel.transactionPassword.getValue());
                if (checkPassword(checkPasswordList)) {
                    viewModel.user.getValue().setTransactionPw(viewModel.transactionPassword.getValue());
                    getFragmentManager().beginTransaction().add(R.id.main_container_view,new SignupFragment()).commit();
                }
                viewModel.initViewModels();
            }
        });
    }

    private boolean checkPassword(List<String> passwords) {
        if (passwords.size() == 2) {
            if (passwords.get(0).equals(passwords.get(1))) {
                return true;
            } else {
                passwords.remove(1);
                binding.transactionPwState.setText("비밀번호가 틀렸습니다.");
                return false;
            }
        }
        return false;
    }

    private void setImage(int length, boolean buttonState) {
        if (buttonState) {
            switch (length) {
                case 1:
                    binding.transactionImageView1.setImageResource(R.drawable.dot1_24dp);
                    break;
                case 2:
                    binding.transactionImageView2.setImageResource(R.drawable.dot1_24dp);
                    break;
                case 3:
                    binding.transactionImageView3.setImageResource(R.drawable.dot1_24dp);
                    break;
                case 4:
                    binding.transactionImageView4.setImageResource(R.drawable.dot1_24dp);
                    break;
            }
        } else {
            switch (length) {
                case 0:
                    binding.transactionImageView1.setImageResource(R.drawable.dot2_24dp);
                    break;
                case 1:
                    binding.transactionImageView2.setImageResource(R.drawable.dot2_24dp);
                    break;
                case 2:
                    binding.transactionImageView3.setImageResource(R.drawable.dot2_24dp);
                    break;
                case 3:
                    binding.transactionImageView4.setImageResource(R.drawable.dot2_24dp);
                    break;
            }

        }
    }
}
