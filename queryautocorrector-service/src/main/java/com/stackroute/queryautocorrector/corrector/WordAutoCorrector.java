package com.stackroute.queryautocorrector.corrector;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordAutoCorrector {
    private Map<String,Integer> dict = new HashMap<>();

    public WordAutoCorrector(Path dictionaryFile) throws Exception{
        Stream.of(new String(Files.readAllBytes( dictionaryFile )).toLowerCase().replaceAll("[^a-z ]","").split(" ")).forEach( (word) ->{
            dict.compute( word, (k,v) -> v == null ? 1 : v + 1  );
        });
    }

    Stream<String> edits1(final String word){
        Stream<String> deletes    = IntStream.range(0, word.length())  .mapToObj((i) -> word.substring(0, i) + word.substring(i + 1));
        Stream<String> replaces   = IntStream.range(0, word.length())  .mapToObj((i)->i).flatMap( (i) -> "abcdefghijklmnopqrstuvwxyz".chars().mapToObj( (c) ->  word.substring(0,i) + (char)c + word.substring(i+1) )  );
        Stream<String> inserts    = IntStream.range(0, word.length()+1).mapToObj((i)->i).flatMap( (i) -> "abcdefghijklmnopqrstuvwxyz".chars().mapToObj( (c) ->  word.substring(0,i) + (char)c + word.substring(i) )  );
        Stream<String> transposes = IntStream.range(0, word.length()-1).mapToObj((i)-> word.substring(0,i) + word.substring(i+1,i+2) + word.charAt(i) + word.substring(i+2) );
        return Stream.of( deletes,replaces,inserts,transposes ).flatMap((x)->x);
    }

    Stream<String> known(Stream<String> words){
        return words.filter( (word) -> dict.containsKey(word) );
    }

    String correct(String word){
        Optional<String> e1 = known(edits1(word)).max( (a, b) -> dict.get(a) - dict.get(b) );
        if(e1.isPresent())return dict.containsKey(word) ? word : e1.get();
        Optional<String> e2 = known(edits1(word).map( (w2)->edits1(w2) ).flatMap((x)->x)).max( (a,b) -> dict.get(a) - dict.get(b) );
        return (e2.isPresent() ? e2.get() : word);
    }
}
