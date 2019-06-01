package com.payment.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
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

public class SignUpFragment extends Fragment {

    private TransactionViewModel viewModel;
    private FragmentSignupBinding binding;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);
        View view = binding.getRoot();
        viewModel = ViewModelProviders.of(requireActivity()).get(TransactionViewModel.class);
        binding.setLifecycleOwner(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //회원가입버튼
        binding.signUpButton.setOnClickListener(v -> {
            if (!isSignUpValid(binding.idEditText.getText())) {
                binding.idTextInput.setError(getString(R.string.error_null_info));
            } else {
                binding.idTextInput.setError(null);
            }
            if (!isSignUpValid(binding.passwordEditText.getText())) {
                binding.passwordTextInput.setError(getString(R.string.error_null_info));
            } else {
                binding.passwordTextInput.setError(null);
            }
            if (!isSignUpValid(binding.nameEditText.getText())) {
                binding.nameTextInput.setError(getString(R.string.error_null_info));
            } else {
                binding.nameTextInput.setError(null);
            }

            viewModel.user.getValue().setName(binding.nameEditText.getText().toString());
            viewModel.user.getValue().setId(binding.idEditText.getText().toString());
            viewModel.user.getValue().setPassword(binding.passwordEditText.getText().toString());

            viewModel.callSignUpServer();
        });

        viewModel.successCode_SignUp.observe(requireActivity(), s -> {
            if (s.equals("0")) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_container_view, new LoginFragment())
                        .commit();
            }
        });

        binding.settingTransactionBtn.setOnClickListener(v -> {
            viewModel.user.getValue().setName(binding.nameEditText.getText().toString());
            viewModel.user.getValue().setId(binding.idEditText.getText().toString());
            viewModel.user.getValue().setPassword(binding.passwordEditText.getText().toString());

            getFragmentManager().beginTransaction()
                    .add(R.id.main_container_view, new TransactionPWFragment())
                    .addToBackStack(null)
                    .commit();
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
