package algorithms.search.string;

/**
 * 在 BF 算法的基础上加上 哈希算法，减少 字符串字符依次对比时间
 *
 * @author otfot
 * @date 2021/04/29
 */
public class RabinKarp {


    public boolean match(String master, String pattern) {
        int mLen = master.length();
        int pLen = pattern.length();

        if (mLen < pLen) {
            return false;
        }

        int pHash = hash(pattern);
        int count = 0;
        for (int i = 0; i < mLen - pLen + 1; i++) {
            String substring = master.substring(i, i + pLen);
            int mHash = hash(substring);
            if (mHash == pHash) {
                if (subMatch(substring, pattern)) {
                    System.out.println("Count:" + count);
                    return true;
                }
            } else {
                count++;
            }

        }

        System.out.println("Count:" + count);
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

    private int hash(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) % 2 == 0) {
                count += str.charAt(i) * 2;
            } else {
                count += str.charAt(i);
            }
        }

        return count;
    }
}
