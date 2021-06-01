package edu.epam.handling.service;

import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.exception.HandlerException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TextService {
    TextComponent sortParagraphsByAmountOfSentences(TextComponent component) throws HandlerException;
    List<TextComponent> getSentencesWithLongestWord(TextComponent component) throws HandlerException;
    void deleteSentencesWithAmountOfWordsLessThan(TextComponent component, int amount) throws HandlerException;
    HashMap<String, Integer> amountOfSimilarWords(TextComponent component) throws HandlerException;
    int amountOfVowels(TextComponent component) throws HandlerException;
    int amountOfConsonants(TextComponent component) throws HandlerException;
}
