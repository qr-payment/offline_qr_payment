package com.payment.merchant.model.connector.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Approve {

    private String payId;

}
