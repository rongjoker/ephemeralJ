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

### 01/05/2021
[各种背包](src/main/java/org/rongjoker/dp/pack)

### 01/07/2021
[53. 最大子序和](src/main/java/org/rongjoker/dp/pack/HomeWork.java)<br>
[152. 乘积最大子数组](src/main/java/org/rongjoker/dp/pack/HomeWork.java)<br>

### 01/08/2021
[918. 环形子数组的最大和](src/main/java/org/rongjoker/dp/pack/HomeWork.java)<br>
[120. 三角形最小路径和](src/main/java/org/rongjoker/dp/pack/HomeWork.java)<br>
[198. 打家劫舍](src/main/java/org/rongjoker/dp/pack/HomeWork.java)<br>
[121. 买卖股票的最佳时机](src/main/java/org/rongjoker/dp/stock/BestTimeToBuyAndSellStock.java)<br>
[122. 买卖股票的最佳时机 II](src/main/java/org/rongjoker/dp/stock/BestTimeToBuyAndSellStock2.java)<br>

### 01/09/2021
[1143. 最长公共子序列](src/main/java/org/rongjoker/dp/longest/LongestCommonSubSequence.java)<br>


### 01/10/2021
[62. 不同路径](src/main/java/org/rongjoker/dp/distinct/UniquePaths.java)<br>
[300. 最长递增子序列](src/main/java/org/rongjoker/dp/longest/LongestIncreasingSubSequence.java)<br>
[5. 最长回文子串](src/main/java/org/rongjoker/dp/longest/LongestPalindromicSubstring.java)<br>
[123. 买卖股票的最佳时机 III](src/main/java/org/rongjoker/dp/stock/BestTimeToBuyAndSellStock3.java)<br>

### 01/11/2021
[746. 使用最小花费爬楼梯](src/main/java/org/rongjoker/dp/target/MinCostClimbingStairs.java)<br>
[64. 最小路径和](src/main/java/org/rongjoker/dp/target/MinimumPathSum.java)<br>
[322. 零钱兑换(本质是充分+完全背包)](src/main/java/org/rongjoker/dp/coin/CoinChange.java)<br>
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
[209. 长度最小的子数组(tp入门,后续可以优化O(logn)的二分算法)](src/main/java/org/rongjoker/sw/MinimumSizeSubArraySum.java)<br>
[141. 环形链表(fsp入门,快慢指针在两圈以内一定会相遇,可以用数学归纳法证明)](src/main/java/org/rongjoker/sw/LinkedListCycle.java)<br>


### 01/15/2021
[494. 目标和(dp+hash;可以用dfs解决)](src/main/java/org/rongjoker/dp/distinct/targetSum.java)<br>
[15. 三数之和(tp经典题目;排序+双指针实现去重和O(n))](src/main/java/org/rongjoker/sw/Sum3.java)<br>
[3. 无重复字符的最长子串(sw;利用并查集可以进一步优化时空待后续)](src/main/java/org/rongjoker/sw/Sum3.java)<br>
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

###
心得
我说句题外话，就是何时使用【回溯】，何时使用【动态规划】，用大白话说，就是：

首先看取值范围，递归回溯一维数组，100就是深度的极限了（何况本题是100²） 2.如果是求走迷宫的【路径】，必然是回溯；如果是走迷宫的【路径的条数】，必然是dp--------(这个竟然屡试不爽！！！！)

### @todo
221. 最大正方形
1240. Tiling a Rectangle with the Fewest Squares Hard

174. Dungeon Game Hard

871. Minimum Number of Refueling Stops Hard

[650. 只有两个键的键盘](src/main/java/org/rongjoker/dp/target/MinCostClimbingStairs.java)<br>



### @todo
399. 除法求值(弗洛伊德)
1223. 掷骰子模拟
[142. 环形链表 II(fsp)](src/main/java/org/rongjoker/sw/LinkedListCycle.java)<br>
[688. “马”在棋盘上的概率(dp)](src/main/java/org/rongjoker/dp/distinct/KnightProbabilityInChessboard.java)<br>
[435. 无重叠区间](src/main/java/org/rongjoker/greedy/NonOverlappingIntervals.java)<br>
[406. 根据身高重建队列](src/main/java/org/rongjoker/greedy/QueueReconstructionByHeight.java)<br>


### 并查集题目
「力扣」第 547 题：省份数量（中等）；
「力扣」第 684 题：冗余连接（中等）；
「力扣」第 1319 题：连通网络的操作次数（中等）；
「力扣」第 1631 题：最小体力消耗路径（中等）；
「力扣」第 959 题：由斜杠划分区域（中等）；
「力扣」第 1202 题：交换字符串中的元素（中等）；
「力扣」第 947 题：移除最多的同行或同列石头（中等）；
「力扣」第 721 题：账户合并（中等）；
「力扣」第 803 题：打砖块（困难）；
「力扣」第 1579 题：保证图可完全遍历（困难）;
「力扣」第 778 题：水位上升的泳池中游泳（困难）。