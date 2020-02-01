package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

// BFS
class Solution {
    public static boolean isCousins(TreeNode root, int x, int y) {
        if(root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode xParent = null, yParent = null;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                    if(node.left.val == x) xParent = node;
                    if(node.left.val == y) yParent = node;
                }
                if(node.right != null){
                    queue.offer(node.right);
                    if(node.right.val == x) xParent = node;
                    if(node.right.val == y) yParent = node;
                }
                --size;
                if(xParent != null && yParent != null) break;
            }
            if(xParent != null && yParent != null) return xParent != yParent;
            if((xParent != null && yParent == null) ||
                    (xParent == null && yParent != null)) return false;

        }
        return false;
    }
}

// DFS
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        return findDepth(root,x,1) == findDepth(root,y,1) && !isSibling(root,x,y);
    }

    private boolean isSibling(TreeNode node, int x, int y) {
        if(node == null) return false;

        boolean check = false;
        if(node.left != null && node.right != null){
            check = (node.left.val == x && node.right.val == y) ||
                    (node.left.val == y && node.right.val == x);
        }
        return check || isSibling(node.left, x, y) || isSibling(node.right, x, y);
    }

    private int findDepth(TreeNode node, int val, int height) {
        if(node == null) return 0;
        if(node.val == val) return height;

        return findDepth(node.left, val, height + 1) |
                findDepth(node.right, val, height + 1);
    }
}