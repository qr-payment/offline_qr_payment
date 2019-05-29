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

import com.google.android.material.snackbar.Snackbar;
import com.payment.R;
import com.payment.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

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
        FragmentLoginBinding binding = DataBindingUtil.bind(view);
        binding.setLifecycleOwner(requireActivity());

        binding.loginButton.setOnClickListener(v -> {
            if (!isPasswordValid(binding.passwordEditText.getText())) {
                binding.passwordTextInput.setError(getString(R.string.password_length_error));
            } else {
                //TODO:Login Success Logic 추가해야됨
                binding.passwordTextInput.setError(null); //Clear the error

                if (binding.idEditText.getText().toString().equals("test") && binding.passwordEditText.getText().toString().equals("test")){
                    Snackbar.make(view,"Test Scuccess",Snackbar.LENGTH_LONG).show();
                }
            }
        });

        binding.passwordEditText.setOnKeyListener((view1, i, keyEvent) -> {
            if (isPasswordValid(binding.passwordEditText.getText())) {
                //TODO:Error 상태에서 벗어날 때
                binding.passwordTextInput.setError(null); //Clear the error
            }
            return false;
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //password valid check method
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 3;
    }
}
