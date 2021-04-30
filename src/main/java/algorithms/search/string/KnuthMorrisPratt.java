package algorithms.search.string;

/**
 * KMP 算法
 * D.E.Knuth，J.H.Morris 和 V.R.Pratt
 * <p>
 * 主串好前缀的最长后缀匹配
 * 模式串的好前缀的最长前缀匹配
 * <p>
 * 模式串最长可匹配前缀字串的结尾字符下标 next 数组（失效函数）
 *
 * @author otfot
 * @date 2021/04/30
 */
public class KnuthMorrisPratt {


    public boolean match(String master, String pattern) {
        int mLen = master.length();

        int pLen = pattern.length();

        if (mLen < pLen) {
            return false;
        }

        int[] next = generateNextArray(pattern, pLen);
        // j 表示坏字符的位置
        int j = 0;
        for (int i = 0; i < mLen; i++) {

            while (j > 0 && master.charAt(i) != pattern.charAt(j)) {
                // 当前位置 i 与 j 定位的字符不等
                // j - 1 是最长匹配的字符串的最后一位 next 获取它的前缀匹配
                // + 1 为 0 时就是没有
                // 判断此时前缀后一位和当前主串的字符是否相等
                // 如果没有相等的 此时 j = 0 相当于从头开始比较
                j = next[j - 1] + 1;
            }

            // j 与 i 位置字符相等就前进一位
            if (master.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if (j == pLen) {
                // return i - pLen + 1
                return true;
            }
        }

        return false;
    }


    private int[] generateNextArray(String str, int len) {
        int[] next = new int[len];

        next[0] = -1;
        // k 表示前缀匹配的最后一个
        int k = -1;
        // i 表示 当前从 0 到 i 长度的字符串的最后一位来判断它有没有前缀最长子串
        // 从 i从 1 开始比较重要
        for (int i = 1; i < len; i++) {

            // 如果不相等 就去当前前面找有没有能够匹配的，找不到 next[0] == -1 了
            while (k != -1 && str.charAt(k + 1) != str.charAt(i)) {
                k = next[k];
            }

            if (str.charAt(k + 1) == str.charAt(i)) {
                ++k;
            }

            next[i] = k;
        }

        return next;
    }
}
