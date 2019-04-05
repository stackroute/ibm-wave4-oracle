package com.stackroute.graphquery.service;

import com.stackroute.graphquery.domain.Answer;
import com.stackroute.graphquery.domain.Concept;
import com.stackroute.graphquery.domain.Questions;
import com.stackroute.graphquery.repository.ConceptRepository;
import com.stackroute.graphquery.repository.QueryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class GraphQueryServiceImpl implements GraphQueryService {

    private ConceptRepository conceptRepository;
    private QueryRepository queryRepository;

    @Autowired
    public GraphQueryServiceImpl(ConceptRepository conceptRepository, QueryRepository queryRepository) {
        this.conceptRepository = conceptRepository;
        this.queryRepository = queryRepository;
    }

    /*
                This method takes concept and question through rest call and
                returns the set of answers for that particular question and concept
             */
        @Override
        public Iterable<Questions> solution(String concept) {
            return queryRepository.solution(concept);
        }

        /*
            This method displays all set of question and answer
         */
        @Override
        public Iterable<Questions> getAll() {
            return queryRepository.findAll();
        }

        /*
            This method takes concept,question and answer through rest call and
            creates question and answer domain and also creates relationship between them,
            and also this entire set is attached to particular concept
         */
        @Override
        public Iterable<Questions> createNodesAndRelationships(String concept, String question, String answer) {
            return queryRepository.createNodesAndRelationships(concept, question, answer);
        }

        /*
            This method displays all the concepts
         */
        @Override
        public Iterable<Concept> concepts() {
            return conceptRepository.findAll();
        }
    }

