package com.payment.merchant.model.request;

import com.payment.merchant.model.connector.request.Temporary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @NotEmpty
    private Integer amount;
    @NotEmpty
    private Integer count;
    @NotEmpty
    private String methodType;
    @NotEmpty
    private String methodNum;
    @NotEmpty
    private String productName;
    @NotEmpty
    private String transactionPw;

    @ApiModelProperty(hidden = true)
    private Long userIdx;

    @ApiModelProperty(hidden = true)
    private Long reserveId;

    public Temporary toTemporary() {

        Temporary temporary = Temporary.builder()
                .amount(this.amount)
                .count(this.count)
                .methodNum(this.methodNum)
                .methodType(this.methodType)
                .productName(this.productName)
                .reserveId(this.reserveId)
                .transactionPw(this.transactionPw)
                .userIdx(this.userIdx)
                .build();

        return temporary;

    }

}
