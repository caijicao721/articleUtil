package com.cao.article;

import java.io.FileInputStream;
import java.io.FilterOutputStream;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;

/**
 * ClassName: MyTest
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/5 16:03
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        int ans = new Main().getLatestRightestNode(head).val;
        System.out.println(ans);
    }


    public TreeNode getLatestRightestNode(TreeNode head){
        Result result = get(new Result(head,0));
        return result.node;
    }

    public Result get(Result result){
        TreeNode head = result.node;
        int height = result.height;
        if (head.left==null&&head.right==null){
            return new Result(head,height);
        }
        // 左子树为空说明就是该节点
        if (head.left == null){
            return get(new Result(head,height));
        }
        if (head.right == null){
            return get(new Result(head.left,height+1));
        }
        Result left = get(new Result(head.left,height+1));
        //System.out.println(left.node.val);
        Result right = get(new Result(head.right,height+1));
        //System.out.println(right.node.val);
        if (right.height>=left.height){
            return right;
        }else{
            return left;
        }

    }

}
class Result{
    TreeNode node;
    int height;
    Result(TreeNode node,int height){
        this.node = node;
        this.height = height;
    }
}


class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(){

    }

    TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }


}
