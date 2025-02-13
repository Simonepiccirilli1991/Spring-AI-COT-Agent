package com.tsm.cot.ia.service;

import com.tsm.cot.ia.error.IaIntegrationError;
import com.tsm.cot.ia.ia.IaIntegration;
import com.tsm.cot.ia.model.IaRequest;
import com.tsm.cot.ia.model.IaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class IaChainService {

    private final IaIntegration iaIntegration;


    public IaResponse simpleChain(IaRequest request){
            log.info("SimpleCHain service started with raw request: {}",request);
            // inizializo resp a stringa vuota
            var resp = "";
            // chiamo service chain of trhougs
            if(Boolean.TRUE.equals(request.cot()))
                resp = iaIntegration.cotChat(request.prompt());
            // chiamata semplice
            else
                resp = iaIntegration.simpleChat(request.prompt());
            // checko se resp vuota, in caso eccezzione
            if(ObjectUtils.isEmpty(resp)){
                log.error("Error during Ia integration, response is void");
                throw new IaIntegrationError("Error during Ia integration, response is void");
            }
            log.info("SimpleChain service ended successfully");
            return new IaResponse(resp);
    }
}
