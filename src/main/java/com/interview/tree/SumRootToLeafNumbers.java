package com.interview.tree;

import java.util.*;

//Reference: https://www.youtube.com/watch?v=Gi0202QawRQ&list=PLIA-9QRQ0RqG6CNfSJSzT0h5Pc_HvwZG5&index=112&t=0s
//Derived question: find max number in all path(done) during sum make one variable maximum and keep update it. also find min number in all path(almost same as max sum path).
//Return path which has minimum path sum of all TreeNode in path(done), print all path(done), Root to leaf path sum equal to a given number(done) reference: https://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/,
//,Find all duplicate path, find unique path in tree if TreeNode is represented by character like A, B etc, check tree is sum tree where each TreeNode is sum of all its children sum Reference: http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree/,
//find all parent TreeNode which are parent of any child, find all cousin TreeNode, find all TreeNodes which parent are same, 
//find all root-to-leaf paths where each path's sum equals the given sum, 
public class SumRootToLeafNumbers {
    void dfs(TreeNode root,long val, long[] ans)
    {
        if(root == null)
            return;
        
        val = 10*val + root.val;
        //Here logic will change

        //Leaf TreeNode
        if(root.left == null && root.right == null)
        {
            ans[0]+=val; //Here logic will change
            return;
        }
        dfs(root.left,val,ans);
        dfs(root.right,val, ans);
    }
public long sumNumbers(TreeNode root) {
        if(root == null)
            return 0;
        
        long[] ans = new long[1];
        dfs(root,0, ans);
        return ans[0];
    }
//----------------------------------------------------------------
//Derived FindMaxNumberInAllPath
void maximumnSumOfPathUtil(TreeNode root,long val, long[] ans)
{
    if(root == null)
        return;
    
    val = 10*val + root.val;

    //Leaf TreeNode
    if(root.left == null && root.right == null)
    {
        if (ans[0] < val) {
            //Here logic will change
            ans[0] = val;
        }
        return;
    }
    maximumnSumOfPathUtil(root.left,val, ans);
    maximumnSumOfPathUtil(root.right,val, ans);
}
public long maximumnSumOfPath(TreeNode root) {
    if(root == null)
        return 0;
    long[] ans = new long[1];
    maximumnSumOfPathUtil(root,0, ans);
    return ans[0];
}
//----------------------------------------------------------------

//Derived question: Return path which has minimum path sum of all TreeNode in path
//It can be solved using find answer in integer then convert in staring then compute output but again parsing required
//Other method is not working so make as other one. To work it we need to remove from two places. Try next day.
//----------------------------------------------------------------
void pathWithMinimumSumUtil(TreeNode root,long val, long min, StringBuilder path, StringBuilder ans)
{
    if(root == null)
        return;
    
    val = 10*val + root.val;
    //Need to change logic
    //Trace the path
    if(path.toString().isEmpty()) {
        path.append(root.val);
    } else {
        path.append("->" + root.val);
    }
    

    //Leaf TreeNode
    if(root.left == null && root.right == null)
    {
        if (min > val) {
            //Here logic will change
            min = val;
            ans = path;
        }
        return;
    }
    pathWithMinimumSumUtil(root.left,val, min, path, ans);
    pathWithMinimumSumUtil(root.right,val, min, path, ans);
}
public String pathWithMinimumSum(TreeNode root) {
    if(root == null)
        return null;
    
    StringBuilder path = new StringBuilder();
    long min = Integer.MAX_VALUE;
    StringBuilder ans = new StringBuilder();
    
    pathWithMinimumSumUtil(root,0, min, path, ans);
    return ans.toString();
}

//----------------------------------------------------------------

//Derived question: Return path which has minimum path sum of all TreeNode in path
//It can be solved using find answer in integer then convert in staring then compute output but again parsing required
//Other method is not working so make as other one
//----------------------------------------------------------------
void printAllPathUtil(TreeNode root,int val, ArrayList<Integer> ans)
{
  if(root == null)
      return;
  
  val = 10*val + root.val;
  

  //Leaf TreeNode
  if(root.left == null && root.right == null)
  {
      ans.add(val);

      return;
  }
  printAllPathUtil(root.left,val, ans);
  printAllPathUtil(root.right,val, ans);
}
public ArrayList<Integer> printAllPath(TreeNode root) {
  if(root == null)
      return null;
  

  ArrayList<Integer> ans = new ArrayList<>();
  
  printAllPathUtil(root,0, ans);
  return ans;
}

//----------------------------------------------------------------

//----------------------------------------------------------------
//Derived FindMaxNumberInAllPath
void sumOfPathGivenNumberUtil(TreeNode root,long val, long givenNumber, long[] ans)
{
  if(root == null)
      return;
  
  val = 10*val + root.val;

  //Leaf TreeNode
  if(root.left == null && root.right == null)
  {
      if (givenNumber == val) {
          //Here logic will change
          ans[0] = val;
      }
      return;
  }
  sumOfPathGivenNumberUtil(root.left,val, givenNumber, ans);
  sumOfPathGivenNumberUtil(root.right,val, givenNumber, ans);
}
public long sumOfPathGivenNumber(TreeNode root, long givenNumber) {
  if(root == null)
      return 0;
  long[] ans = new long[1];
  sumOfPathGivenNumberUtil(root,0, givenNumber, ans);
  if (ans[0] != 0) {
      return ans[0]; 
  } else {
      return -1;
  }
  
}
//Reference: VVImp: https://leetcode.com/problems/path-sum-ii/ This problem is solved by two way
private void pathSumUtil(TreeNode root, int number, int sum, List<List<Integer>> result, List<Integer> currentPath) {
    if (root == null) {
        return;
    }
    number +=root.val;
    currentPath.add(root.val);
    if (root.left == null && root.right == null) {
        if (number == sum) {
            result.add(new ArrayList<>(currentPath));
        }
        currentPath.remove(currentPath.size()-1);
        return;
    }

    pathSumUtil(root.left, number, sum, result, currentPath);
    pathSumUtil(root.right, number, sum , result, currentPath);
    currentPath.remove(currentPath.size()-1);
}
public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();
    pathSumUtil(root, 0, sum, result, current);
    return result;
}
//----------------------------------------------------------------

    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int inorder[] = {4,2,5,1,6,3};
        int preorder[] = {1,2,4,5,3,6};
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        TreeNode root = ctf.createTree(inorder, preorder);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        System.out.println();
        tt.preOrder(root);
        System.out.println();
        SumRootToLeafNumbers srln = new SumRootToLeafNumbers();
        //System.out.println(srln.sumNumbers(root));//385
        //System.out.println(srln.maximumnSumOfPath(root));//136
        //System.out.println(srln.pathWithMinimumSum(root));//1->2->4
//        ArrayList<Integer> ans = srln.printAllPath(root);
//        for(Integer path: ans) {
//            System.out.println(path);
//        }
        System.out.println(srln.sumOfPathGivenNumber(root, 126));
        
        
    }
    
}
