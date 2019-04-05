package com.stackroute.graphquery.repository;

import com.stackroute.graphquery.domain.Concept;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptRepository extends Neo4jRepository<Concept, Long> {
}
