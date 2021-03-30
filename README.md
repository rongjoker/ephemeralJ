# ephemeralJ
Ephemeral codes,permanent thoughts;<br>
兄弟会背叛你，女人会离开你，金钱会诱惑你，生活会刁难你，只有算法不会，不会就是不会，怎么学都不会。<br>

## 注释
1. dp:动态规划
2. greedy:贪心算法
3. sw:滑动窗口
4. tp:双指针
5. fsp:快慢指针
6. ds:并查集
7. dfs:深度优先查找算法
8. bfs:广度优先查找算法
9. heap:堆
10. bs:二分查找算法
11. maths:数学
12. ps:前缀和
13. bt:回溯法
14. stack:单调栈
15. bit:位运算
16. str:字符串
17. array:数组

### 01/05/2021
[各种背包](src/main/java/org/rongjoker/dp/pack)

### 01/07/2021
[53. 最大子序和](src/main/java/org/rongjoker/dp/pack/HomeWork.java)<br>
[152. 乘积最大子数组](src/main/java/org/rongjoker/dp/pack/HomeWork.java)<br>

### 01/08/2021
[918. 环形子数组的最大和](src/main/java/org/rongjoker/dp/pack/HomeWork.java)<br>
[120. 三角形最小路径和](src/main/java/org/rongjoker/dp/pack/HomeWork.java)<br>
[198. 打家劫舍(198)](src/main/java/org/rongjoker/dp/target/HouseRobber198.java)<br>
[121. 买卖股票的最佳时机](src/main/java/org/rongjoker/dp/stock/BestTimeToBuyAndSellStock.java)<br>
[122. 买卖股票的最佳时机 II](src/main/java/org/rongjoker/dp/stock/BestTimeToBuyAndSellStock2.java)<br>

### 01/09/2021
[1143. 最长公共子序列](src/main/java/org/rongjoker/longest/LongestCommonSubSequence1143.java)<br>


### 01/10/2021
[62. 不同路径](src/main/java/org/rongjoker/dp/distinct/UniquePaths.java)<br>
[300. 最长递增子序列](src/main/java/org/rongjoker/longest/LongestIncreasingSubSequence300.java)<br>
[5. 最长回文子串](src/main/java/org/rongjoker/longest/LongestPalindromicSubstring5.java)<br>
[123. 买卖股票的最佳时机 III](src/main/java/org/rongjoker/dp/stock/BestTimeToBuyAndSellStock3.java)<br>

### 01/11/2021
[746. 使用最小花费爬楼梯](src/main/java/org/rongjoker/dp/target/MinCostClimbingStairs.java)<br>
[64. 最小路径和](src/main/java/org/rongjoker/dp/target/MinimumPathSum.java)<br>
[322. 零钱兑换(本质是充分+完全背包)](src/main/java/org/rongjoker/dp/coin/CoinChange322.java)<br>
[931. 下降路径最小和(dp+滑动窗口最大值才是最优解,待update)](src/main/java/org/rongjoker/dp/target/MinimumFallingPathSum.java)<br>
[983. 最低票价(其实就是完全背包套了一层皮)](src/main/java/org/rongjoker/dp/coin/MinimumCostForTickets.java)<br>
以上全部是dp，今日结束后开启dp和greedy双刷

### 01/12/2021
[455. 分发饼干(greedy;leetcode抵达30题)](src/main/java/org/rongjoker/greedy/AssignCookies.java)<br>
[55. 跳跃游戏(greedy经典题目,可以从后向前遍历进一步优化时空)](src/main/java/org/rongjoker/greedy/JumpGame.java)<br>
[452. 用最少数量的箭引爆气球(greedy;利用区间不重叠优化代码和性能)](src/main/java/org/rongjoker/greedy/MinimumNumberOfArrowsToBurstBalloons.java)<br>

### 01/13/2021
chrome的ublock origin 插件添加一个filter:[https://assets.leetcode-cn.com/lccn-resources/cn.js](https://assets.leetcode-cn.com/lccn-resources/cn.js) ,即可浏览英文版leetcode<br>
[多重背包,利用二进制的思路转化为01背包,时间复杂度降低到O(V*sum(logc))](src/main/java/org/rongjoker/dp/pack/PackageMultiple.java)<br>
[分组背包,理解后较为精彩](src/main/java/org/rongjoker/dp/pack/PackageGroup.java)<br>
[1155. 掷骰子的N种方法(参考分组背包,最近做的最精彩的dp)](src/main/java/org/rongjoker/dp/distinct/NumberOfDiceRollsWithTargetSum.java)<br>
有知乎大佬称leetcode为小学奥数，把一个题目的多种解法比作孔乙己的回字的几种写法。大骇。<br>

### 01/14/2021
开始复习滑动窗口(sw)+双指针(tp)+快慢指针(fsp),这类题目比较冷门，不过可以练习对hash和队列之类数据结构的巧妙掌握和诡谲应用<br>
[滑动窗口最小子串(sw)](src/main/java/org/rongjoker/sw/ShortestSubString.java)<br>
[219. 存在重复元素 II(sw入门,典型的利用hashset做滑动窗口的题目)](src/main/java/org/rongjoker/sw/ContainsDuplicateIi.java)<br>
[209. 长度最小的子数组(tp入门,后续可以优化O(logn)的二分算法)](src/main/java/org/rongjoker/sw/MinimumSizeSubArraySum209.java)<br>
[141. 环形链表(fsp入门,快慢指针在两圈以内一定会相遇,可以用数学归纳法证明)](src/main/java/org/rongjoker/sw/LinkedListCycle.java)<br>


### 01/15/2021
[494. 目标和(dp+hash;可以用dfs解决)](src/main/java/org/rongjoker/dp/distinct/targetSum.java)<br>
[15. 三数之和(tp经典题目;排序+双指针实现去重和O(n))](src/main/java/org/rongjoker/sw/Sum3.java)<br>
[3. 无重复字符的最长子串(sw;利用并查集可以进一步优化时空待后续)](src/main/java/org/rongjoker/sw/LongestSubstringWithoutRepeatingCharacters.java)<br>
排序在双指针和贪心算法等问题中往往是预操作；"连续子数组"暗示使用滑动窗口


### 01/19/2021
[11. 盛最多水的容器(tp;求面积最大,类似快速排序的思路)](src/main/java/org/rongjoker/sw/ContainerWithMostWater.java)<br>

### 01/20/2021
开始复习并查集(ds)、深度(dfs)和广度(bfs)，几个月不接触，已经生疏了<br>
[并查集按照rank优化(ds)](src/main/java/org/rongjoker/ds/DisjointSetRank.java)<br>

### 01/25/2021
[200. 岛屿数量(dfs)](src/main/java/org/rongjoker/ds/NumberOfIslands200dfs.java)<br>
[200. 岛屿数量(bfs)](src/main/java/org/rongjoker/ds/NumberOfIslands200bfs.java)<br>

### 01/26/2021
[200. 岛屿数量(ds)](src/main/java/org/rongjoker/ds/NumberOfIslands200ds.java)<br>
[547. 省份数量(ds入门)](src/main/java/org/rongjoker/ds/NumberOfProvinces547ds.java)<br>

### 01/27/2021
[56. 合并区间](src/main/java/org/rongjoker/merge/MergeIntervals.java)<br>
[206. 反转链表(迭代和递归)](src/main/java/org/rongjoker/list/ReverseLinkedList206.java)<br>
[92. 反转链表 II](src/main/java/org/rongjoker/list/ReverseLinkedList92.java)<br>


### 01/28/2021
[1414. 和为 K 的最少斐波那契数字数目(greedy)](src/main/java/org/rongjoker/greedy/FindMinFibonacciNumbers1414.java)<br>

### 02/04/2021
[剑指 Offer 62. 圆圈中最后剩下的数字(约瑟夫换:线性表+链表)](src/main/java/org/rongjoker/list/LastRemainingNumber.java)<br>
[637. 二叉树的层平均值(优化后的方法天秀)](src/main/java/org/rongjoker/binarytree/AverageOfLevels637.java)<br>
[102. 二叉树的层序遍历](src/main/java/org/rongjoker/binarytree/LevelOrder102.java)<br>
[94. 二叉树的中序遍历(递归和栈)](src/main/java/org/rongjoker/binarytree/InorderTraversal94.java)<br>
[144. 二叉树的前序遍历(递归和栈)](src/main/java/org/rongjoker/binarytree/PreorderTraversal144.java)<br>
[145. 二叉树的后序遍历(递归和栈)](src/main/java/org/rongjoker/binarytree/PostorderTraversal145.java)<br>
[1339. 分裂二叉树的最大乘积(考试题目,刷至50题)](src/main/java/org/rongjoker/binarytree/MaximumProductOfSplittedBinaryTree1339.java)<br>


### 02/05/2021
[377. 组合总和 Ⅳ (dp题目,本质是完全背包,几分钟bug free)](src/main/java/org/rongjoker/dp/distinct/CombinationSum4_377.java)<br>


### 02/08/2021
[684. 冗余连接(ds)](src/main/java/org/rongjoker/ds/RedundantConnection684.java)<br>
[226. 翻转二叉树](src/main/java/org/rongjoker/binarytree/InvertBinaryTree226.java)<br>
[98. 验证二叉搜索树(好题目,方法三结合了数学思维)](src/main/java/org/rongjoker/binarytree/ValidateBinarySearchTree98.java)<br>
[529. 扫雷游戏(dfs,比较稀碎的一个题目)](src/main/java/org/rongjoker/ds/Minesweeper.java)<br>



### 02/09/2021
[279. 完全平方数(dp,完全背包的变形;也可用数学四平方定理)](src/main/java/org/rongjoker/dp/target/PerfectSquares279.java)<br>
[416. 分割等和子集(dp,01充分背包的变形)](src/main/java/org/rongjoker/dp/distinct/PartitionEqualSubsetSum416.java)<br>
[1046. 最后一块石头的重量I(greedy,大顶堆)](src/main/java/org/rongjoker/greedy/LastStoneWeight1046.java)<br>
[1049. 最后一块石头的重量II(dp,01背包的变形)](src/main/java/org/rongjoker/dp/target/LastStoneWeight1049.java)<br>
[63. 不同路径II(dp;春节前最后一个题目,达成60)](src/main/java/org/rongjoker/dp/distinct/UniquePaths63.java)<br>



### 02/18/2021
[剑指 Offer 40. 最小的k个数(heap,堆的变形应用)](src/main/java/org/rongjoker/heap/LeastNumbers40.java)<br>
[347. 前 K 个高频元素(heap)](src/main/java/org/rongjoker/heap/TopKFrequent347.java)<br>
[215. 数组中的第K个最大元素(heap)](src/main/java/org/rongjoker/heap/FindKthLargest215.java)<br>
[295. 数据流的中位数(双堆)](src/main/java/org/rongjoker/heap/MedianFinder295.java)<br>
[704. 二分查找(bs)](src/main/java/org/rongjoker/binarysearch/BinarySearch704.java)<br>
[263. 丑数(maths)](src/main/java/org/rongjoker/maths/UglyNumber263.java)<br>


### 02/19/2021
[349. 两个数组的交集(tp)](src/main/java/org/rongjoker/sw/IntersectionOfTwoArrays349.java)<br>
[518. 零钱兑换 II(dp,反向操作避开顺序重复问题)](src/main/java/org/rongjoker/dp/coin/CoinChange518.java)<br>
[343. 整数拆分(dp,结合数学的题目)](src/main/java/org/rongjoker/dp/target/IntegerBreak343.java)<br>

### 02/22/2021
[三阶&二阶魔方](src/main/java/org/rongjoker/cube/aaa.cube.info.md)<br>


### 02/25/2021
[34. 在排序数组中查找元素的第一个和最后一个位置(bs)](src/main/java/org/rongjoker/binarysearch/SearchRange34.java)<br>
[287. 寻找重复数(bs)](src/main/java/org/rongjoker/binarysearch/FindDuplicate287.java)<br>


### 03/02/2021
[303. 区域和检索 - 数组不可变(ps,前缀和入门题目)](src/main/java/org/rongjoker/ps/NumArray303.java)<br>
[560. 和为K的子数组(ps+hash,前缀和经典题目)](src/main/java/org/rongjoker/ps/SubArraySum560.java)<br>


### 03/04/2021
[46. 全排列(bt,回溯法入门题目)](src/main/java/org/rongjoker/bt/Permutations46.java)<br>
[47. 全排列 II(bt,回溯法+去重剪枝)](src/main/java/org/rongjoker/bt/permuteUnique47.java)<br>
<br>
回溯法和动态规划的区别在于，动态规划是用来验证最优解的效率，回溯法是找出最优化的具体解法。


### 03/05/2021
[39. 组合总和(bt,回溯法+剪枝)](src/main/java/org/rongjoker/bt/CombinationSum39.java)<br>
[40. 组合总和 II(bt,回溯法+去重剪枝)](src/main/java/org/rongjoker/bt/CombinationSum40.java)<br>
[77. 组合(bt,回溯法组合入门)](src/main/java/org/rongjoker/bt/Combinations77.java)<br>
[93. 复原 IP 地址(bt,回溯法应用题)](src/main/java/org/rongjoker/bt/RestoreIpAddresses93.java)<br>


### 03/07/2021
[22. 括号生成 (bt,回溯法巧妙的应用题;抵达80题)](src/main/java/org/rongjoker/bt/GenerateParenthesis22.java)<br>
[17. 电话号码的字母组合 (bt,回溯法应用题)](src/main/java/org/rongjoker/bt/LetterCombinations17.java)<br>


### 03/08/2021
[1277. 统计全为 1 的正方形子矩阵(dp)](src/main/java/org/rongjoker/dp/target/CountSquares1277.java)<br>
[221. 最大正方形(dp)](src/main/java/org/rongjoker/dp/target/MaximalSquare221.java)<br>


### 03/10/2021
[78. 子集 (bt,两次递归实现无重复回溯)](src/main/java/org/rongjoker/bt/Subsets78.java)<br>
[90. 子集 II (bt,处理带重复数据回溯)](src/main/java/org/rongjoker/bt/SubsetsWithDup90.java)<br>


### 03/14/2021
[剑指 Offer 42. 连续子数组的最大和 (dp,同53题)](src/main/java/org/rongjoker/dp/target/MaxSubArray42.java)<br>
[1213. 三个有序数组的交集 (tp,注意越界)](src/main/java/org/rongjoker/sw/ArraysIntersection1213.java)<br>
[213. 打家劫舍 II(dp)](src/main/java/org/rongjoker/dp/target/HouseRobber213.java)<br>
[84. 柱状图中最大的矩形(stack,单调栈解决蛮力的典型)](src/main/java/org/rongjoker/stack/LargestRectangleArea84.java)<br>
[85. 最大矩形(stack,84的迭代,抵达90)](src/main/java/org/rongjoker/stack/MaximalRectangle85.java)<br>


### 03/22/2021
[887. 鸡蛋掉落(dp+bs,经典而又困难的dp二分题目)](src/main/java/org/rongjoker/dp/target/SuperEggDrop887.java)<br>
[191. 位1的个数(bit,位运算入门题目)](src/main/java/org/rongjoker/bit/HammingWeight191.java)<br>

### 03/23/2021
[112. 路径总和(dfs,dfs入门题目)](src/main/java/org/rongjoker/ds/pathSum112.java)<br>
[113. 路径总和 II(dfs/bt)](src/main/java/org/rongjoker/ds/pathSum113.java)<br>


### 03/24/2021
[9. 回文数(math,数学的解法更快)](src/main/java/org/rongjoker/maths/PalindromeNumber9.java)<br>
[101. 对称二叉树(dfs)](src/main/java/org/rongjoker/ds/SymmetricTree101.java)<br>
[111. 二叉树的最小深度(dfs)](src/main/java/org/rongjoker/ds/MinimumDepthOfBinaryTree111.java)<br>
[131. 分割回文串(dfs,bt;回溯算法是dfs重要的分支)](src/main/java/org/rongjoker/ds/PalindromePartitioning131.java)<br>
[257. 二叉树的所有路径(dfs,bt;二叉树的回溯计算)](src/main/java/org/rongjoker/ds/BinaryTreePaths257.java)<br>


### 03/25/2021
[337. 打家劫舍 III(dfs+dp;树的动态规划;抵达100题)](src/main/java/org/rongjoker/ds/HouseRobber337.java)<br>
[51. N 皇后(bt;回溯算法最经典+最难的题目)](src/main/java/org/rongjoker/bt/Nqueens51.java)<br>


### 03/26/2021
[83. 删除排序链表中的重复元素(list;经典递归题目)](src/main/java/org/rongjoker/list/DeleteDuplicates83.java)<br>
[203. 移除链表元素(list;链表递归入门题目)](src/main/java/org/rongjoker/list/RemoveLinkedListElements203.java)<br>
[82. 删除排序链表中的重复元素 II(list;83+203的结合)](src/main/java/org/rongjoker/list/DeleteDuplicates82.java)<br>
[2. 两数相加(list;递归经典题目)](src/main/java/org/rongjoker/list/AddTwoNumbers2.java)<br>
[14. 最长公共前缀(str;字符串经典题目)](src/main/java/org/rongjoker/str/LongestCommonPrefix14.java)<br>
[139. 单词拆分(dp;字符串切割为充分背包题目)](src/main/java/org/rongjoker/dp/decide/WordBreak139.java)<br>
[718. 最长重复子数组(dp;01充分背包题目)](src/main/java/org/rongjoker/longest/MaximumLengthOfRepeatedSubarray718.java)<br>
[325. 和等于 k 的最长子数组长度(ps+hash;前缀和560题进阶题目)](src/main/java/org/rongjoker/ps/MaxSubArrayLen325.java)<br>


### 03/27/2021
[19. 删除链表的倒数第 N 个结点(list;双指针实现按下标操作链表)](src/main/java/org/rongjoker/list/RemoveNthFromEnd19.java)<br>
[61. 旋转链表(list;双指针实现按下标操作链表)](src/main/java/org/rongjoker/list/RotateList61.java)<br>
[189. 旋转数组(array;数组就地反转计算)](src/main/java/org/rongjoker/list/RotateArray189.java)<br>
[409. 最长回文串(hash)](src/main/java/org/rongjoker/str/RotateArray189.java)<br>
[516. 最长回文子序列(dp;区间dp的入门+经典题目)](src/main/java/org/rongjoker/longest/LongestPalindromeSubseq516.java)<br>

### 03/28/2021
[173. 二叉搜索树迭代器(创建迭代器)](src/main/java/org/rongjoker/binarytree/BSTIterator.java)<br>
[剑指 Offer 48. 最长不含重复字符的子字符串(sw;和3是同题)](src/main/java/org/rongjoker/longest/LengthOfLongestSubstring48.java)<br>
[20. 有效的括号(str;stack经典题目;抵达120题)](src/main/java/org/rongjoker/str/ValidParentheses20.java)<br>
[91. 解码方法(dp)](src/main/java/org/rongjoker/dp/distinct/DecodeWays91.java)<br>
[639. 解码方法 II(dp;91的进阶,long->int防止溢出)](src/main/java/org/rongjoker/dp/distinct/DecodeWays639.java)<br>
[225. 用队列实现栈(stack;设计题目)](src/main/java/org/rongjoker/stack/MyStack.java)<br>


### 03/29/2021
[232. 用栈实现队列(stack;设计题目;设计题目;较巧妙)](src/main/java/org/rongjoker/stack/MyQueue.java)<br>
[146. LRU 缓存机制(list;手写双向链表)](src/main/java/org/rongjoker/stack/LRUCache.java)<br>


### 03/30/2021
[74. 搜索二维矩阵(bs;二分查找在矩阵的应用,包含二分的所有注意事项)](src/main/java/org/rongjoker/binarysearch/SearchMatrix74.java)<br>

516的
等价版: #1143
简单版: #718
进阶版: #10


79. 单词搜索
69. x 的平方根


### @todo

stack题目
232. 用栈实现队列
901. 股票价格跨度

dp题目
309. 最佳买卖股票时机含冷冻期
32. 最长有效括号(最长系列，困难)
42. 接雨水
72. 编辑距离
10. 正则表达式匹配

滑动窗口
239. 滑动窗口最大值
485. 最大连续 1 的个数 系列
978. 最长湍流子数组
76. 最小覆盖子串


224. 基本计算器

dfs
394. 字符串解码


位运算
136. 只出现一次的数字


拓扑排序
207. 课程表

贪心
134. 加油站
     

474. 一和零

[650. 只有两个键的键盘](src/main/java/org/rongjoker/dp/target/MinCostClimbingStairs.java)<br>


