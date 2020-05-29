import WordWrap.WordWrapper;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class WordWrapperShould {

    /*
    To do list:
         ("", 2) -> ""
         ("Hola", -2) -> "Not negative numbers allowed"
         ("Hola", 0) -> "Not zero"
         ("a longword", 8) -> "a--longword"
         ("Hoy tengo papas de comer" , 5) -> "Hoy--tengo--papas--de--comer"
         ("longword", 4) -> "long--word"
         ("areallylongword", 6) -> "areall--ylongw--ord"
         ("a longword", 6) -> "a long--word"
         ("Un hipopotamo se hizo amigo de un murcielago", 7) -> "Un--hipopot--amo-se hizo--amigo--de un--murciel--ago"
     */

    @Test
    public void small_text_does_not_need_to_be_wrapped(){
        WordWrapper wordWrapper = new WordWrapper();
        assertThat(wordWrapper.wrap("", 2)).isEqualTo("");
        assertThat(wordWrapper.wrap("test", 7)).isEqualTo("test");
    }

    @Test
    public void words_are_wrapped_when_they_dont_fit_the_column_width(){
        WordWrapper wordWrapper = new WordWrapper();
        assertThat(wordWrapper.wrap("longword", 4)).isEqualTo("long\nword");
        assertThat(wordWrapper.wrap("areallylongword", 6)).isEqualTo("areall\nylongw\nord");
    }

    @Test
    public void wrap_lines_at_the_space_position_when_possible(){
        WordWrapper wordWrapper = new WordWrapper();
        assertThat(wordWrapper.wrap("d ef", 3)).isEqualTo("d\nef");
        assertThat(wordWrapper.wrap("abc def", 3)).isEqualTo("abc\ndef");
        assertThat(wordWrapper.wrap("abc def ghi", 3)).isEqualTo("abc\ndef\nghi");
        assertThat(wordWrapper.wrap("a longword", 8)).isEqualTo("a\nlongword");
        assertThat(wordWrapper.wrap("a longword", 6)).isEqualTo("a\nlongwo\nrd");
    }


    @Test
    public void replaces_only_those_spaces_matching_the_wrap_index(){
        WordWrapper wordWrapper = new WordWrapper();
        assertThat(wordWrapper.wrap(" def", 3)).isEqualTo("\ndef");
        assertThat(wordWrapper.wrap("  def", 3)).isEqualTo(" \ndef");
        assertThat(wordWrapper.wrap("abc  def", 3)).isEqualTo("abc\n\ndef");
        assertThat(wordWrapper.wrap("abc   def", 3)).isEqualTo("abc\n \ndef");
    }
}
