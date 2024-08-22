import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DelimitersTest {
  private Delimiters delimiters;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void checkConstructor() {
    delimiters = new Delimiters("<", ">");
    assertEquals("<", delimiters.getOpenDel());
    assertEquals(">", delimiters.getCloseDel());
  }

  @Test
  void getDelimitersList() {
    delimiters = new Delimiters("(", ")");
    String[] tokens = {"(", "x+y", ")", "*5"};
    ArrayList<String> delimiterList = delimiters.getDelimitersList(tokens);
    assertEquals(2, delimiterList.size());
    assertEquals("(", delimiterList.get(0));
    assertEquals(")", delimiterList.get(1));

    delimiters = new Delimiters("<q>", "</q>");
    tokens = new String[]{"<q>", "yy", "</q>", "zz", "</q>"};
    delimiterList = delimiters.getDelimitersList(tokens);
    assertEquals(3, delimiterList.size());
    assertEquals("<q>", delimiterList.get(0));
    assertEquals("</q>", delimiterList.get(1));
    assertEquals("</q>", delimiterList.get(2));
  }

  @Test
  void isBalancedOne() {
    delimiters = new Delimiters("<sup>", "</sup>");
    String[] tokens = {"<sup>", "<sup>", "yo", "yo", "hey", "</sup>", "<sup>", "hey", "</sup>", "yo", "</sup>"};
    ArrayList<String> delimiterList = delimiters.getDelimitersList(tokens);

    assertEquals(6, delimiterList.size());
    assertEquals("<sup>", delimiterList.get(0));
    assertEquals("<sup>", delimiterList.get(1));
    assertEquals("</sup>", delimiterList.get(2));
    assertEquals("<sup>", delimiterList.get(3));
    assertEquals("</sup>", delimiterList.get(4));
    assertEquals("</sup>", delimiterList.get(5));

    assertTrue(delimiters.isBalanced(delimiterList));
  }

  @Test
  void isBalancedTwo() {
    delimiters = new Delimiters("<sup>", "</sup>");
    String[] tokens = {"<sup>", "yo", "yo", "hey", "</sup>", "hey", "</sup>", "yo", "<sup>"};
    ArrayList<String> delimiterList = delimiters.getDelimitersList(tokens);

    assertEquals(4, delimiterList.size());
    assertEquals("<sup>", delimiterList.get(0));
    assertEquals("</sup>", delimiterList.get(1));
    assertEquals("</sup>", delimiterList.get(2));
    assertEquals("<sup>", delimiterList.get(3));

    assertFalse(delimiters.isBalanced(delimiterList));
  }

  @Test
  void isBalancedThree() {
    delimiters = new Delimiters("<sup>", "</sup>");
    String[] tokens = {"yo", "yo", "hey", "</sup>", "hey", "yo"};
    ArrayList<String> delimiterList = delimiters.getDelimitersList(tokens);

    assertEquals(1, delimiterList.size());
    assertEquals("</sup>", delimiterList.get(0));

    assertFalse(delimiters.isBalanced(delimiterList));
  }

  @Test
  void isBalancedFour() {
    delimiters = new Delimiters("<sup>", "</sup>");
    String[] tokens = {"<sup>", "yo", "<sup>", "yo", "hey", "</sup>", "hey", "yo"};
    ArrayList<String> delimiterList = delimiters.getDelimitersList(tokens);

    assertEquals(3, delimiterList.size());
    assertEquals("<sup>", delimiterList.get(0));
    assertEquals("<sup>", delimiterList.get(1));
    assertEquals("</sup>", delimiterList.get(2));

    assertFalse(delimiters.isBalanced(delimiterList));
  }

  @Test
  void isBalancedFive() {
    delimiters = new Delimiters("<sup>", "</sup>");
    String[] tokens = {"<sup>", "yo", "</sup>", "<sup>", "yo", "<sup>", "hey", "</sup>", "hey", "yo", "</sup>"};
    ArrayList<String> delimiterList = delimiters.getDelimitersList(tokens);

    assertEquals(6, delimiterList.size());
    assertEquals("<sup>", delimiterList.get(0));
    assertEquals("</sup>", delimiterList.get(1));
    assertEquals("<sup>", delimiterList.get(2));
    assertEquals("<sup>", delimiterList.get(3));
    assertEquals("</sup>", delimiterList.get(4));
    assertEquals("</sup>", delimiterList.get(5));

    assertTrue(delimiters.isBalanced(delimiterList));
  }
}