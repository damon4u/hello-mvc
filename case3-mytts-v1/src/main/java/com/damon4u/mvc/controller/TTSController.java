package com.damon4u.mvc.controller;

import com.damon4u.mvc.bean.SubmitRequest;
import com.damon4u.mvc.bean.SubmitResult;
import com.damon4u.mvc.service.TTSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 15:46
 */
@Controller
public class TTSController {
    public static final Logger logger = LoggerFactory.getLogger(TTSController.class);

    @Resource
    private TTSService service;

    @RequestMapping(value = "submit")
    @ResponseBody
    public SubmitResult submit(SubmitRequest submitRequest) {
        SubmitResult submit = service.submit(submitRequest);
        logger.info("result:" + submit);
        return submit;
    }
}
