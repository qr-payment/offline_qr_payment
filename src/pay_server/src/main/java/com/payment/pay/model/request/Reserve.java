package com.payment.pay.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class Reserve {

    @ApiModelProperty(hidden = true)
    private long reserveId;
    @ApiModelProperty(hidden = true)
    private long merchantId;

    @NotNull
    private Integer amount;
    @NotNull
    private Integer count;
    @NotEmpty
    private String productName;
    @NotNull
    private Long userIdx;

    @ApiModelProperty(hidden = true)
    private long reserveAt;

}
