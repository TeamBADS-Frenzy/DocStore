package com.amazon.docstore.utils;

public class SearchUtil {
    static int NO_OF_CHARS = 256;
    //The preprocessing function for Boyer Moore's bad character heuristic
    private static void badCharHeuristic(char[] input, int size, int badChar[]) {

        // Initialize all occurrences as -1
        for (int i = 0; i < NO_OF_CHARS; i++)
            badChar[i] = -1;

        // Fill the actual value of last occurrence
        // of a character (indices of table are ascii and values are index of occurrence)
        for (int i = 0; i < size; i++)
            badChar[input[i]] = i;
    }

    /* A pattern searching function that uses Bad
    Character Heuristic of Boyer Moore Algorithm */
    public static int search(char input[], char pattern[]) {
        int patternLen = pattern.length;
        int inputLength = input.length;

        int badChar[] = new int[NO_OF_CHARS];

	/* Fill the bad character array by calling
		the preprocessing function badCharHeuristic()
		for given pattern */
        badCharHeuristic(pattern, patternLen, badChar);

        int shift = 0; // s is shift of the pattern with respect to text
        int totalOccurence =0 ;
        while (shift <= (inputLength - patternLen)) {
            int j = patternLen - 1;

		/* Keep reducing index j of pattern while
			characters of pattern and text are
			matching at this shift s */
            while (j >= 0 && pattern[j] == input[shift + j])
                j--;

		/* If the pattern is present at current
			shift, then index j will become -1 after
			the above loop */
            if (j < 0) {
                totalOccurence++;
                shift += (shift+patternLen < inputLength)? patternLen-badChar[input[shift+patternLen]] : 1;
            } else
			/* Shift the pattern so that the bad character
				in text aligns with the last occurrence of
				it in pattern. The max function is used to
				make sure that we get a positive shift.
				We may get a negative shift if the last
				occurrence of bad character in pattern
				is on the right side of the current
				character. */
                shift += Math.max(1, j - badChar[input[shift + j]]);
        }
        return totalOccurence;
    }
}