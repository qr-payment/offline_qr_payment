package com.payment.merchant.controller;

import com.payment.merchant.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @Value("${app.payFront}")
    private String payFront;

    @Value("${app.returnUrl}")
    private String returnUrl;

    @RequestMapping(value = "/qrscan", method = RequestMethod.GET)
    public String redirectPage(@RequestParam(value = "orderName") String encodedOrderName, @RequestParam(value = "amount")int amount, @RequestParam(value = "count")int count) {

        long reserveId = orderService.reserve(encodedOrderName, amount, count);

        String newPayFront = payFront + "/login/" + reserveId;

        return "redirect:" + newPayFront;

    }

    @RequestMapping(value = "/pay/complete", method = RequestMethod.GET)
    public String approve(@RequestParam(value = "redisKey") String redisKey, @RequestParam(value = "payId") String payId) {

        orderService.approval(redisKey, payId);

        return "redirect:" + returnUrl + "/txn/complete";

    }

}
