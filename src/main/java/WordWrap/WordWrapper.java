package WordWrap;

public class WordWrapper {
    public String wrap(String text, int columnWidth) {
        if (text.length() <= columnWidth) {
            return text;
        }
        int indexOfSpace = text.lastIndexOf(" ");
        boolean shallWrapUsingSpace = indexOfSpace > -1 && indexOfSpace < columnWidth;
        int wrapIndex = (shallWrapUsingSpace) ? indexOfSpace : columnWidth;
        String wrappedText = text.substring(0, wrapIndex).concat("\n");
        String unwrappedText = text.substring(wrapIndex).replaceFirst(" ", "");
        return wrappedText.concat(wrap(unwrappedText, columnWidth));
    }

}
