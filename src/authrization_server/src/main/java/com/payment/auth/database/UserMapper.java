package com.payment.auth.database;

import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.request.SignUp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void createUser(SignUp signUp);

}
