package com.payment.auth.model.response;

import com.payment.auth.model.response.internal.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetPayMethods {

    private List<PayMethod> methods;

}
