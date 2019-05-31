package com.payment.auth.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdCheck {

    @NotEmpty
    private String targetId;

}
