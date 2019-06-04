package com.payment.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.pay.model.request.Reserve;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.websocket.Endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void reserve() throws Exception {

        // given : valid data
        Reserve reserve = Reserve.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .build();

        String json = asJsonString(reserve);

        // when & then
        mockMvc.perform(post(EndPoint.Reserve.getEndPoint())
                .header("merchant_id", 0)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.message").value("성공"));

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
