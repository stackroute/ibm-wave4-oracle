package com.stackroute.graphquery.controller;

import com.stackroute.graphquery.domain.Answer;
import com.stackroute.graphquery.domain.Concept;
import com.stackroute.graphquery.domain.Questions;
import com.stackroute.graphquery.service.GraphQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
public class GraphQueryController {

   /* RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject("url", String.class, vars);
*/

    private GraphQueryService graphQueryService;

    @Autowired
    public GraphQueryController(GraphQueryService graphQueryService) {
        this.graphQueryService = graphQueryService;
    }

    /*
            This method displays all set of question and answer
         */
    @GetMapping("/questions")
    public ResponseEntity<Iterable<Questions>> getAll() {
        return new ResponseEntity<>(graphQueryService.getAll(), HttpStatus.OK);
    }

    /*
        This method displays all the concepts
     */
    @GetMapping("/concepts")
    public ResponseEntity<Iterable<Concept>> getConcepts() {
        return new ResponseEntity<>(graphQueryService.concepts(), HttpStatus.OK);
    }

    /*
        This method takes concept and question through rest call and
        returns the set of answers for that particular question and concept
     */

    @GetMapping("/answer/{concept}")
    public ResponseEntity<Iterable<Questions>> getSolution(@PathVariable String concept) {
        return new ResponseEntity<>(graphQueryService.solution(concept), HttpStatus.FOUND);
    }

    /*
       This method takes concept,question and answer through rest call and
       creates question and answer domain and also creates relationship between them,
       and also this entire set is attached to particular concept
    */
    @PostMapping("/relationship/{concept}/{question}/{answer}")
    public ResponseEntity<Iterable<Questions>> createNodesAndRelationships(@PathVariable String concept, @PathVariable String question, @PathVariable String answer) {
        return new ResponseEntity<>(graphQueryService.createNodesAndRelationships(concept, question, answer), HttpStatus.CREATED);
    }
}
