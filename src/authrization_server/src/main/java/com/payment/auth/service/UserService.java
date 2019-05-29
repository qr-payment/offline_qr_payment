package com.payment.auth.service;

import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.request.SignUp;
import com.payment.auth.model.response.SignUpRes;

public interface UserService {

    SignUpRes signUp(SignUp signIn);

}
