package com.stackroute.com.extractintentservice.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.com.extractintentservice.model.ConceptData;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v2")
public class ExtractController {

    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;

    @PostMapping
    @RequestMapping(value = "/concepts")
    public HashSet<?> extract(@RequestBody String input) throws IOException {

//
//        TypeReference<List<ConceptData>> typeReference = new TypeReference<List<ConceptData>>() {
//        };
//        ObjectMapper mapper = new ObjectMapper();
//        ///InputStream inputStream = new FileInputStream(new File("/extract-intent-service/src/json/concepts.json"));
//        RestTemplate restTemplate = new RestTemplate();
//        String correctedQuery = restTemplate.getForObject("http://localhost:8082/api/v1/concepts/" + , String.class);
//
//
//        List<ConceptData> list = mapper.readValue(inputStream, typeReference);



        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        HashSet hashSet = new HashSet();
//        for (int i = 0; i < coreLabelList.size(); i++) {
//            String pos = coreLabelList.get(i).get(CoreAnnotations.PartOfSpeechAnnotation.class);
//            String output = coreLabelList.get(i).lemma().toLowerCase();
//            if (pos.equals("NN") || pos.equals("NNP")) {
//
//                for (int j = 0; j < list.size(); j++) {
//
//                    if (output.equals(list.get(j).getConcept())) {
//                        hashSet.add(output);
//                    }
//
//                }
//
//            }
//        }
        return hashSet;
    }
}
