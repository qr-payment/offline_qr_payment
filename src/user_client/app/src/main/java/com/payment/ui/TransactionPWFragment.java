package com.payment.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.payment.R;
import com.payment.databinding.FragmentTransactionPwBinding;
import com.payment.model.viewmodel.TransactionViewModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionPWFragment extends Fragment {
    private static final int PASSWORD_LENGTH = 4;
    private static final int PASSWORD_KIND = 2;
    private FragmentTransactionPwBinding binding;
    private TransactionViewModel viewModel;
    private List<String> checkPasswordList;
    private View view;

    public TransactionPWFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_pw, container, false);
        view = binding.getRoot();
        viewModel = ViewModelProviders.of(requireActivity()).get(TransactionViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setTranViewModel(viewModel);
        binding.setFragment(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkPasswordList = new ArrayList<>();
        binding.resetTransactionBtn.setOnClickListener(v -> viewModel.shuffleList());
    }

    public void pressTransactionPW(boolean buttonState, String number) {
        viewModel.buttonState.setValue(buttonState);
        viewModel.setPassword(number);
        ImageView imageView = view.findViewWithTag(String.valueOf(viewModel.transactionPasswordLength.getValue()));
        imageView.setImageResource(R.drawable.dot1_24dp);
        if (viewModel.transactionPassword != null && viewModel.transactionPasswordLength.getValue() == PASSWORD_LENGTH) {
            if (viewModel.recycleFragment.getValue()){
                //결제완료
                transactionComplete();
            }else{
                //회원가입시
                resetPassword();
            }
        }
    }

    private void transactionComplete(){
        viewModel.user.getValue().setTransactionPw(viewModel.transactionPassword.getValue());
        if (viewModel.transactionPassword.getValue() != null){
            viewModel.transactionRequestMutableLiveData.getValue().setTransactionPw(viewModel.transactionPassword.getValue());
        }
        viewModel.sendTransaction();

        viewModel.transactionResult.observe(this, aBoolean -> {
            if (aBoolean){
                getFragmentManager().beginTransaction()
                        .remove(TransactionPWFragment.this)
                        .commit();
                getFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                viewModel.initTransaction();
            }else{
                viewModel.failTransactionInit();
                viewModel.shuffleList();
                resetTransactionPassword();
                binding.transactionPwState.setText(viewModel.transactionResultMessage.getValue());
            }
        });
    }

    public void deletePressed(boolean buttonState) {
        viewModel.buttonState.setValue(buttonState);
        viewModel.deletePassword();
        ImageView imageView = view.findViewWithTag(String.valueOf(viewModel.transactionPasswordLength.getValue() + 1));
        imageView.setImageResource(R.drawable.dot2_24dp);
    }

    private boolean checkPassword(List<String> passwords) {
        if (passwords.size() == PASSWORD_KIND) {
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

    private void resetPassword() {
        binding.transactionTitle.setText(getString(R.string.transaction_re_check));
        for (int i = 1; i <= PASSWORD_LENGTH; i++) {
            ImageView imageView = view.findViewWithTag(String.valueOf(i));
            imageView.setImageResource(R.drawable.dot2_24dp);
        }
        checkPasswordList.add(viewModel.transactionPassword.getValue());
        if (checkPassword(checkPasswordList)) {
            viewModel.user.getValue().setTransactionPw(viewModel.transactionPassword.getValue());
            getFragmentManager().beginTransaction().remove(TransactionPWFragment.this).commit();
            getFragmentManager().popBackStack();
        }
        viewModel.initViewModels();
    }

    private void resetTransactionPassword(){
        for (int i = 1; i <= PASSWORD_LENGTH; i++) {
            ImageView imageView = view.findViewWithTag(String.valueOf(i));
            imageView.setImageResource(R.drawable.dot2_24dp);
        }
    }
}
