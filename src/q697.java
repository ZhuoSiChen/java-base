//给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
//
// 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
//
//
//
// 示例 1：
//
//
//输入：[1, 2, 2, 3, 1]
//输出：2
//解释：
//输入数组的度是2，因为元素1和2的出现频数最大，均为2.
//连续子数组里面拥有相同度的有如下所示:
//[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
//最短连续子数组[2, 2]的长度为2，所以返回2.
//
//
// 示例 2：
//
//
//输入：[1,2,2,3,1,4,2]
//输出：6
//
//
//
//
// 提示：
//
//
// nums.length 在1到 50,000 区间范围内。
// nums[i] 是一个在 0 到 49,999 范围内的整数。
//
// Related Topics 数组
// 👍 269 👎 0


import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class ShortestSubArray{
        int key;
        int value;
        int start;
        int end;
    }
    public int findShortestSubArray(int[] nums) {
        Map<Integer, ShortestSubArray> hashMap = new HashMap<Integer, ShortestSubArray>();
        for (int i = 0; i < nums.length; i++) {
            ShortestSubArray shortestSubArray = hashMap.get(nums[i]);
            if(shortestSubArray == null) {
                shortestSubArray = new ShortestSubArray();
                shortestSubArray.key = nums[i];
                shortestSubArray.value = 1;
                shortestSubArray.start = i;
                shortestSubArray.end = i;
                hashMap.put(nums[i],shortestSubArray);
            }else{
                shortestSubArray.end = i;
                shortestSubArray.value = shortestSubArray.value +1;
                hashMap.put(nums[i],shortestSubArray);
            }
        }
        int max = 0;
        int shortest = nums.length;
        for(Map.Entry<Integer, ShortestSubArray> shortestSub:hashMap.entrySet()){
            ShortestSubArray shortestSubArray = shortestSub.getValue();
            int len = shortestSubArray.end - shortestSubArray.start + 1;
            if(shortestSubArray.value > max ){
                max = shortestSubArray.value;
                shortest = len;
            }else if(shortestSubArray.value == max && shortest > len){
                shortest = len;
            }

        }
        return shortest;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,2,3,1,4,2};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
