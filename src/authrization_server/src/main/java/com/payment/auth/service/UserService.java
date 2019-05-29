package com.payment.auth.service;

import com.payment.auth.model.request.IdCheck;
import com.payment.auth.model.request.SignUp;

public interface UserService {

    void signUp(SignUp signIn);
    void idCheck(IdCheck idCheck);

}
