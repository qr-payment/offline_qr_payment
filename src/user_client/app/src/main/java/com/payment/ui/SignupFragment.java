package com.payment.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.payment.R;
import com.payment.databinding.FragmentSignupBinding;
import com.payment.model.TransactionViewModel;

public class SignupFragment extends Fragment {

    private TransactionViewModel viewModel;
    private FragmentSignupBinding binding;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = DataBindingUtil.bind(view);
        binding.setLifecycleOwner(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(TransactionViewModel.class);

        binding.signUpButton.setOnClickListener(v -> {
            if (!isSignUpValid(binding.idEditText.getText())) {
                binding.idTextInput.setError("필수정보입니다.");
            } else {
                binding.idTextInput.setError(null);
            }
            if (!isSignUpValid(binding.passwordEditText.getText())) {
                binding.passwordTextInput.setError("필수정보입니다.");
            } else {
                binding.passwordTextInput.setError(null);
            }
            if (!isSignUpValid(binding.nameEditText.getText())) {
                binding.nameTextInput.setError("필수정보입니다.");
            } else {
                binding.nameTextInput.setError(null);
            }

            viewModel.callSignUpServer();
        });

        binding.settingTransactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.user.getValue().setName(binding.nameEditText.getText().toString());
                viewModel.user.getValue().setId(binding.idEditText.getText().toString());
                viewModel.user.getValue().setPassword(binding.passwordEditText.getText().toString());

                getFragmentManager().beginTransaction()
                        .add(R.id.main_container_view, new TransactionpwFragment())
                        .commit();
            }
        });

        binding.passwordCheckEditText.setOnKeyListener((view1, i, keyEvent) -> {
            if (!binding.passwordCheckEditText.getText().toString().equals(binding.passwordEditText.getText().toString())) {
                binding.passwordCheckTextInput.setError(getString(R.string.error_notsame_password));
            } else {
                binding.passwordCheckTextInput.setError(null);
            }
            return false;
        });
    }

    @Override
    public void onResume() {
        if (viewModel.user.getValue().getTransactionPw() != null) {
            Log.e("user", "" + viewModel.user.getValue().toString());
            binding.settingTransactionBtn.setBackgroundColor(Color.BLUE);
            binding.settingTransactionBtn.setText("설정 완료");
            binding.settingTransactionBtn.setEnabled(false);

            binding.idEditText.setText(viewModel.user.getValue().getId());
            binding.nameEditText.setText(viewModel.user.getValue().getName());
            binding.passwordEditText.setText(viewModel.user.getValue().getPassword());
            binding.passwordCheckEditText.setText(viewModel.user.getValue().getPassword());
        }
        super.onResume();
    }

    private boolean isSignUpValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }
}
