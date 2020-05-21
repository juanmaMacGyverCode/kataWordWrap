package WordWrap;

import java.util.LinkedList;

public class WordWrap {
    public String wordWrap(String text, int columnWidth) {

        if (columnWidth < 1) {
            return "Err. The column width can't to be negative or zero";
        }

        if (text.length() > 0) {
            return treatment_of_text(text, columnWidth);
        }

        return "";
    }

    private String treatment_of_text(String text, int columnWidth) {
        String[] splitTextBySpaces = text.split(" ");

        if (splitTextBySpaces.length > 1) {
            return wrap_all_text(text, columnWidth);
        } else {
            return wrap_one_word(text, columnWidth);
        }

    }

    private String wrap_one_word(String text, int columnWidth) {

        LinkedList<String> splitWord = new LinkedList<String>();
        for (int i = 0; i < text.length(); i = i + columnWidth) {
            if (i + columnWidth < text.length()) {
                splitWord.add(text.substring(i, i + columnWidth));
            } else {
                splitWord.add(text.substring(i));
            }
        }

        return createNewStringWithWrap(splitWord);
    }

    private String wrap_all_text(String text, int columnWidth) {
        String[] splitInWords = text.split(" ");

        LinkedList<String> eachLine = new LinkedList<String>();
        for (int i = 0; i < splitInWords.length; i++) {
            if (wordIsMinorThanColumnWidth(splitInWords[i], columnWidth)) {
                if (eachLine.isEmpty()) {
                    eachLine.add(splitInWords[i]);
                } else {
                    if (wordIsMinorThanColumnWidthAndInnerWord(splitInWords[i], eachLine.getLast(), columnWidth)) {
                        eachLine.set(eachLine.size() - 1, eachLine.getLast() + " " + splitInWords[i]);
                    } else {
                        eachLine.add(splitInWords[i]);
                    }
                }
            } else {
                eachLine.add(wrap_one_word(splitInWords[i], columnWidth));
            }
        }
        return createNewStringWithWrap(eachLine);
    }

    private boolean wordIsMinorThanColumnWidthAndInnerWord(String word, String innerWord, int columnWidth) {
        return columnWidth >= innerWord.length() + 1 + word.length();
    }

    private boolean wordIsMinorThanColumnWidth(String word, int columnWidth) {
        return word.length() <= columnWidth;
    }

    private String createNewStringWithWrap(LinkedList<String> eachLine) {

        String formedText = "";
        for (int i = 0; i < eachLine.size(); i++) {
            formedText += eachLine.get(i);
            if (i < eachLine.size() - 1) {
                formedText += "\n";
            }
        }
        return formedText;
    }
}
