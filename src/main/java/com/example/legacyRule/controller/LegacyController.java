package com.example.legacyRule.controller;

import com.example.legacyRule.request.LegacyReq;
import com.example.legacyRule.service.LegacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LegacyController {

    @Autowired
    private LegacyService legacyService;

    @PostMapping("/")
    public String checkLegacyRule(@RequestBody LegacyReq legacyReq) {
        return legacyService.checkLegacyRuleService(legacyReq);
    }

}
