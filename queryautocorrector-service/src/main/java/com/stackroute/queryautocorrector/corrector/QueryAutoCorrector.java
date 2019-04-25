package com.stackroute.queryautocorrector.corrector;

/* Created on : 28/03/2019 by Subhajit Pal (@rahzex) */

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueryAutoCorrector {

    /* predefined technologies list to avoid wrong auto correction */
    private static List<String> technologiesList ;

    static {
        technologiesList = new ArrayList<>();
        technologiesList.add("intellij");technologiesList.add("mongo");
        technologiesList.add("npm");technologiesList.add("jdk");
        technologiesList.add("ubuntu");technologiesList.add("maven");
    }

    private QueryAutoCorrector(){}

    /* This method returns corrected queryAnswer after removing any spelling errors */

    public static String correctQuery(String query) throws IOException {

        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());

        /* Checking for spelling errors */
        List<RuleMatch> matches = langTool.check(query);
        String correctedQuery = query;

        for (RuleMatch match : matches) {
            /* Getting incorrect word from the queryAnswer */
            String incorrectWord = query.substring(match.getFromPos(),match.getToPos());
            String correctedWord = "";


            /* iterating through predefined technologies list to avoid wrong auto correction */
            for (String s : technologiesList) {
                if (s.equals(incorrectWord.toLowerCase())){
                    correctedWord = incorrectWord;
                    break;
                }
            }

            if(!match.getSuggestedReplacements().isEmpty()){
                if(correctedWord.equals("")){
                    /* Getting the first correct word suggestion from list */
                    correctedWord = match.getSuggestedReplacements().get(0);
                }

                /* Auto correcting the queryAnswer word by word */
                if (match.getFromPos() == 0)
                    correctedQuery = correctedQuery.replaceFirst(incorrectWord,correctedWord);
                else if (match.getToPos() == query.length())
                    correctedQuery = correctedQuery.replaceFirst(" "+incorrectWord," "+correctedWord);
                else
                    correctedQuery = correctedQuery.replaceFirst(" "+incorrectWord+" "," "+correctedWord+" ");
            }

        }

        return correctedQuery;
    }
}
