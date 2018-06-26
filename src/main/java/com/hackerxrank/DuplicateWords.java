package com.hackerxrank;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 */
public class DuplicateWords {

    public static void main(String[] args) {

        String regex = "(?i)\\b(\\w+)((?:\\s+)(\\1\\b))+";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Scanner in = new Scanner(System.in);
        int numSentences = Integer.parseInt(in.nextLine());
        
        while (numSentences-- > 0) {
            String input = in.nextLine();
            
            Matcher m = p.matcher(input);
            
            // Check for subsequences of input that match the compiled pattern
            while (m.find()) {
                
                String  tempOutput;
                tempOutput = String.format("I found the text" + " \"%s\" starting at index %d and ending at index %d.%n",
                                            m.group(), 
                                            m.start(), 
                                            m.end());
                System.out.println(tempOutput);
                System.out.println("Group count: " + m.groupCount());
                for (int i = 1; i <m.groupCount(); i++) {
                    
                    System.out.println("Group[" + i + "]=" + m.group(i));
                }
                
                input = input.replaceAll(regex, "$1"); //
            }
            
            // Prints the modified sentence.
            System.out.println("Modified: " + input);
        }
        
        in.close();
        
    }
}
