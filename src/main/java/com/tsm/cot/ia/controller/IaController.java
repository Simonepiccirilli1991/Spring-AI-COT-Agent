package com.tsm.cot.ia.controller;

import com.tsm.cot.ia.model.IaRequest;
import com.tsm.cot.ia.model.IaResponse;
import com.tsm.cot.ia.service.IaChainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ia")
@RequiredArgsConstructor
public class IaController {


    private final IaChainService iaChainService;


    @PostMapping("/simplechain")
    public ResponseEntity<IaResponse> simpleChain(@RequestBody IaRequest request){
        return ResponseEntity.ok(iaChainService.simpleChain(request));
    }
}
