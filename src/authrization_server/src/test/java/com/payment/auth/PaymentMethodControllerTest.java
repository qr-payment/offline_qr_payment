package com.payment.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.auth.database.PaymentMethodMapper;
import com.payment.auth.database.UserMapper;
import com.payment.auth.model.request.IdCheck;
import com.payment.auth.model.request.RegistPayMethod;
import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.request.SignUp;
import com.payment.auth.service.PaymentMethodService;
import com.payment.auth.service.UserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PaymentMethodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private UserService userService;

    public long userIdx;

    @Test
    public void registAccountWithAccount() throws Exception {

        // given : valid account data
        RegistPayMethod registPayMethod = RegistPayMethod.builder()
                .paymentMethodType("Account")
                .paymentMethodNum("1235-1235")
                .userIdx(userIdx)
                .build();

        String json = asJsonString(registPayMethod);

        // when and then
        mockMvc.perform(post(EndPoint.RegistPayMethod.getEndPoint())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("성공"));

    }

    @Test
    public void registAccountWithCard() throws Exception {

        // given : valid card data
        RegistPayMethod registPayMethod = RegistPayMethod.builder()
                .paymentMethodType("Card")
                .paymentMethodNum("1235-1235")
                .userIdx(userIdx)
                .build();

        String json = asJsonString(registPayMethod);

        // when and then
        mockMvc.perform(post(EndPoint.RegistPayMethod.getEndPoint())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("성공"));

    }

    @Test
    public void registAccountWithExistAccount() throws Exception {

        // given : already exist account data
        RegistPayMethod registPayMethod = RegistPayMethod.builder()
                .paymentMethodType("Account")
                .paymentMethodNum("1234-1234")
                .userIdx(userIdx)
                .build();

        String json = asJsonString(registPayMethod);

        // when and then
        mockMvc.perform(post(EndPoint.RegistPayMethod.getEndPoint())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(300))
                .andExpect(jsonPath("$.message").value("이미 등록되어 있는 결제 수단입니다."));

    }

    @Test
    public void registAccountWithExistCard() throws Exception {

        // given : already exist card data
        RegistPayMethod registPayMethod = RegistPayMethod.builder()
                .paymentMethodType("Card")
                .paymentMethodNum("1234-1234")
                .userIdx(userIdx)
                .build();

        String json = asJsonString(registPayMethod);

        // when and then
        mockMvc.perform(post(EndPoint.RegistPayMethod.getEndPoint())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(300))
                .andExpect(jsonPath("$.message").value("이미 등록되어 있는 결제 수단입니다."));

    }

    @Before
    public void init() throws Exception {

        SignUp signUp = SignUp.builder()
                .id("existId")
                .password("existPw")
                .transactionPw("1234")
                .salt("salt")
                .name("유저")
                .build();

        userMapper.createUser(signUp);

        // given : valid data
        SignIn signIn = SignIn.builder()
                .id("existId")
                .password("existPw")
                .build();

        userIdx = userService.signIn(signIn).getUserIdx();

        RegistPayMethod registPayMethod = RegistPayMethod.builder()
                .paymentMethodType("Account")
                .paymentMethodNum("1234-1234")
                .userIdx(userIdx)
                .build();

        paymentMethodService.registPayMethod(registPayMethod);

        registPayMethod.setPaymentMethodType("Card");

        paymentMethodService.registPayMethod(registPayMethod);

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