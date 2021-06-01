package edu.epam.handling.service.impl;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.HandlerException;
import edu.epam.handling.service.TextService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final String VOWELS = "eyuioa";
    private static final String CONSONANTS = "qwrtpsdfghjklzxcvbnm";
    private static Logger logger = LogManager.getLogger();
    @Override
    public TextComponent sortParagraphsByAmountOfSentences(TextComponent component) throws HandlerException {
        if (component.getType() == ComponentType.TEXT){
            List<TextComponent> paragraphs = component.getChildren();
            TextComponent newText = new TextComposite(ComponentType.TEXT);
            newText.addAll(paragraphs.stream().sorted(Comparator.comparingInt(TextComponent::getComponentsAmount)).collect(Collectors.toList()));
            logger.log(Level.INFO, "Paragraphs were sorted by amount of sentences");
            return newText;
        }else{
            throw new HandlerException("Incorrect text component");
        }
    }

    @Override
    public List<TextComponent> getSentencesWithLongestWord(TextComponent component) throws HandlerException {
        if (component.getType() == ComponentType.TEXT){
            int maxLength = 0;
            List<TextComponent> result = new ArrayList<>();
            List<TextComponent> paragraphs = component.getChildren();
            for (var paragraph : paragraphs){
                List<TextComponent> sentences = paragraph.getChildren();
                for (var sentence : sentences){
                    List<TextComponent> words = getWordsFromSentence(sentence);
                    for (var word : words){
                        String w = word.toString();
                        if (w.length() > maxLength){
                            maxLength = w.length();
                            result.clear();
                            result.add(sentence);
                        }else if (w.length() == maxLength){
                            if (!result.contains(sentence)){
                                result.add(sentence);
                            }
                        }
                    }
                }
            }
            logger.log(Level.INFO, "Were returned all sentences with the longest word");
            return result;
        }else{
            throw new HandlerException("Incorrect text component");
        }
    }

    @Override
    public void deleteSentencesWithAmountOfWordsLessThan(TextComponent component, int amount) throws HandlerException {
        if (component.getType() == ComponentType.TEXT){
            List<TextComponent> paragraphs = component.getChildren();
            for (var paragraph : paragraphs){
                List<TextComponent> sentences = paragraph.getChildren();
                paragraph.clear();
                for (var sentence : sentences){
                    List<TextComponent> words = getWordsFromSentence(sentence);
                    if(words.size() >= amount){
                        paragraph.add(sentence);
                    }
                }
            }
            logger.log(Level.INFO, "Were deleted all sentences with amount of words less than " + amount);
        }else{
            throw new HandlerException("Incorrect text component");
        }
    }

    @Override
    public HashMap<String, Integer> amountOfSimilarWords(TextComponent component) throws HandlerException {
        if (component.getType() == ComponentType.TEXT){
            List<TextComponent> paragraphs = component.getChildren();
            HashMap<String, Integer> similarWords = new HashMap<>();
            for (var paragraph : paragraphs){
                List<TextComponent> sentences = paragraph.getChildren();
                for (var sentence : sentences){
                    List<TextComponent> words = getWordsFromSentence(sentence);
                    for (var word : words){
                        String key = word.toString().toLowerCase();
                        if (similarWords.containsKey(key)){
                            Integer oldValue = similarWords.get(key);
                            similarWords.replace(key, oldValue, oldValue + 1);
                        }else{
                            similarWords.put(key, 1);
                        }
                    }
                }
            }
            logger.log(Level.INFO, "Were returned map with all similar words");
            return similarWords;
        }else{
            throw new HandlerException("Incorrect text component");
        }
    }

    @Override
    public int amountOfVowels(TextComponent component) throws HandlerException {
        if (component.getType() == ComponentType.SENTENCE){
            List<TextComponent> words = getWordsFromSentence(component);
            int amountOfVowels = 0;
            for (var word : words){
                List<TextComponent> letters = word.getChildren();
                for (var letter : letters){
                    if (VOWELS.contains(letter.toString().toLowerCase())){
                        amountOfVowels++;
                    }
                }
            }
            logger.log(Level.INFO, "Were returned amount of vowels");
            return amountOfVowels;
        }else{
            throw new HandlerException("Incorrect text component");
        }
    }

    @Override
    public int amountOfConsonants(TextComponent component) throws HandlerException {
        if (component.getType() == ComponentType.SENTENCE){
            List<TextComponent> words = getWordsFromSentence(component);
            int amountOfConsonants = 0;
            for (var word : words){
                List<TextComponent> letters = word.getChildren();
                for (var letter : letters){
                    if (CONSONANTS.contains(letter.toString().toLowerCase())){
                        amountOfConsonants++;
                    }
                }
            }
            logger.log(Level.INFO, "Were returned amount of consonants");
            return amountOfConsonants;
        }else{
            throw new HandlerException("Incorrect text component");
        }
    }
    private List<TextComponent> getWordsFromSentence(TextComponent component) throws HandlerException {
        List<TextComponent> lexemes = component.getChildren();
        List<TextComponent> words = new ArrayList<>();
        for (var lexeme : lexemes){
            List<TextComponent> components = lexeme.getChildren();
            for (var c : components){
                if (c.getType() == ComponentType.WORD){
                    words.add(c);
                }
            }
        }
        return words;
    }
}
