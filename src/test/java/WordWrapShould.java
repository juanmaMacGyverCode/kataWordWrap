import WordWrap.WordWrap;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordWrapShould {

    @Test
    public void allow_void_expresion(){
        WordWrap wordWrap = new WordWrap();
        assertThat(wordWrap.wordWrap("", 10)).isEqualTo("");
    }

    @Test
    public void not_allow_negative_column_width_less_than_or_equal_to_zero(){
        WordWrap wordWrap = new WordWrap();
        assertThat(wordWrap.wordWrap("", -1)).isEqualTo("Err. The column width can't to be negative or zero");
        assertThat(wordWrap.wordWrap("", 0)).isEqualTo("Err. The column width can't to be negative or zero");
    }

    @Test
    public void allow_word_wrap_one_word(){
        WordWrap wordWrap = new WordWrap();
        assertThat(wordWrap.wordWrap("Hola", 2)).isEqualTo("Ho\nla");
        assertThat(wordWrap.wordWrap("Holahola", 4)).isEqualTo("Hola\nhola");
        assertThat(wordWrap.wordWrap("Holaholahola", 4)).isEqualTo("Hola\nhola\nhola");
        assertThat(wordWrap.wordWrap("Hola", 1)).isEqualTo("H\no\nl\na");
    }

    @Test
    public void allow_word_wrap_two_words(){
        WordWrap wordWrap = new WordWrap();
        assertThat(wordWrap.wordWrap("Hola hola", 4)).isEqualTo("Hola\nhola");
        assertThat(wordWrap.wordWrap("Hola hola", 5)).isEqualTo("Hola\nhola");
        assertThat(wordWrap.wordWrap("Hola mundo a todo el planeta", 5)).isEqualTo("Hola\nmundo\na\ntodo\nel\nplane\nta");
        assertThat(wordWrap.wordWrap("Hola mundo a todo el planeta", 6)).isEqualTo("Hola\nmundo\na todo\nel\nplanet\na");
        assertThat(wordWrap.wordWrap("Hola mundo a todo el planeta", 10)).isEqualTo("Hola mundo\na todo el\nplaneta");
    }
}
