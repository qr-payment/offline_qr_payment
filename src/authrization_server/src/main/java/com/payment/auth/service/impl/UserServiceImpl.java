package com.payment.auth.service.impl;

import com.payment.auth.database.UserMapper;
import com.payment.auth.exception.AlreadyExistIdException;
import com.payment.auth.exception.InvalidDataException;
import com.payment.auth.exception.InvalidIdException;
import com.payment.auth.exception.InvalidPasswordException;
import com.payment.auth.model.db.User;
import com.payment.auth.model.request.IdCheck;
import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.request.SignUp;
import com.payment.auth.model.response.SignInRes;
import com.payment.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void signUp(SignUp signUp) {

        // TODO: Generate Salt and PW Encoding
        
        try {
            userMapper.createUser(signUp);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException();
        }

    }

    @Override
    public void idCheck(IdCheck idCheck) {

        int result = userMapper.idCheck(idCheck);

        if (result == 1)
            throw new AlreadyExistIdException();

    }

    @Override
    public SignInRes signIn(SignIn signIn) {

        User user = userMapper.getPassword(signIn.getId());

        if (user == null)
            throw new InvalidIdException();

        // TODO: Encoding PW
        String encodedPassword = signIn.getPassword();

         if(!encodedPassword.equals(user.getPassword()))
             throw new InvalidPasswordException();

        return user.toSignInRes();

    }

}
