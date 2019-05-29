package com.payment.ui;


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

import com.google.android.material.snackbar.Snackbar;
import com.payment.R;
import com.payment.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment {


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
        FragmentSignupBinding binding = DataBindingUtil.bind(view);
        binding.setLifecycleOwner(requireActivity());

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"회원가입 Button Click Test", Snackbar.LENGTH_LONG).show();
                if (!isSignUpValid(binding.idEditText.getText())){
                    binding.idTextInput.setError("필수정보입니다.");
                }else{
                    binding.idTextInput.setError(null);
                }
                if (!isSignUpValid(binding.passwordEditText.getText())){
                    binding.passwordTextInput.setError("필수정보입니다.");
                }else{
                    binding.passwordTextInput.setError(null);
                }
                if (!isSignUpValid(binding.passwordCheckEditText.getText())){
                    binding.passwordCheckTextInput.setError("필수정보입니다");
                }else{
                    binding.passwordCheckTextInput.setError(null);
                }
                if (!isSignUpValid(binding.nameEditText.getText())){
                    binding.nameTextInput.setError("필수정보입니다.");
                }else{
                    binding.nameTextInput.setError(null);
                }

            }
        });
    }


    private boolean isSignUpValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }
}
