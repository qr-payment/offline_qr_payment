package com.payment.auth.service.impl;

import com.payment.auth.database.UserMapper;
import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.request.SignUp;
import com.payment.auth.model.response.SignUpRes;
import com.payment.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SignUpRes signUp(SignUp signUp) {

        userMapper.createUser(signUp);

    }

}
