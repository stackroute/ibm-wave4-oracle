package com.stackroute.com.extractintentservice.controller;



import com.stackroute.com.extractintentservice.model.ConceptData;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1")
public class ExtractController {

    private final Logger logger = LoggerFactory.getLogger(ExtractController.class);
    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;
    ConceptData conceptData;



    @GetMapping(value = "/concepts/{input}")
    public String extract(@PathVariable("input") String input){
        try {

            RestTemplate restTemplate = new RestTemplate();
            String neoConcept = restTemplate.getForObject("http://localhost:8082/api/v1/concepts/", String.class);

            CoreDocument coreDocument = new CoreDocument(input);
            stanfordCoreNLP.annotate(coreDocument);
            List<CoreLabel> coreLabelList = coreDocument.tokens();

            CoreDocument coreDocumentNeo = new CoreDocument(neoConcept);
            stanfordCoreNLP.annotate(coreDocumentNeo);
            List<CoreLabel> list = coreDocumentNeo.tokens();


            for (int i = 0; i < coreLabelList.size(); i++) {
                String pos = coreLabelList.get(i).get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String output = coreLabelList.get(i).lemma().toLowerCase();

                if (pos.equals("NN") || pos.equals("NNP")) {

                    for (int j = 0; j < list.size(); j++) {

                        String concept = list.get(j).lemma().toLowerCase();
                        if (output.equals(concept)) {
                            return output;
                        }
                    }
                }
            }



        }
        catch (Exception e) {
            logger.info(e.getMessage());
        }

        return null;
    }
}
