package com.payment.auth.database;

import com.payment.auth.model.db.User;
import com.payment.auth.model.request.IdCheck;
import com.payment.auth.model.request.SignUp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    void createUser(SignUp signUp);
    int idCheck(IdCheck idCheck);
    User getPassword(String id);

}
