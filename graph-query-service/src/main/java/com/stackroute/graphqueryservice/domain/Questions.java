package com.stackroute.graphqueryservice.domain;


import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;


@NodeEntity(label = "Questions") //This annotation creates the node with label name as Questions
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questions {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String concept;
    private String intent;


    /*
        This annotation creates the relationship as ANSWER_OF
        between the Questions node and Answer node.It also mentions
        the direction of relationship as INCOMING

     */
    @Relationship(type = "ANSWER_OF", direction = "INCOMING")
    private List<Answer> answerList;

}