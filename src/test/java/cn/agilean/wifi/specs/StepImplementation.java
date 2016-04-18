package cn.agilean.wifi.specs;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class StepImplementation {

    private HashSet<Character> vowels;

    @Step("Vowels in English language are <vowelString>.")
    public void setLanguageVowels(String vowelString) {
        vowels = new HashSet<>();
        for (char ch : vowelString.toCharArray()) {
            vowels.add(ch);
        }
        Gauge.writeMessage("vowelString，print message");
    }

    @Step("print vowels <word>")
    public void printVowels(String word) {
    	assertEquals("aeiou", word);
    	Gauge.writeMessage("in wowels word:"+word);
    }
    
    @Step("print vowels <word>")
    public void printVowelsTest(String word) {
    	assertEquals("aeiou", word);
    	Gauge.writeMessage("in wowels word test:"+word);
    }
    
    @Step("The word <word> has <expectedCount> vowels.")
    public void verifyVowelsCountInWord(String word, int expectedCount) {
        int actualCount = countVowels(word);
        assertEquals(expectedCount, actualCount);
    }

    @Step("Almost all words have vowels <wordsTable>")
    public void verifyVowelsCountInMultipleWords(Table wordsTable) {
        for (TableRow row : wordsTable.getTableRows()) {
            String word = row.getCell("Word");
            int expectedCount = Integer.parseInt(row.getCell("Vowel Count"));
            int actualCount = countVowels(word);

            assertEquals(expectedCount, actualCount);
        }
    }
    
    @Step({"Create a user <username>", "Create another user <username>"})
    public void helloWorld(String role) {
        Gauge.writeMessage("role:"+role);
    }
    
    @Step({"创建一个用户 <username>", "创建另一个用户 <username>"})
    public void helloWorldTest(String role) {
        Gauge.writeMessage("role:"+role);
    }

    private int countVowels(String word) {
        int count = 0;
        for (char ch : word.toCharArray()) {
            if (vowels.contains(ch)) {
                count++;
            }
        }
        return count;
    }
}
