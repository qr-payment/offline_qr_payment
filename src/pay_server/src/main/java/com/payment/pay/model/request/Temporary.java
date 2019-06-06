package com.payment.pay.model.request;

import com.payment.pay.model.dao.ReserveDAO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class Temporary {

    @ApiModelProperty(hidden = true)
    private Long transactionIdx;
    @ApiModelProperty(hidden = true)
    private Long merchantId;

    @NotNull
    private Long reserveId;
    @NotNull
    private Long userIdx;
    @NotNull
    private Integer amount;
    @NotNull
    private Integer count;
    @NotEmpty
    private String productName;
    @NotEmpty
    private String methodType;
    @NotEmpty
    private String methodNum;
    @NotEmpty
    private String transactionPw;

    @ApiModelProperty(hidden = true)
    private String payId;

    public boolean isSameOrder(ReserveDAO reserveDAO) {

        return this.amount.equals(reserveDAO.getAmount()) &&
                this.count.equals(reserveDAO.getCount()) &&
                this.merchantId.equals(reserveDAO.getMerchantId()) &&
                this.productName.equals(reserveDAO.getProductName()) &&
                this.reserveId.equals(reserveDAO.getReserveId()) &&
                this.userIdx.equals(reserveDAO.getUserIdx());

    }

}
