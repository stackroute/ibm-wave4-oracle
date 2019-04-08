package com.stackroute.graphquery.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

@NodeEntity(label = "Answer") //This annotation creates the node with label name as Answer
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue
    private Long graphId;
    private String answer;
    private String concept;


}
