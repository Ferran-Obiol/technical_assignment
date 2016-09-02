package main;

/*
An object of type RomanNumeral is an integer between 1 and 3999.  It can
be constructed either from an integer or from a string that represents
a Roman numeral in this range.  The function toString() will return a
standardized Roman numeral representation of the number.  The function
toInt() will return the number as a value of type int.
*/

public class RomanNumeral {

private final int num;   // The number represented by this Roman numeral.

/* The following arrays are used by the toString() function to construct
   the standard Roman numeral representation of the number.  For each i,
   the number numbers[i] is represented by the corresponding string, letters[i].
*/

private static int[]    numbers = { 1000,  900,  500,  400,  100,   90,  
                                      50,   40,   10,    9,    5,    4,    1 };
                                   
private static String[] letters = { "M",  "CM",  "D",  "CD", "C",  "XC",
                                    "L",  "XL",  "X",  "IX", "V",  "IV", "I" };
   

public RomanNumeral(int arabic) {
      // Constructor.  Creates the Roman number with the int value specified
      // by the parameter.  Throws a NumberFormatException if arabic is
      // not in the range 1 to 3999 inclusive.
   if (arabic < 1)
      throw new NumberFormatException("Value of RomanNumeral must be positive.");
   if (arabic > 3999)
      throw new NumberFormatException("Value of RomanNumeral must be 3999 or less.");
   num = arabic;
}


public RomanNumeral(String roman) {
      // Constructor.  Creates the Roman number with the given representation.
      // For example, RomanNumeral("xvii") is 17.  If the parameter is not a
      // legal Roman numeral, a NumberFormatException is thrown.  Both upper and
      // lower case letters are allowed.
      
   if (roman.length() == 0)
      throw new NumberFormatException("An empty string does not define a Roman numeral.");
      
   roman = roman.toUpperCase();  // Convert to upper case letters.
   
   int i = 0;       // A position in the string, roman;
   int arabic = 0;  // Arabic numeral equivalent of the part of the string that has
                    //    been converted so far.
   int repeatingCounter = 1; // Counter for checking no more than 3 letters repeated
  // boolean substraction = false; //To check if we did a substraction on the previous iteration.
   while (i < roman.length()) {
   
      char letter = roman.charAt(i);        // Letter at current position in string.
      int number = letterToNumber(letter);  // Numerical equivalent of letter.
           
      if (number < 0)
         throw new NumberFormatException("Illegal character \"" + letter + "\" in roman numeral.");
         
      i++;  // Move on to next position in the string
      
      if (i == roman.length()) {
            // There is no letter in the string following the one we have just processed.
            // So just add the number corresponding to the single letter to arabic.
         arabic += number;
      }
      else {
            // Look at the next letter in the string.  If it has a larger Roman numeral
            // equivalent than number, then the two letters are counted together as
            // a Roman numeral with value (nextNumber - number).
    	 char nextLetter = roman.charAt(i);
         int nextNumber = letterToNumber(roman.charAt(i));
         if (nextNumber > number) {
             //We check several things to see if it's a valid substraction
        	 //Only one small-value symbol may be subtracted from any large-value symbol
        	 if (repeatingCounter>1)
        		 throw new NumberFormatException("We cannot have two equal letters before substraction.");
        	 //We cannot substract letters V, L or D
        	 if (letter=='V'|| letter=='L' ||letter=='D') 
        		 throw new NumberFormatException("This combination of roman numerals is invalid.");
        	 //We check it it's one of the valid substraction cases
        	 if ((letter=='I') && !((nextLetter=='V') || (nextLetter=='X')))
        		 throw new NumberFormatException("This combination of roman numerals is invalid.");
        	 if ((letter=='X') && !((nextLetter=='L') || (nextLetter=='C')))
        		 throw new NumberFormatException("This combination of roman numerals is invalid.");
        	 if ((letter=='C') && !((nextLetter=='D') || (nextLetter=='M')))
        		 throw new NumberFormatException("This combination of roman numerals is invalid.");	 
        		 
        		 
        	// Combine the two letters to get one value, and move on to next position in string. 
            arabic += (nextNumber - number);
            i++;
            repeatingCounter=1;
         }
         else {
        	 //We check we are not repeating letters V, L or D
        	 if (((letter=='V') && (nextLetter=='V')) ||((letter=='V') && (nextLetter=='L')) ||((letter=='L') && (nextLetter=='D')) ||((letter=='D')))
        		 throw new NumberFormatException("This combination of roman numerals is invalid.");
        	
        	 //We update the repeatingCounter
        	 if (letter==nextLetter) {
        		 repeatingCounter++; 
        		 System.out.println("repeatingCounter=" + repeatingCounter);
        		 System.out.println("letter=" + letter);
        		 System.out.println("nextLetter=" + nextLetter);
        	 }
        	 else {repeatingCounter = 1; System.out.println("repeatingCounter=" + repeatingCounter);};
        		
        	 // If the number of repetitions is bigger than 3, it's an invalid number
        	 if (repeatingCounter>3)
        	  throw new NumberFormatException("This combination of roman numerals is invalid.");
        	 
               // Don't combine the letters.  Just add the value of the one letter onto the number.
            arabic += number;
         }
      }
      
   }  // end while
   
   if (arabic > 3999)
      throw new NumberFormatException("Roman numeral must have value 3999 or less.");
      
   num = arabic;
   
} // end constructor


private int letterToNumber(char letter) {
      // Find the integer value of letter considered as a Roman numeral.  Return
      // -1 if letter is not a legal Roman numeral.  The letter must be upper case.
   switch (letter) {
      case 'I':  return 1;
      case 'V':  return 5;
      case 'X':  return 10;
      case 'L':  return 50;
      case 'C':  return 100;
      case 'D':  return 500;
      case 'M':  return 1000;
      default:   return -1;
   }
}


public String toString() {
      // Return the standard representation of this Roman numeral.
   String roman = "";  // The roman numeral.
   int N = num;        // N represents the part of num that still has
                       //   to be converted to Roman numeral representation.
   for (int i = 0; i < numbers.length; i++) {
      while (N >= numbers[i]) {
         roman += letters[i];
         N -= numbers[i];
      }
   }
   return roman;
}


public int toInt() {
     // Return the value of this Roman numeral as an int.
   return num;
}


} // end class RomanNumeral