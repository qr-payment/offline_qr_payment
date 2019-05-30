package com.payment.auth.service;

import com.payment.auth.model.request.IdCheck;
import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.request.SignUp;
import com.payment.auth.model.response.SignInRes;

public interface UserService {

    void signUp(SignUp signIn);
    void idCheck(IdCheck idCheck);
    SignInRes signIn(SignIn signIn);

}
