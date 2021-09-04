package org.rongjoker.contest.biweekly60;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LockingTree {

    int[] tree;
    Map<Integer,Integer> lock_user;


    Map<Integer,ArrayList<Integer>> map;


    public LockingTree(int[] parent) {
        tree = parent;
        int len = parent.length;
        lock_user = new HashMap<>();
        map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if(!map.containsKey(parent[i]))map.put(parent[i],new ArrayList());
            map.get(parent[i]).add(i);
        }

    }

    public boolean lock(int num, int user) {
        if(lock_user.containsKey(num))return false;
        else {
            lock_user.put(num,user);
            return true;
        }

    }

    public boolean unlock(int num, int user) {
        Integer lock_u = lock_user.get(num);
        if(lock_u!=null && lock_u.equals(user)){
            lock_user.remove(num);
            return true;
        }else return false;

    }

    public void unl(int index){
        lock_user.remove(index);
        ArrayList<Integer> children = map.get(index);
        if(children==null)return;
        for (Integer child : children) {
            lock_user.remove(child);
            unl((child));
        }
    }

    public boolean check(int index){

        ArrayList<Integer> children = map.get(index);
        if(children==null)return false;
        for (Integer child : children) {
            if(lock_user.containsKey(child) || check(child))return true;

        }
        return false;
    }

    public boolean backtracking(int index){

        if(index>=0){
            return !lock_user.containsKey(index) && backtracking(tree[index]);
        }return true;
    }



    public boolean upgrade(int num, int user) {
        if(!lock_user.containsKey(num) && check(num) && backtracking(num)){
            ArrayList<Integer> children = map.get(num);
            for (Integer child : children) unl(child);
            lock_user.put(num,user);
            return true;

        }else return false;



    }

    public static void main(String[] args) {
        LockingTree lockingTree = new LockingTree(new int[]{-1, 0, 0, 1, 1, 2, 2});
        System.out.println(lockingTree.lock(2, 2));    // 返回 true ，因为节点 2 未上锁。
        // 节点 2 被用户 2 上锁。
        System.out.println(lockingTree.unlock(2, 3));  // 返回 false ，因为用户 3 无法解锁被用户 2 上锁的节点。
        System.out.println( lockingTree.unlock(2, 2));  // 返回 true ，因为节点 2 之前被用户 2 上锁。
        // 节点 2 现在变为未上锁状态。
        System.out.println(lockingTree.lock(4, 5));    // 返回 true ，因为节点 4 未上锁。
        // 节点 4 被用户 5 上锁。
        System.out.println(lockingTree.upgrade(0, 1)); // 返回 true ，因为节点 0 未上锁且至少有一个被上锁的子孙节点（节点 4）。
        // 节点 0 被用户 1 上锁，节点 4 变为未上锁。
        System.out.println(lockingTree.lock(0, 1));    // 返回 false ，因为节点 0 已经被上锁了。


    }

}
