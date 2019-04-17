package com.stackroute.queryautocorrector.controller;

import com.stackroute.queryautocorrector.corrector.QueryAutoCorrector;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class QueryCorrectorController {

    @GetMapping("/getCorrectedQuery/{queryAnswer}")
    public String getCorrectedQuery(@PathVariable("queryAnswer") String query) throws IOException {
        return QueryAutoCorrector.correctQuery(query);
    }
}
