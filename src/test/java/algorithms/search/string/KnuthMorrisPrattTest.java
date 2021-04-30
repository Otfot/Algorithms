package algorithms.search.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date 2021/04/30
 */
class KnuthMorrisPrattTest {

    @Test
    void match() {
        String[] strs = {
                "abccdescdc",
                "aaaaaaaaab",
                "dksfwehjdsead",
                "dkdkadasddsddd",
                "dddddddsssddsdddsdss",
                "kdksfjksdflsaaab",
                "     "
        };

        boolean[] ress = {
                true,
                true,
                false,
                false,
                false,
                true,
                false
        };

        String pattern = "ab";

        KnuthMorrisPratt kmp = new KnuthMorrisPratt();

        for (int i = 0; i < strs.length; i++) {
            boolean res = kmp.match(strs[i], pattern);
            assertEquals(res, ress[i]);
        }
    }
}