package com.payment.ui;


import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.payment.R;
import com.payment.databinding.FragmentAddCardBinding;
import com.payment.model.Account;
import com.payment.model.Card;
import com.payment.model.viewmodel.RegistrationViewModel;

public class AddCardFragment extends Fragment {

    private FragmentAddCardBinding binding;
    private RegistrationViewModel viewModel;
    private Card userCard;
    private Account userAccount;
    private String resultCardNum;
    private Boolean isFabOpen = false;

    public AddCardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_card, container, false);
        View view = binding.getRoot();
        viewModel = ViewModelProviders.of(requireActivity()).get(RegistrationViewModel.class);
        binding.setLifecycleOwner(this);
        userCard = new Card();
        userAccount = new Account();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] bankArray = getResources().getStringArray(R.array.bank);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, bankArray);
        binding.inputAccountLayout.bankSpinner.setAdapter(adapter);

        binding.inputCardLayout.cardRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEditTextState(binding.inputCardLayout.cardNumEdit1.getText(), 1);
                checkEditTextState(binding.inputCardLayout.cardNumEdit2.getText(), 2);
                checkEditTextState(binding.inputCardLayout.cardNumEdit3.getText(), 3);
                checkEditTextState(binding.inputCardLayout.cardNumEdit4.getText(), 4);

                if (binding.inputCardLayout.cardNickNameEditText.getText() != null && binding.inputCardLayout.cardNickNameEditText.getText().toString().length() > 0) {
                    userCard.setCardNickName(binding.inputCardLayout.cardNickNameEditText.getText().toString());
                    resultCardNum = binding.inputCardLayout.cardNumEdit1.getText().toString() + binding.inputCardLayout.cardNumEdit2.getText().toString() + binding.inputCardLayout.cardNumEdit3.getText().toString()
                            + binding.inputCardLayout.cardNumEdit4.getText().toString();
                    if (resultCardNum.length() == 16) {
                        userCard.setCardNum(resultCardNum);
                        getFragmentManager().beginTransaction()
                                .remove(AddCardFragment.this)
                                .commit();
                        Snackbar.make(view, "설정완료" + userCard.toString(), Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.inputAccountLayout.accountRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.inputAccountLayout.accountNumEditText.getText() != null) {
                    userAccount.setBankAccount(binding.inputAccountLayout.accountNumEditText.getText().toString());
                    userAccount.setBankName(binding.inputAccountLayout.bankSpinner.getSelectedItem().toString());
                    viewModel.accountLiveData.setValue(userAccount);

                    if (binding.inputAccountLayout.accountNickNameEditText.getText() != null && binding.inputAccountLayout.accountNickNameEditText.getText().toString().length() > 0){
                        Log.e("Account Data", "" + viewModel.accountLiveData.getValue().toString());
                        getFragmentManager().beginTransaction()
                                .remove(AddCardFragment.this)
                                .commit();
                    }
                }
            }
        });

        binding.cardViewLayout.addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
            }
        });
        binding.cardViewLayout.addCreditCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
                if (binding.inputAccountLayout.accountInputLayout.getVisibility() == View.VISIBLE) {
                    binding.inputAccountLayout.accountInputLayout.setVisibility(View.INVISIBLE);
                }
                binding.inputCardLayout.cardInputLayout.setVisibility(View.VISIBLE);
            }
        });
        binding.cardViewLayout.addAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
                if (binding.inputCardLayout.cardInputLayout.getVisibility() == View.VISIBLE) {
                    binding.inputCardLayout.cardInputLayout.setVisibility(View.INVISIBLE);
                }
                binding.inputAccountLayout.accountInputLayout.setVisibility(View.VISIBLE);
            }
        });

        binding.inputCardLayout.cardNumEdit1.setOnKeyListener((view1, i, keyEvent) -> {
            if (!cardNumValid(binding.inputCardLayout.cardNumEdit1.getText())) {
                binding.inputCardLayout.cardNumTextInput1.setError("4자리 입력해주세요.");
            } else {
                binding.inputCardLayout.cardNumTextInput1.setError(null);
            }
            return false;
        });
        binding.inputCardLayout.cardNumEdit2.setOnKeyListener((view1, i, keyEvent) -> {
            if (!cardNumValid(binding.inputCardLayout.cardNumEdit2.getText())) {
                binding.inputCardLayout.cardNumTextInput2.setError("4자리 입력해주세요.");
            } else {
                binding.inputCardLayout.cardNumTextInput2.setError(null);
            }
            return false;
        });
        binding.inputCardLayout.cardNumEdit3.setOnKeyListener((view1, i, keyEvent) -> {
            if (!cardNumValid(binding.inputCardLayout.cardNumEdit3.getText())) {
                binding.inputCardLayout.cardNumTextInput3.setError("4자리 입력해주세요.");
            } else {
                binding.inputCardLayout.cardNumTextInput3.setError(null);
            }
            return false;
        });
        binding.inputCardLayout.cardNumEdit4.setOnKeyListener((view1, i, keyEvent) -> {
            if (!cardNumValid(binding.inputCardLayout.cardNumEdit4.getText())) {
                binding.inputCardLayout.cardNumTextInput4.setError("4자리 입력해주세요.");
            } else {
                binding.inputCardLayout.cardNumTextInput4.setError(null);
            }
            return false;
        });
    }

    private void checkEditTextState(Editable text, int id) {
        if (cardNumValid(text)) {
            if (id == 1) {
                binding.inputCardLayout.cardNumTextInput1.setError(null);
            } else if (id == 2) {
                binding.inputCardLayout.cardNumTextInput2.setError(null);
            } else if (id == 3) {
                binding.inputCardLayout.cardNumTextInput3.setError(null);
            } else {
                binding.inputCardLayout.cardNumTextInput4.setError(null);
            }
        } else if (id == 1) {
            binding.inputCardLayout.cardNumTextInput1.setError(" ");
        } else if (id == 2) {
            binding.inputCardLayout.cardNumTextInput2.setError(" ");
        } else if (id == 3) {
            binding.inputCardLayout.cardNumTextInput3.setError(" ");
        } else {
            binding.inputCardLayout.cardNumTextInput4.setError(" ");
        }
    }

    //password valid check method
    private boolean cardNumValid(@Nullable Editable text) {
        return text != null && text.length() == 4;
    }

    private boolean lengthValid(@Nullable Editable text) {
        return text == null && text.length() != 4;
    }

    private boolean nickNameValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    private void anim() {
        if (isFabOpen) {
            binding.cardViewLayout.addCreditCardButton.hide();
            binding.cardViewLayout.addAccountButton.hide();
            isFabOpen = false;
        } else {
            binding.cardViewLayout.addCreditCardButton.show();
            binding.cardViewLayout.addAccountButton.show();
            isFabOpen = true;
        }
    }
}
