package org.rongjoker.contest.biweekly52;

public class Test3 {

    public char[][] rotateTheBox(char[][] box) {

        int m = box.length, n = box[0].length;
        int index;
        char[][] ans = new char[n][m];
        for (int i = 0; i < m; i++) {
            index = n;
            for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == '*') {
                    index = j;
                    ans[j][m - 1 - i] = box[i][j];
                }
                else if (box[i][j] == '#') {
                    if (index == j + 1) {
                        index = j;
                        ans[j][m - 1 - i] = box[i][j];
                    }
                    else {
                        box[i][index - 1] = '#';
                        box[i][j] = '.';

                        ans[index - 1][m - 1 - i] = '#';
                        ans[j][m - 1 - i] = '.';

                        --index;
                    }
                }else ans[j][m - 1 - i] = box[i][j];

            }
        }

        return ans;

    }
}
