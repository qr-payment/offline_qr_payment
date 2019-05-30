package com.payment.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.auth.database.UserMapper;
import com.payment.auth.model.request.IdCheck;
import com.payment.auth.model.request.SignUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void signUpTest() throws Exception {

        // given : valid data
        SignUp signUp = SignUp.builder()
                .id("test")
                .password("test")
                .transactionPw("1234")
                .salt("salt")
                .name("테스터")
                .build();

        String json = asJsonString(signUp);

        // when and then
        mockMvc.perform(post(EndPoint.SignUp.getEndPoint())
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("성공"));

    }

    @Test
    public void signUpWithInvalidData() throws Exception {

        // given : invalid data
        SignUp signUp = SignUp.builder()
                .id("test")
                .password("test")
                .transactionPw("1234")
                .build();

        String json = asJsonString(signUp);

        // when and then
        mockMvc.perform(post(EndPoint.SignUp.getEndPoint())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(100))
                .andExpect(jsonPath("$.message").value("데이터 형식이 올바르지 않습니다."));

    }

    @Test
    public void idCheckWithNotExistId() throws Exception {

        // given : not exist id (valid data)
        IdCheck idCheck = IdCheck.builder()
                .targetId("test")
                .build();

        String json = asJsonString(idCheck);

        // when and then
        mockMvc.perform(post(EndPoint.IdCheck.getEndPoint())
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("성공"));


    }


    @Test
    public void idCheckWithExistId() throws Exception {

        // given : already exist id
        IdCheck idCheck = IdCheck.builder()
                .targetId("existId")
                .build();

        String json = asJsonString(idCheck);

        // when and then
        mockMvc.perform(post(EndPoint.IdCheck.getEndPoint())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("이미 존재하는 사용자 ID 입니다."));

    }

    @Test
    public void idCheckWithInvalidData() throws Exception {

        // given : invalid data
        IdCheck idCheck = new IdCheck();

        String json = asJsonString(idCheck);

        // when and then
        mockMvc.perform(post(EndPoint.IdCheck.getEndPoint())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(100))
                .andExpect(jsonPath("$.message").value("데이터 형식이 올바르지 않습니다."));

    }

    @Before
    public void init() {

        SignUp signUp = SignUp.builder()
                .id("existId")
                .password("test")
                .transactionPw("1234")
                .salt("salt")
                .name("테스터")
                .build();


        userMapper.createUser(signUp);

    }

    public String asJsonString(Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}