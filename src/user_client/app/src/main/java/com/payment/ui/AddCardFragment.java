package com.payment.ui;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.payment.R;
import com.payment.databinding.FragmentAddCardBinding;
import com.payment.model.Account;
import com.payment.model.Card;
import com.payment.model.viewmodel.RegistrationViewModel;
import com.payment.model.viewmodel.TransactionViewModel;

public class AddCardFragment extends Fragment {

    private static int ELEMENT_CARD_NUM = 4;
    private static int MAX_CARD_LENGTH = 16;
    private FragmentAddCardBinding binding;
    private RegistrationViewModel viewModel;
    private Card userCard;
    private Account userAccount;
    private Boolean isFabOpen = false;
    private TransactionViewModel transactionViewModel;
    private TextInputEditText[] inputCardNumArray;
    private TextInputLayout[] inputLayoutArray;
    private String[] cardNumArray;
    private StringBuilder resultCardNum;

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
        initObject();
        setUpCardNumEditText();
        setUpInputLayouts();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] bankArray = getResources().getStringArray(R.array.bank);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, bankArray);
        binding.inputAccountLayout.bankSpinner.setAdapter(adapter);
        binding.inputCardLayout.bankSpinner.setAdapter(adapter);
        binding.inputCardLayout.cardRegistrationButton.setOnClickListener(v -> {
            checkEditTextValid();
            getResultCardNum();
            if (nickNameValid(binding.inputCardLayout.cardNickNameEditText.getText())) {
                if (resultCardNum.toString().length() == MAX_CARD_LENGTH && !resultCardNum.toString().contains("null")) {
                    setUserCard();
                    getFragmentManager().beginTransaction()
                            .remove(AddCardFragment.this)
                            .commit();
                    Toast.makeText(requireActivity(), "카드등록완료", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.inputAccountLayout.accountRegistrationButton.setOnClickListener(v -> {
            if (binding.inputAccountLayout.accountNumEditText.getText() != null) {
                if (nickNameValid(binding.inputAccountLayout.accountNickNameEditText.getText())) {
                    setUserAccount();
                    getFragmentManager().beginTransaction()
                            .remove(AddCardFragment.this)
                            .commit();
                    Toast.makeText(requireActivity(), "계좌등록완료", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.cardViewLayout.addCardButton.setOnClickListener(v -> anim());
        binding.cardViewLayout.addCreditCardButton.setOnClickListener(v -> {
            anim();
            setCardVisibility();
        });
        binding.cardViewLayout.addAccountButton.setOnClickListener(v -> {
            anim();
            setAccountVisibility();
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == ELEMENT_CARD_NUM){
                    if (binding.inputCardLayout.cardNumEdit1.hasFocus()){
                        binding.inputCardLayout.cardNumEdit2.requestFocus();
                    }else if (binding.inputCardLayout.cardNumEdit2.hasFocus()){
                        binding.inputCardLayout.cardNumEdit3.requestFocus();
                    }else if (binding.inputCardLayout.cardNumEdit3.hasFocus()){
                        binding.inputCardLayout.cardNumEdit4.requestFocus();
                    }
                }
            }
        };
        binding.inputCardLayout.cardNumEdit1.addTextChangedListener(textWatcher);
        binding.inputCardLayout.cardNumEdit2.addTextChangedListener(textWatcher);
        binding.inputCardLayout.cardNumEdit3.addTextChangedListener(textWatcher);
    }

    private void checkEditTextValid() {
        for (int i = 0; i < ELEMENT_CARD_NUM; i++) {
            if (cardNumValid(inputCardNumArray[i].getText())) {
                inputLayoutArray[i].setError(null);
                cardNumArray[i] = inputCardNumArray[i].getText().toString();
            } else {
                inputLayoutArray[i].setError(" ");
            }
        }
    }

    private void getResultCardNum() {
        if (cardNumArray.length == ELEMENT_CARD_NUM) {
            for (String s : cardNumArray) {
                if (s != null) {
                    resultCardNum.append(s);
                } else {
                    Toast.makeText(getActivity(), "카드번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean cardNumValid(@Nullable Editable text) {
        return text != null && text.length() == ELEMENT_CARD_NUM;
    }

    private boolean nickNameValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    private void setUserAccount() {
        userAccount.setPaymentMethodType("Account");
        userAccount.setPaymentMethodNum(binding.inputAccountLayout.accountNumEditText.getText().toString());
        userAccount.setUserIdx((Long) transactionViewModel.successCode_Login.getValue().getBody());
        userAccount.setNickName(binding.inputAccountLayout.accountNickNameEditText.getText().toString());
        userAccount.setBankName(binding.inputAccountLayout.bankSpinner.getSelectedItem().toString());
        viewModel.accountLiveData.setValue(userAccount);
        viewModel.registAccount();
    }

    private void setUserCard() {
        userCard.setNickName(binding.inputCardLayout.cardNickNameEditText.getText().toString());
        userCard.setPaymentMethodNum(resultCardNum.toString());
        userCard.setPaymentMethodType("Card");
        userCard.setNickName(binding.inputCardLayout.cardNickNameEditText.getText().toString());
        userCard.setUserIdx((Long) transactionViewModel.successCode_Login.getValue().getBody());
        userCard.setBankName(binding.inputCardLayout.bankSpinner.getSelectedItem().toString());
        viewModel.cardLiveData.setValue(userCard);
        viewModel.registCard();
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

    private void setCardVisibility() {
        if (binding.inputAccountLayout.accountInputLayout.getVisibility() == View.VISIBLE) {
            binding.inputAccountLayout.accountInputLayout.setVisibility(View.INVISIBLE);
        }
        binding.inputCardLayout.cardInputLayout.setVisibility(View.VISIBLE);
    }

    private void setAccountVisibility() {
        if (binding.inputCardLayout.cardInputLayout.getVisibility() == View.VISIBLE) {
            binding.inputCardLayout.cardInputLayout.setVisibility(View.INVISIBLE);
        }
        binding.inputAccountLayout.accountInputLayout.setVisibility(View.VISIBLE);
    }

    private void setUpCardNumEditText() {
        inputCardNumArray[0] = binding.inputCardLayout.cardNumEdit1;
        inputCardNumArray[1] = binding.inputCardLayout.cardNumEdit2;
        inputCardNumArray[2] = binding.inputCardLayout.cardNumEdit3;
        inputCardNumArray[3] = binding.inputCardLayout.cardNumEdit4;
    }

    private void initObject() {
        resultCardNum = new StringBuilder();
        viewModel = ViewModelProviders.of(requireActivity()).get(RegistrationViewModel.class);
        transactionViewModel = ViewModelProviders.of(requireActivity()).get(TransactionViewModel.class);
        binding.setLifecycleOwner(this);
        userCard = new Card();
        userAccount = new Account();
        inputCardNumArray = new TextInputEditText[ELEMENT_CARD_NUM];
        inputLayoutArray = new TextInputLayout[ELEMENT_CARD_NUM];
        cardNumArray = new String[ELEMENT_CARD_NUM];
    }

    private void setUpInputLayouts() {
        inputLayoutArray[0] = binding.inputCardLayout.cardNumTextInput1;
        inputLayoutArray[1] = binding.inputCardLayout.cardNumTextInput2;
        inputLayoutArray[2] = binding.inputCardLayout.cardNumTextInput3;
        inputLayoutArray[3] = binding.inputCardLayout.cardNumTextInput4;
    }
}
