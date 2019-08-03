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
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.payment.R;
import com.payment.databinding.FragmentLoginBinding;
import com.payment.model.User;
import com.payment.model.viewmodel.TransactionViewModel;

public class LoginFragment extends Fragment {

    private TransactionViewModel viewModel;
    private FragmentLoginBinding binding;

    public LoginFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(requireActivity()).get(TransactionViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        View view = binding.getRoot();
        binding.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user = new User();

        viewModel.successCode_Login.observe(requireActivity(), serverResponse -> {
            if (serverResponse != null) {
                if (serverResponse.getCode() == 0) {
                    Log.i("User Index",""+serverResponse.getBody());
                    if (getFragmentManager() != null) {
                        getFragmentManager().beginTransaction()
                                .remove(this)
                                .commit();
                    }
                } else {
                    Log.e("error message",""+serverResponse.getMessage());
                    Snackbar.make(requireView(), serverResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        binding.loginButton.setOnClickListener(v -> {
            if (binding.passwordEditText.getText().toString().length() == 0 || binding.idEditText.getText().toString().length() == 0) {
                binding.passwordTextInput.setError(getString(R.string.login_null_error));
            } else {
                //서버 없이 테스트
                getFragmentManager().beginTransaction()
                        .remove(this)
                        .commit();
//                binding.passwordTextInput.setError(null);
//                user.setId(binding.idEditText.getText().toString());
//                user.setPassword(binding.passwordEditText.getText().toString());
//                viewModel.callSignInServer(user);
            }
        });

        binding.passwordEditText.setOnKeyListener((view1, i, keyEvent) -> {
            if (isPasswordValid(binding.passwordEditText.getText())) {
                binding.passwordTextInput.setError(null);
            }
            return false;
        });

        //회원가입 버튼
        binding.signUpButton.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_container_view, new SignUpFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    //password valid check method
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }
}
