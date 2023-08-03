package com.example.legacyRule.service;

import com.example.legacyRule.request.LegacyReq;
import com.example.legacyRule.entity.legacy_rules;
import com.example.legacyRule.repository.LegacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
public class LegacyService {

    @Autowired
    private LegacyRepository legacyRepository;

    public String checkLegacyRuleService(LegacyReq legacyReq) {

        String receivedCallingGt = legacyReq.getCallingGt();
        System.out.println("Received calling_gt Value: " + receivedCallingGt);

        String receivedContent = legacyReq.getLegacyReqParameters().getContent();
        System.out.println("Received content Value: " + receivedContent);

        List<legacy_rules> listLegacyDB = legacyRepository.getAllValues();
        String resultString = "";

        for(legacy_rules legacyDBVal: listLegacyDB) {

//          Code block for matching and blocking if legacyRule.calling_gt JSON matches calling_gt from database
            if ((legacyDBVal.getLrCallingGt() != null) && !(legacyDBVal.getLrCallingGt().equals("%")) ) {

                String callingGtDB = legacyDBVal.getLrCallingGt();

                if (callingGtDB.contains("%")) {
                    callingGtDB = callingGtDB.replace("%", ".*");
                }

                if (callingGtDB.contains("_")) {
                    callingGtDB = callingGtDB.replace("_", "[0-9A-Za-z]");
                }

                if (receivedCallingGt.matches(callingGtDB)) {
                    resultString = "MATCH FOUND with CALLING_GT\nlr_id: -> " + legacyDBVal.getLrId() + "\nLegacyRule calling_gt received:\t " + receivedCallingGt + "\nDatabase LegacyRule calling_gt:\t " + legacyDBVal.getLrCallingGt() + "\nReplaced Content Value:\t\t " + callingGtDB;
                    System.out.println(resultString);
                    return resultString;
                }
            }

//          Code block for matching and blocking if legacyRule.content JSON matches content from database
            if( (legacyDBVal.getLrContent() != null) && !(legacyDBVal.getLrContent().equals("%"))
                    && !(legacyDBVal.getLrContent().isEmpty()) ) {

                boolean isMatchFound = true;

                String contentDB = legacyDBVal.getLrContent();

                int percentCount = (int) contentDB.chars().filter(ch -> ch == '%').count();
                System.out.println("ContentDB: " + contentDB + "\npercentCount = " + percentCount);

                contentDB = contentDB.replace("%", ".*");

                if (percentCount == 0) {
                    if (!receivedContent.contains(contentDB)) {
                        isMatchFound = false;
                    }
                }

                else if (percentCount == 1 || percentCount == 2) {
                    if (!receivedContent.matches(contentDB)) {
                        isMatchFound = false;
                    }
                }

                else {
                    receivedContent = legacyReq.getLegacyReqParameters().getContent();

                    String[] splitWords = legacyDBVal.getLrContent().split("%");

                    Arrays.sort(splitWords, Comparator.comparingInt(String::length).reversed());

                    for (String singleWord : splitWords) {
                        if (!receivedContent.contains(singleWord)) {
                            isMatchFound = false;
                            break;
                        }
                        receivedContent = receivedContent.replaceFirst(singleWord, "");
                    }
                }

                if(isMatchFound) {
                    resultString = "MATCH FOUND with CONTENT\nlr_id: -> " + legacyDBVal.getLrId() + "\nLegacyRule content received:\t " + receivedContent + "\nDatabase LegacyRule content:\t " + legacyDBVal.getLrContent() + "\nReplaced Content Value:\t\t " + contentDB;
                    System.out.println(resultString);
                    return resultString;
                }

//                boolean allWordsMatch = true;
//
//                String contentDB = legacyDBVal.getLrContent();
//
//                String[] splitWords = contentDB.split("%");
//
////                System.out.print("lr_id: " + legacyDBVal.getLrId() + ", " + "contentDB: " + contentDB + "length: " + splitWords.length + ", Elements:");
////                for(String str : splitWords) {
////                    System.out.print(str + ", ");
////                }
////                System.out.println();
//
//                if(splitWords.length > 1) {
//
//                    for (String singleWord : splitWords) {
////                        receivedContent.contentEquals()
//                        if (!receivedContent.contains(singleWord)) {
//                            allWordsMatch = false;
//                            break;
//                        }
////                        receivedContent = receivedContent.replace(singleWord, "");
//                    }
//
//                    if (allWordsMatch) {
//                        resultString = "MATCH FOUND with CONTENT JUMBLED\nlr_id: -> " + legacyDBVal.getLrId() + "\nLegacyRule content received:\t " + legacyReq.getLegacyReqParameters().getContent() + "\nDatabase LegacyRule content:\t " + legacyDBVal.getLrContent() + "\nReplaced Content Value:\t\t " + contentDB;
//                        System.out.println(resultString);
//                        return resultString;
//                    }
//                }
//
////                if(splitWords.length > 1) {
////
////                    for (String singleWord : splitWords) {
////
////                        if (!singleWord.isEmpty()) {
////
////                            if (!receivedContent.contains(singleWord)) {
////                                allWordsMatch = false;
////                                break;
////                            }
////                        }
////                    }
////
////                    if (allWordsMatch) {
////                        resultString = "MATCH FOUND with CONTENT JUMBLED\nlr_id: -> " + legacyDBVal.getLrId() + "\nLegacyRule content received:\t " + receivedContent + "\nDatabase LegacyRule content:\t " + legacyDBVal.getLrContent() + "\nReplaced Content Value:\t\t " + contentDB;
////                        System.out.println(resultString);
////                        return resultString;
////                    }
////                }
//
////                if (contentDB.contains("%")){
////                    contentDB = contentDB.replace("%", ".*");
////                }
//
//                receivedContent = legacyReq.getLegacyReqParameters().getContent();
//
//                if (receivedContent.matches(contentDB)) {
//                    resultString = "MATCH FOUND with CONTENT\nlr_id: -> " + legacyDBVal.getLrId() + "\nLegacyRule content received:\t " + receivedContent + "\nDatabase LegacyRule content:\t " + legacyDBVal.getLrContent() + "\nReplaced Content Value:\t\t " + contentDB;
//                    System.out.println(resultString);
//                    return resultString;
//                }
            }
        }

        resultString = "MATCH NOT FOUND!\ncalling_gt received: " + receivedCallingGt + "\ncontent received: " + receivedContent;
        System.out.println(resultString);
        return resultString;
    }
}