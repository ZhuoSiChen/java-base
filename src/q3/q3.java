package q3;

import java.util.HashMap;


//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
// 示例 4:
//
//
//输入: s = ""
//输出: 0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 4979 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("abba"));
    }
//leetcode submit region end(Prohibit modification and deletion)

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        int right = 0;
        int left = 0;
        int max = 0;
        while ( left < s.length()) {
            Integer integer = hashMap.get(chars[left]);
            if (integer == null) {
                hashMap.put(chars[left], left);
            } else {
                int end = integer + 1;
                for(int i=right;i < integer+1;i++){
                    hashMap.remove(chars[i]);
                }
                right = integer +1;
                hashMap.put(chars[left], left);
            }
            if (left - right + 1 > max) {
                max = left - right + 1;
            }
            left++;
        }
        return max;
    }
}

