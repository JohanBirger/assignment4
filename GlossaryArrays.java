/**
 * Class containing the glossary arrays, this class can <br/>
 * be altered with more words and added functionality such as <br/>
 * file reader for input and addition of synonyms
 */

public class GlossaryArrays {

        //array with swedish glossary word
        private String[] swe_words = {"Bil","Bok","Mus","Dator","Fönster","Kopp","Penna","Växt","Gitarr","Glasögon"};

        //array with english glossary words
        private String[] eng_words = {"Car","Book","Mouse","Computer","Window","Cup","Pen","Plant","Guitar","Glasses"};

        public String[] getSwe_words() {
            return swe_words.clone();
        }

        public String[] getEng_words() {
            return eng_words.clone();
        }
}


