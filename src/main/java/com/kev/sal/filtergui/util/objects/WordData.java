package com.kev.sal.filtergui.util.objects;

import java.util.*;

/**
 * This class basically holds all the data for a single puzzle
 * Word, alpha and given will never be null
 * Hints may be null if useHints is false when constructing the word
 */
public class WordData {
    // The actual word
    private String word;
    // The word's alphagram
    private String alpha;
    // Map of the alphagram index of the given letter to the word's index of the letter
    private Map<Integer,Integer> givenMap;
    // Map of the alphagram index of the hint letter to the word's index of the letter
    private Map<Integer,Integer> hintsMap;

    public WordData(String word, Map<Integer,Integer> given, boolean useHints) {
        // Set word and create alpha
        this.word = word;
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        this.alpha = new String(letters);
        this.givenMap = given != null ? given : new HashMap<Integer, Integer>();

        if(useHints) {
            generateHints();
        }
    }


    public WordData(String word, String alpha, Map<Integer,Integer> given, boolean useHints) {
        // Set word and alpha
        this.word = word;
        this.alpha = alpha;
        this.givenMap = given != null ? given : new HashMap<Integer, Integer>();

        if(useHints) {
            generateHints();
        }
    }

    private void generateHints() {
        Random Dice = new Random();
        this.hintsMap = new HashMap<>();
        // TODO: WTF MAKE THIS BETTER
        // Track indices in the stupid word
        Map<String,Set<Integer>> wordThing = new HashMap<>();
        String[] splitWord = this.word.split("");
        for(int i = 0; i < splitWord.length; i++){
            String character = splitWord[i];
            Set<Integer> indices = wordThing.getOrDefault(character, new HashSet<>());
            indices.add(i);
            if(!wordThing.containsKey(character)) {
                wordThing.put(character, indices);
            }
        }

        // Generate 3 hints (KEY = ALPHA INDEX, VALUE = WORD INDEX)
        while(this.hintsMap.size() < 3) {
            // Get a random index between 0 and word length
            int alphaIndex = Dice.nextInt(this.alpha.length());
            // Make sure this index is not given or already a hint
            if(!this.givenMap.containsKey(alphaIndex) && !this.hintsMap.containsKey(alphaIndex)) {
                // Convert the existing indices in the word into a list so we can get a random element
                List<Integer> charIndices = new ArrayList<>(wordThing.get(this.alpha.charAt(alphaIndex)));
                int chosenIndex = charIndices.get(Dice.nextInt(charIndices.size()));
                // Remove the element from the set so that we don't get duplicate hints for the same letter
                wordThing.get(this.alpha.charAt(alphaIndex)).remove(chosenIndex);

                this.hintsMap.put(alphaIndex, chosenIndex);
            }
        }
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public Map<Integer, Integer> getGivenMap() {
        return givenMap;
    }

    public void setGivenMap(Map<Integer, Integer> givenMap) {
        this.givenMap = givenMap;
    }

    public Map<Integer, Integer> getHintsMap() {
        return hintsMap;
    }

    public void setHintsMap(Map<Integer, Integer> hintsMap) {
        this.hintsMap = hintsMap;
    }

}
