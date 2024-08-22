import java.util.ArrayList;

public class Delimiters {
  private String openDel;
  private String closeDel;

  public Delimiters(String open, String close) {
    openDel = open;
    closeDel = close;
  }

  public String getOpenDel() {
    return openDel;
  }

  public String getCloseDel() {
    return closeDel;
  }

  // Returns an array with only tokens that are delimiters.
  public ArrayList<String> getDelimitersList(String[] tokens) {
    ArrayList<String> result = new ArrayList<>();
    for (String token : tokens) {
      if (token.equals(openDel) || token.equals(closeDel))
        result.add(token);
    }
    return result;
  }

  // Check if open, close delimiters are balanced.
  // This is a similar problem to checking if parentheses are matching in an expression
  // There are two ways to approach this:
  // 1. Loop through tokens and keep an active tally of number of open delimiters and close delimiters.
  //    If at any point there are more close delimiters than open delimiters, return false.
  //    Otherwise, return true if there is an equal number of open and close delimiters
  // 2. (Particularly better for strings) Replace all non-delimiter characters in a string (including spaces).
  //    Have a match string that is equivalent to openDelimiter + closeDelimiter (e.g., for parentheses, "()")
  //    While the string still has the match string, delete the match from the string.
  //    When there are no more matches, if the string is empty, return true; if it is not empty, return false.
  // In terms of time complexity, the first option tends to be more efficient.
  // I've implemented the second option before here:
  // https://github.com/ChJus/Projects/blob/7e11e73cfe732132fca6fa35bd2f1e5c3f63cc34/Calculator/main.c#L857C1-L935C2.
  // https://github.com/ChJus/Projects/blob/7e11e73cfe732132fca6fa35bd2f1e5c3f63cc34/dDesCalculator/src/PrattCalculatorImproved.java#L238.
  public boolean isBalanced(ArrayList<String> delimiters) {
    int open = 0, close = 0;
    for (String delimiter : delimiters) {
      if (delimiter.equals(openDel)) open++;        // Tally open delimiters
      else if (delimiter.equals(closeDel)) close++; // Tally close delimiters
      if (open < close) return false;               // If at any time there are more close delimiters, return false.
    }

    // Check to see if there are an equal number of open and close delimiters
    return open == close;
  }
}
