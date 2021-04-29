package algorithms.search.string;

/**
 * 暴力匹配算法 或 朴素匹配算法
 *
 * 主串n 与 模式串m   n > m
 *
 * @author otfot
 * @date 2021/04/29
 */
public class BruteForce {



    public boolean match(String master, String pattern) {

        int mLen = master.length();
        int pLen = pattern.length();

        if (mLen < pLen) {
            return false;
        }

        for (int i = 0; i < mLen - pLen + 1; i++) {
             if (subMatch(master.substring(i, i+pLen), pattern))  {
                 return true;
             }
        }

        return false;
    }

    private boolean subMatch(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

}
