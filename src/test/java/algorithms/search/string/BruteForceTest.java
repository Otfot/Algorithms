package algorithms.search.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date 2021/04/29
 */
class BruteForceTest {

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

        BruteForce bf = new BruteForce();

        for (int i = 0; i < strs.length; i++) {
            boolean res = bf.match(strs[i], pattern);
            assertEquals(res, ress[i]);
        }
    }
}