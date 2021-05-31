package org.rongjoker.contest.week243;

import org.junit.Test;

import java.util.*;

public class Test3 {

    //[74,57,61,82,67,97,67,21,61,79,21,50,14,88,48,52,76,64]
    //[21,100,48,64,20,8,28,10,3,63,7]

    @Test
    public void test3() {

//        System.out.println(Arrays.toString(assignTasks(new int[]{3, 3, 2}, new int[]{1, 2, 3, 2, 1, 2})));
//        System.out.println(Arrays.toString(assignTasks(new int[]{5,1,4,3,2}, new int[]{2,1,2,4,5,2,1})));
//        System.out.println(Arrays.toString(assignTasks(new int[]{74,57,61,82,67,97,67,21,61,79,21,50,14,88,48,52,76,64}, new int[]{21,100,48,64,20,8,28,10,3,63,7})));
//[12,7,10,14,11,15,1,2,8,17,4]
        //[12,10,7,14,11,15,1,8,2,17,4]
        int[] s = {338, 890, 301, 532, 284, 930, 426, 616, 919, 267, 571, 140, 716, 859, 980, 469, 628, 490, 195, 664, 925, 652, 503, 301, 917, 563, 82, 947, 910, 451, 366, 190, 253, 516, 503, 721, 889, 964, 506, 914, 986, 718, 520, 328, 341, 765, 922, 139, 911, 578, 86, 435, 824, 321, 942, 215, 147, 985, 619, 865};
        int[] t = {773, 537, 46, 317, 233, 34, 712, 625, 336, 221, 145, 227, 194, 693, 981, 861, 317, 308, 400, 2, 391, 12, 626, 265, 710, 792, 620, 416, 267, 611, 875, 361, 494, 128, 133, 157, 638, 632, 2, 158, 428, 284, 847, 431, 94, 782, 888, 44, 117, 489, 222, 932, 494, 948, 405, 44, 185, 587, 738, 164, 356, 783, 276, 547, 605, 609, 930, 847, 39, 579, 768, 59, 976, 790, 612, 196, 865, 149, 975, 28, 653, 417, 539, 131, 220, 325, 252, 160, 761, 226, 629, 317, 185, 42, 713, 142, 130, 695, 944, 40, 700, 122, 992, 33, 30, 136, 773, 124, 203, 384, 910, 214, 536, 767, 859, 478, 96, 172, 398, 146, 713, 80, 235, 176, 876, 983, 363, 646, 166, 928, 232, 699, 504, 612, 918, 406, 42, 931, 647, 795, 139, 933, 746, 51, 63, 359, 303, 752, 799, 836, 50, 854, 161, 87, 346, 507, 468, 651, 32, 717, 279, 139, 851, 178, 934, 233, 876, 797, 701, 505, 878, 731, 468, 884, 87, 921, 782, 788, 803, 994, 67, 905, 309, 2, 85, 200, 368, 672, 995, 128, 734, 157, 157, 814, 327, 31, 556, 394, 47, 53, 755, 721, 159, 843};

//        int[] rig = {26, 50, 47, 11, 56, 31, 18, 55, 32, 9, 4, 2, 23, 53, 43, 0, 44, 30, 6, 51, 29, 51, 15, 17, 22, 34, 38, 33, 42, 3, 25, 10, 49, 51, 7, 58, 16, 21, 19, 31, 19, 12, 41, 35, 45, 52, 13, 59, 47, 36, 1, 28, 48, 39, 24, 8, 46, 20, 5, 54, 27, 37, 14, 57, 40, 59, 8, 45, 4, 51, 47, 7, 58, 4, 31, 23, 54, 7, 9, 56, 2, 46, 56, 1, 17, 42, 11, 30, 12, 44, 14, 32, 7, 10, 23, 1, 29, 27, 6, 10, 33, 24, 19, 10, 35, 30, 35, 10, 17, 49, 50, 36, 29, 1, 48, 44, 7, 11, 24, 57, 42, 30, 10, 55, 3, 20, 38, 15, 7, 46, 32, 21, 40, 16, 59, 30, 53, 17, 18, 22, 51, 11, 53, 36, 57, 26, 5, 56, 36, 55, 31, 34, 57, 7, 52, 37, 31, 10, 0, 51, 41, 2, 32, 25, 0, 7, 49, 47, 13, 14, 24, 57, 28, 4, 45, 43, 39, 38, 8, 2, 44, 45, 29, 25, 25, 12, 54, 5, 44, 30, 27, 23, 26, 7, 33, 58, 41, 25, 52, 40, 58, 9, 52, 40};
        int[] wrong = assignTasks(s, t);
        int[] rig = assignTasks2(s, t);
        int len = rig.length;
        for (int i = 0; i < len; i++) {
            if (rig[i] != wrong[i]) {
                System.out.println(i + ":" + rig[i] + "(" + s[rig[i]] + "):" + wrong[i] + "(" + s[wrong[i]] + ")");
            }
        }

    }

    static class TaskServer {

        public TaskServer(int index, int delay) {
            this.index = index;
            this.delay = delay;
        }

        int index;
        int delay;
    }


    /**
     * 思路是一样的，只是应该跳过时间,而不是轮询秒，比第二种更慢
     * @param servers
     * @param tasks
     * @return
     */
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length, m = tasks.length;

        int[] ans = new int[m];

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            if (servers[o1] != servers[o2]) return servers[o1] - servers[o2];
            else return o1 - o2;
        });

        for (int i = 0; i < n; i++) queue.add(i);

        PriorityQueue<TaskServer> queue2 = new PriorityQueue<>(Comparator.comparingInt(o -> o.delay));

        Deque<Integer> jobs = new LinkedList<>();


        for (int i = 0; i < m; i++) {
            jobs.addLast(i);


            while (!queue2.isEmpty() && queue2.peek().delay <= i) {
                int id = queue2.poll().index;
                queue.offer(id);
            }

            while (!queue.isEmpty() && !jobs.isEmpty()) {
                int poll = queue.poll();
                int job = jobs.pollFirst();
                ans[job] = poll;
                queue2.offer(new TaskServer(poll, tasks[job] + i));
            }


        }


        int index = m;

        while (!jobs.isEmpty()) {

            while (!queue2.isEmpty() && queue2.peek().delay <= index) {
                int id = queue2.poll().index;
                queue.offer(id);
            }

            while (!queue.isEmpty() && !jobs.isEmpty()) {

                int poll = queue.poll();
                int job = jobs.pollFirst();

                ans[job] = poll;


                queue2.offer(new TaskServer(poll, tasks[job] + index));
            }

            if(queue.isEmpty()){
                index = queue2.peek().delay;
            }else index++;



        }

        return ans;

    }


    public int[] assignTasks2(int[] servers, int[] tasks) {

        int n = servers.length, m = tasks.length;

        int[] ans = new int[m];

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            if (servers[o1] != servers[o2]) return servers[o1] - servers[o2];
            else return o1 - o2;
        });

        for (int i = 0; i < n; i++) queue.offer(i);

        PriorityQueue<TaskServer> queue2 = new PriorityQueue<>(Comparator.comparingInt(o -> o.delay));

        int dey = 0;
        for (int i = 0; i < m;) {

            while (!queue2.isEmpty() && queue2.peek().delay <= dey) queue.offer(queue2.poll().index);

            while (!queue.isEmpty()&&i<m && dey >= i) {

                int poll = queue.poll();
                ans[i] = poll;
                queue2.offer(new TaskServer(poll, tasks[i] + dey));
                i++;
            }

            if(queue.isEmpty())dey = queue2.peek().delay;
            else dey++;

        }


        return ans;

    }



}
