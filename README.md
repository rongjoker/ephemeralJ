# ephemeralJ
Ephemeral codes,permanent thoughts;<br>
兄弟会背叛你，女人会离开你，金钱会诱惑你，生活会刁难你，只有算法不会，不会就是不会，怎么学都不会。<br>


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
[62. 不同路径](src/main/java/org/rongjoker/dp/paths/UniquePaths.java)<br>
[300. 最长递增子序列](src/main/java/org/rongjoker/dp/longest/LongestIncreasingSubSequence.java)<br>
[5. 最长回文子串](src/main/java/org/rongjoker/dp/longest/LongestPalindromicSubstring.java)<br>
[123. 买卖股票的最佳时机 III](src/main/java/org/rongjoker/dp/stock/BestTimeToBuyAndSellStock3.java)<br>

### 01/11/2021
[746. 使用最小花费爬楼梯](src/main/java/org/rongjoker/dp/paths/MinCostClimbingStairs.java)<br>
[64. 最小路径和](src/main/java/org/rongjoker/dp/paths/MinimumPathSum.java)<br>
[322. 零钱兑换(本质是充分+完全背包)](src/main/java/org/rongjoker/dp/coin/CoinChange.java)<br>
[931. 下降路径最小和(dp+滑动窗口最大值才是最优解,待update)](src/main/java/org/rongjoker/dp/paths/MinimumFallingPathSum.java)<br>
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
[1155. 掷骰子的N种方法(参考分组背包,最近做的最精彩的dp)](src/main/java/org/rongjoker/dp/ways/NumberOfDiceRollsWithTargetSum.java)<br>
有知乎大佬称leetcode为小学奥数，把一个题目的多种解法比作孔乙己的回字的几种写法。大骇。<br>

### 01/14/2021
开始复习滑动窗口(sw)+双指针(tp)+快慢指针(fsp),这类题目比较冷门，不过可以练习对hash和队列之类数据结构的巧妙掌握和诡谲应用<br>
[滑动窗口最小子串(sw)](src/main/java/org/rongjoker/sw/ShortestSubString.java)<br>
[219. 存在重复元素 II(sw入门,典型的利用hashset做滑动窗口的题目)](src/main/java/org/rongjoker/sw/ContainsDuplicateIi.java)<br>
[209. 长度最小的子数组(tp入门,后续可以优化O(logn)的二分算法)](src/main/java/org/rongjoker/sw/MinimumSizeSubArraySum.java)<br>
[141. 环形链表(fsp入门,快慢指针在两圈以内一定会相遇,可以用数学归纳法证明)](src/main/java/org/rongjoker/sw/LinkedListCycle.java)<br>


### 01/15/2021
[494. 目标和(dp+hash;可以用dfs解决)](src/main/java/org/rongjoker/dp/ways/targetSum.java)<br>
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
[200. 岛屿数量(ds)](src/main/java/org/rongjoker/ds/NumberOfIslands200ds.java)<br>

### Todo
[56. 合并区间](src/main/java/org/rongjoker/merge/MergeIntervals.java)<br>
[142. 环形链表 II(fsp)](src/main/java/org/rongjoker/sw/LinkedListCycle.java)<br>
[688. “马”在棋盘上的概率(dp)](src/main/java/org/rongjoker/dp/ways/KnightProbabilityInChessboard.java)<br>
[435. 无重叠区间](src/main/java/org/rongjoker/greedy/NonOverlappingIntervals.java)<br>
[406. 根据身高重建队列](src/main/java/org/rongjoker/greedy/QueueReconstructionByHeight.java)<br>
