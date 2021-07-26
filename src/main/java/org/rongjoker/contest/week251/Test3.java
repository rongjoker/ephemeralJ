package org.rongjoker.contest.week251;

import org.junit.Test;

public class Test3 {

    public int maxCompatibilitySum2(int[][] students, int[][] mentors) {
        int m = students.length,n = students[0].length;
        int[][] dp = new int[m][m];
        for(int i=0;i<m;++i){
            for(int j=0;j<m;++j){
                int temp=0;
                for(int k =0;k<n;k++){
                    if(students[i][k]==mentors[j][k]){
                        temp++;
                    }
                }
                dp[i][j] = temp;
            }
        }

        track(dp,0,0,m,0);

        return ans;
    }

    int ans = 0;


    public void  track(int[][] dp,int l,int r,int m,int cur){
        if(l==m){
            ans = Math.max(cur,ans);
            return;
        }

        for(int i=l;i<m;++i){
            for(int j=r;j<m;++j){
                cur+= dp[i][j];
                track(dp,i+1,j+1,m,cur);
                cur-= dp[i][r];
            }
        }
    }








    @Test
    public void test3(){
        
        //[[1,1,1},{0,0,1},{0,0,1},{0,1,0]]
        //[[1,0,1},{0,1,1},{0,1,0},{1,1,0]]

        System.out.println(maxCompatibilitySum2(new int[][]{{
                1,1,1},{0,0,1},{0,0,1},{0,1,0
                }},
                new int[][]{{
                        1,0,1},{0,1,1},{0,1,0},{1,1,0
                }}));//9

//        System.out.println(maxCompatibilitySum(new int[][]{{
//                0,1,0,1,1,1},{1,0,0,1,0,1},{1,0,1,1,0,0
//                }},
//                new int[][]{{
//                        1,0,0,0,0,1},{0,1,0,0,1,1},{0,1,0,0,1,1
//                }}));
//
//
//
//        System.out.println(maxCompatibilitySum(new int[][]{{
//                0,0},{0,0},{0,0
//        }},
//                new int[][]{{
//                        1,1},{1,1},{1,1
//                }}));
//
//        System.out.println(maxCompatibilitySum(new int[][]{{1,1,0},{1,0,1},{0,0,1}},
//                new int[][]{{
//                        1,0,0},{0,0,1},{1,1,0
//                }}));

    }

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length,n = students[0].length;
        boolean[][] visited = new boolean[m][2];//0为学生，1为老师
        int count = n;
        int ans = 0;
        int total=0;
        flag:while (count>0){

            one:for(int i=0;i<m;++i){
                if(visited[i][0])continue;
                for(int j=0;j<m;++j){
                    if(visited[j][1])continue;
                    int temp=0;
                    for(int k =0;k<n;k++){
                        if(students[i][k]==mentors[j][k]){
                            temp++;
                        }
                    }
                        if(temp==count){
                            visited[i][0] = true;
                            visited[j][1] = true;
                            System.out.println(i+":"+j);
                            ans+=temp;
                            total++;
                            if(total==m)break flag;
                            continue one;
                        }

                }
            }
            count--;
        }

        return ans;

    }
}
