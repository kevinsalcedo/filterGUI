package com.kev.sal.filtergui.util;

import com.kev.sal.filtergui.util.objects.GameSettings;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public class ListParser {
    /**
     * Takes a filepath, and read contents of text file to an arraylist
     * Text file is a newline-delimited list. One word per line.
     * @param file - absolute path to the chosen file
     * @return array list containing file contents
     */
    public static List<String> readWordsFromFile(File file) {
        List<String> wordList = new ArrayList<>();

        // Read each line of the given .txt file, add words to wordList
        // This assumes a correctly formatted .txt file.
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(word -> {
                // Should never be empty, basically
                if(!word.isEmpty()) {
                    wordList.add(word);
                }
            });
        } catch(Exception ex) {
            System.out.println("ERROR: Caught error reading .txt file contents - " + ex.getMessage());
        }
        // Sort list alphabetically
        Collections.sort(wordList);
        return wordList;
    }

    /**
     * Take a file, and convert the given list into a map with the alphagram as key, and matching words as values
     * This method is specifically for 9s, where we need to make an alphagram map of ALL nines, and compare it to
     * our pared down list of 9s.
     * @param file - the file we are converting
     * @return a map with keys being the alphagram and values being words matching that alphagram
     */
    public static Map<String, Set<String>> createAlphaMap(File file) {
        List<String> words = readWordsFromFile(file);
        return createAlphaMap(words);
    }

    /**
     * Take a list of words, and convert the given list into a map with the alphagram as key, and matching words as values
     * @param words - list of words
     * @return a map with keys being the alphagram and values being words matching that alphagram
     */
    public static Map<String, Set<String>> createAlphaMap(List<String> words) {
        Map<String, Set<String>> alphas = new HashMap<>();
        for(String word: words) {
            // Split each
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            String alpha = new String(letters);

            // Check the map for existing anagrams, putting it in the map if it does not exist
            Set<String> existingAnagrams = alphas.getOrDefault(alpha, null);
            if(existingAnagrams == null) {
                existingAnagrams = new HashSet<>();
                alphas.put(alpha, existingAnagrams);
            }
            existingAnagrams.add(word);
        }
        return alphas;
    }

    public static void generateOutput(GameSettings settings, Map<String, Set<String>> alphaMap, List<String> words) {
        try {
            // TODO: Either have the user choose the output destination (new parameter) or make it in the same dir as sourceFile
            File output = new File("output.js");
            FileWriter writer = new FileWriter(output);

            // Create the header line for the output file
            String headerLine = "WORD;ALPHA";
            if(settings.includesGiven()) {
                headerLine+="LETTER;INDICES;";
            }
            if(settings.includesHints()) {
                headerLine += "HINTS;";
            }

            // Do all the processing


        } catch (Exception ex) {
            System.out.println("ERROR: Encountered an error when generating output files: " + ex.getMessage());
        }
    }

    /**
     * Take a list of anagrams sharing the same alphagram, see which ones have a unique solution when selecting a certain amount of letters
     * @param alpha the alphagram string
     * @param anagrams list of words with a matching alphagram
     */
    private void generateGivenIndices(String alpha, List<String> anagrams) {
    }
}


