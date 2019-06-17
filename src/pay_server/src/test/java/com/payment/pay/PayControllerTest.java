package com.payment.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.pay.model.request.Approve;
import com.payment.pay.model.request.Reserve;
import com.payment.pay.model.request.Temporary;
import com.payment.pay.model.response.ReserveRes;
import com.payment.pay.model.response.TemporaryRes;
import com.payment.pay.service.PayService;
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

    @Autowired
    private PayService payService;

    private Long reserveId;
    private String payId;

    private String alreadyApprovalPayId = "10a8ef80-4fd1-4e65-815d-b7e814175574";

    @Test
    public void reserve() throws Exception {

        // given : valid data
        Reserve reserve = Reserve.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .build();

        String json = asJsonString(reserve);

        // when & then
        mockMvc.perform(post(EndPoint.Reserve.getEndPoint())
                .header("merchant_id", 0)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("성공"))
                .andExpect(jsonPath("$.body.reserveId").isNotEmpty());

    }

    @Test
    public void reserveWithNullMerId() throws Exception {

        // given : valid data
        Reserve reserve = Reserve.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .build();

        String json = asJsonString(reserve);

        // when & then
        mockMvc.perform(post(EndPoint.Reserve.getEndPoint())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(101))
                .andExpect(jsonPath("$.message").value("필요한 Header 값이 존재하지 않습니다."));

    }

    @Test
    public void temporary() throws Exception {

        // set reserve
        Reserve reserve = Reserve.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .build();

        long merchantId = 0L;

        ReserveRes reserveRes = payService.reserve(reserve, merchantId);

        // given : valid data
        Temporary temporary = Temporary.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .transactionPw("6666")
                .methodType("Card")
                .methodNum("1234123412341234")
                .reserveId(reserveRes.getReserveId())
                .build();

        String json = asJsonString(temporary);

        // when & then
        mockMvc.perform(post(EndPoint.Temporary.getEndPoint())
                .header("merchant_id", 0)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("성공"))
                .andExpect(jsonPath("$.body.payId").isNotEmpty());

    }

    @Test
    public void temporaryWithTemporaryReservation() throws Exception {

        // given : valid data
        Temporary temporary = Temporary.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .transactionPw("6666")
                .methodType("Card")
                .methodNum("1234123412341234")
                .reserveId(2L)
                .build();

        String json = asJsonString(temporary);

        // when & then
        mockMvc.perform(post(EndPoint.Temporary.getEndPoint())
                .header("merchant_id", 0)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(201))
                .andExpect(jsonPath("$.message").value("지불 할 수 없는 상태의 결제입니다."));

    }

    @Test
    public void temporaryWithNotMatchInfo() throws Exception {

        // set reserve
        Reserve reserve = Reserve.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .build();

        long merchantId = 0L;

        ReserveRes reserveRes = payService.reserve(reserve, merchantId);

        // given : valid data
        Temporary temporary = Temporary.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(27870)
                .count(4)
                .userIdx(117L)
                .transactionPw("6666")
                .methodType("Card")
                .methodNum("1234123412341234")
                .reserveId(reserveRes.getReserveId())
                .build();

        String json = asJsonString(temporary);

        // when & then
        mockMvc.perform(post(EndPoint.Temporary.getEndPoint())
                .header("merchant_id", 0)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(202))
                .andExpect(jsonPath("$.message").value("결제 예약 정보와 지불 정보가 일치하지 않습니다."));

    }

    @Test
    public void temporaryWithInvalidPayMethod() throws Exception {

        // set reserve
        Reserve reserve = Reserve.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .build();

        long merchantId = 0L;

        ReserveRes reserveRes = payService.reserve(reserve, merchantId);

        // given : valid data
        Temporary temporary = Temporary.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .transactionPw("6666")
                .methodType("Card")
                .methodNum("11111132313141")
                .reserveId(reserveRes.getReserveId())
                .build();

        String json = asJsonString(temporary);

        // when & then
        mockMvc.perform(post(EndPoint.Temporary.getEndPoint())
                .header("merchant_id", 0)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(203))
                .andExpect(jsonPath("$.message").value("지불할 수 없는 결제 수단입니다."));

    }

    @Test
    public void temporaryWithInvalidReserve() throws Exception {

        // given : valid data
        Temporary temporary = Temporary.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(27870)
                .count(4)
                .userIdx(117L)
                .methodType("Card")
                .transactionPw("6666")
                .methodNum("1234123412341234")
                .reserveId(0L)
                .build();

        String json = asJsonString(temporary);

        // when & then
        mockMvc.perform(post(EndPoint.Temporary.getEndPoint())
                .header("merchant_id", 0)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("존재하지 않는 예약 정보입니다."));

    }

    @Test
    public void temporaryWithInvalidTransactionPW() throws Exception {

        // given : valid data
        Temporary temporary = Temporary.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(27870)
                .count(4)
                .userIdx(117L)
                .methodType("Card")
                .transactionPw("6665")
                .methodNum("1234123412341234")
                .reserveId(0L)
                .build();

        String json = asJsonString(temporary);

        // when & then
        mockMvc.perform(post(EndPoint.Temporary.getEndPoint())
                .header("merchant_id", 0)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("존재하지 않는 예약 정보입니다."));

    }

    @Test
    public void approve() throws Exception {

        Approve approve = Approve.builder()
                .payId(this.payId)
                .build();

        String json = asJsonString(approve);

        mockMvc.perform(post(EndPoint.Approve.getEndPoint())
                .header("merchant_id", 0L)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("성공"));

    }

    @Test
    public void approveWithAlreadyApprovalPayId() throws Exception {

        Approve approve = Approve.builder()
                .payId(this.alreadyApprovalPayId)
                .build();

        String json = asJsonString(approve);

        mockMvc.perform(post(EndPoint.Approve.getEndPoint())
                .header("merchant_id", 0L)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(301))
                .andExpect(jsonPath("$.message").value("이미 결제가 완료되었습니다."));

    }

    @Test
    public void approveWithNotMetchantPayId() throws Exception {

        Reserve reserve = Reserve.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .build();

        long merchantId = 0L;

        ReserveRes reserveRes = payService.reserve(reserve, merchantId);
        this.reserveId = reserveRes.getReserveId();

        // set transaction
        Temporary temporary = Temporary.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .transactionPw("6666")
                .methodType("Card")
                .methodNum("1234123412341234")
                .reserveId(this.reserveId)
                .build();

        TemporaryRes temporaryRes = payService.temporary(temporary, merchantId);

        Approve approve = Approve.builder()
                .payId(temporaryRes.getPayId())
                .build();

        String json = asJsonString(approve);

        mockMvc.perform(post(EndPoint.Approve.getEndPoint())
                .header("merchant_id", 1L)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(303))
                .andExpect(jsonPath("$.message").value("상점 정보가 일치하지 않습니다."));

    }

    @Test
    public void approveWithInvalidPayId() throws Exception {

        Approve approve = Approve.builder()
                .payId("invalidpayid")
                .build();

        String json = asJsonString(approve);

        mockMvc.perform(post(EndPoint.Approve.getEndPoint())
                .header("merchant_id", 0L)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(300))
                .andExpect(jsonPath("$.message").value("존재하지 않는 결제 번호 입니다."));

    }

    @Before
    public void init() {

        // set reserve
        Reserve reserve = Reserve.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .build();

        long merchantId = 0L;

        ReserveRes reserveRes = payService.reserve(reserve, merchantId);
        this.reserveId = reserveRes.getReserveId();

        // set transaction
        Temporary temporary = Temporary.builder()
                .productName("드라이빙 로퍼 슈즈 외 3건")
                .amount(278700)
                .count(4)
                .userIdx(117L)
                .transactionPw("6666")
                .methodType("Card")
                .methodNum("1234123412341234")
                .reserveId(this.reserveId)
                .build();

        TemporaryRes temporaryRes = payService.temporary(temporary, merchantId);
        this.payId = temporaryRes.getPayId();

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
