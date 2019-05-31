package com.payment.ui;

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

import com.google.android.material.snackbar.Snackbar;
import com.payment.R;
import com.payment.databinding.FragmentLoginBinding;
import com.payment.model.TransactionViewModel;
import com.payment.model.User;

public class LoginFragment extends Fragment {

    private TransactionViewModel viewModel;
    public LoginFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user = new User();
        viewModel = ViewModelProviders.of(requireActivity()).get(TransactionViewModel.class);
        FragmentLoginBinding binding = DataBindingUtil.bind(view);
        binding.setLifecycleOwner(requireActivity());

        viewModel.successCode.observe(requireActivity(), s -> {
            if (s.equals("0")) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_container_view,new MainFragment())
                        .commit();
            }else{
                Snackbar.make(view,"로그인실패",Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.loginButton.setOnClickListener(v -> {
            if (binding.passwordEditText.getText().toString().length() == 0 || binding.idEditText.getText().toString().length() == 0){
                binding.passwordTextInput.setError(getString(R.string.login_null_error));
            }else{
                binding.passwordTextInput.setError(null);
                user.setId(binding.idEditText.getText().toString());
                user.setPassword(binding.passwordEditText.getText().toString());
                viewModel.callSignInServer(user);
            }
        });

        binding.passwordEditText.setOnKeyListener((view1, i, keyEvent) -> {
            if (isPasswordValid(binding.passwordEditText.getText())) {
                binding.passwordTextInput.setError(null);
            }
            return false;
        });

        //회원가입 버튼
        binding.signUpButton.setOnClickListener(v -> getFragmentManager().beginTransaction()
                .replace(R.id.main_container_view, new SignupFragment())
                .addToBackStack(null)
                .commit());
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //password valid check method
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }
}
