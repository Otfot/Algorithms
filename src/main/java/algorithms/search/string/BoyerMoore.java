package algorithms.search.string;

/**
 * BM 算法 速度超 KMP 算法 3-4 倍
 * <p>
 * 坏字符规则
 * 从后往前匹配，如果模式字符串与主字符串不匹配 则查找模式字符串中是否有该字符，有则将其位置对其（如果有多个对齐最后一个），
 * 重新从后向前匹配，如果不存在，则直接移动到该字符之后位置
 * <p>
 * 好后缀规则
 * 坏字符串规则有可能计算出移动位置为负  aaaaaaaaaa 与 baaa
 * 如果在遇到坏字符串之前，有匹配的多个字符,则在模式串中找出现该匹配字符串的最后一个字符串位置
 * 如果模式串没有，则查看匹配字符串的后缀子串有没有（查前缀匹配），没有则直接移动到匹配子字符串的后面
 * <p>
 * 可以只使用好后缀规则实现 BM 算法
 * <p>
 * grep 命令使用 BM 算法实现
 *
 * @author otfot
 * @date 2021/04/29
 */
public class BoyerMoore {

    private static final int SIZE = 128;
    private int[] hashPatternStr;

    private boolean[] prefix;
    private int[] suffix;


    public BoyerMoore() {
        hashPatternStr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            hashPatternStr[i] = -1;
        }
    }

    public boolean match(String master, String pattern) {

        int mLen = master.length();
        int pLen = pattern.length();

        if (mLen < pLen) {
            return false;
        }

        // 计算 模式串 hash 值，为之后之后找位置方便
        computeHash(pattern);

        // 主串与模式串对齐的第一个字符
        int i = 0;

        while (i <= mLen - pLen) {
            int j;
            // 模式串从后往前匹配
            for (j = pLen - 1; j >= 0; j--) {
                if (master.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            // 匹配成功
            if (j < 0) {
                return true;
            }
            // 没有匹配时，移动重新匹配位置
            int x = j - hashPatternStr[master.charAt(i + j)];
            int y = 0;

            // 如果有好后缀，不是从第一个就不相等
            if (j < pLen - 1) {
                y = computeGoodMove(j, pLen);
            }

            i = i + Math.max(x, y);
        }

        return false;
    }

    private void computeHash(String str) {
        // 模式字符串相同字符位置会被覆盖，为了得到最靠后的位置，防止滑动过长
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i);
            hashPatternStr[index] = i;
        }
    }

    private int computeGoodMove(int badIndex, int pLen) {
        // 好后缀长度
        int k = pLen - badIndex;

        // 判断好后缀长度是否在模式串中存在其他地方
        if (suffix[k] != -1) {
            // 加 1 是为了移动到坏字符后面一位
            return badIndex - suffix[k] + 1;
        }

        // 没有好后缀，看是否有前缀字串, 由于是字串，所以开始是从第二个后缀位置开始
        for (int i = badIndex + 2; i <= pLen - 1; i++) {
            if (prefix[pLen - i]) {
                return i;
            }
        }
        return pLen;
    }

    private void recordPrefixAndSuffix(String str) {
        int l = str.length();
        suffix = new int[l];
        prefix = new boolean[l];
        for (int i = 0; i < l; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        // 最多到倒数第二个字符
        for (int i = 0; i < l - 1; i++) {
            int j = i;
            int k = 0;

            while (j >= 0 && str.charAt(j) == str.charAt(l - 1 - k)) {
                --j;
                k++;
                // 此时 k 从 1 开始记录，后缀字串长度为 1 记录
                // j + 1 是因为 上面 j--，此时还原
                suffix[k] = j + 1;
            }

            // 如果 此时 j == 0， 则有一个公共前缀(即使之后有公共后缀，但此时公共前缀记下了，之后不会被更新
            if (j == -1) {
                prefix[k] = true;
            }
        }


    }

}
