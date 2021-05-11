package com.andriosi.fabio.authentication.controller;


import com.andriosi.fabio.authentication.entity.Sse;
import com.andriosi.fabio.authentication.session.SseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SseController {

    @GetMapping("/sse")
    @ResponseBody
    public ResponseEntity<List<Sse>> sse(){
        return new ResponseEntity<List<Sse>>(new SseService().findAll(), HttpStatus.OK);
    }
}
