package com.interview.tree;

import java.util.*;

//Reference: https://www.youtube.com/watch?v=Gi0202QawRQ&list=PLIA-9QRQ0RqG6CNfSJSzT0h5Pc_HvwZG5&index=112&t=0s
//Derived question: find max number in all path(done) during sum make one variable maximum and keep update it. also find min number in all path(almost same as max sum path).
//Return path which has minimum path sum of all node in path(done), print all path(done), Root to leaf path sum equal to a given number(done) reference: https://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/,
//,Find all duplicate path, find unique path in tree if node is represented by character like A, B etc, check tree is sum tree where each node is sum of all its children sum Reference: http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree/,
//find all parent node which are parent of any child, find all cousin node, find all nodes which parent are same, 
//find all root-to-leaf paths where each path's sum equals the given sum, 
public class SumRootToLeafNumbers {
    void dfs(Node root,long val, long[] ans)
    {
        if(root == null)
            return;
        
        val = 10*val + root.data;
        //Here logic will change

        //Leaf node
        if(root.left == null && root.right == null)
        {
            ans[0]+=val; //Here logic will change
            return;
        }
        dfs(root.left,val,ans);
        dfs(root.right,val, ans);
    }
public long sumNumbers(Node root) {
        if(root == null)
            return 0;
        
        long[] ans = new long[1];
        dfs(root,0, ans);
        return ans[0];
    }
//----------------------------------------------------------------
//Derived FindMaxNumberInAllPath
void maximumnSumOfPathUtil(Node root,long val, long[] ans)
{
    if(root == null)
        return;
    
    val = 10*val + root.data;

    //Leaf node
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
public long maximumnSumOfPath(Node root) {
    if(root == null)
        return 0;
    long[] ans = new long[1];
    maximumnSumOfPathUtil(root,0, ans);
    return ans[0];
}
//----------------------------------------------------------------

//Derived question: Return path which has minimum path sum of all node in path
//It can be solved using find answer in integer then convert in staring then compute output but again parsing required
//Other method is not working so make as other one
//----------------------------------------------------------------
void pathWithMinimumSumUtil(Node root,long val, long min, StringBuilder path, StringBuilder ans)
{
    if(root == null)
        return;
    
    val = 10*val + root.data;
    //Need to change logic
    //Trace the path
    if(path.toString().isEmpty()) {
        path.append(root.data);
    } else {
        path.append("->" + root.data);
    }
    

    //Leaf node
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
public String pathWithMinimumSum(Node root) {
    if(root == null)
        return null;
    
    StringBuilder path = new StringBuilder();
    long min = Integer.MAX_VALUE;
    StringBuilder ans = new StringBuilder();
    
    pathWithMinimumSumUtil(root,0, min, path, ans);
    return ans.toString();
}

//----------------------------------------------------------------

//Derived question: Return path which has minimum path sum of all node in path
//It can be solved using find answer in integer then convert in staring then compute output but again parsing required
//Other method is not working so make as other one
//----------------------------------------------------------------
void printAllPathUtil(Node root,int val, ArrayList<Integer> ans)
{
  if(root == null)
      return;
  
  val = 10*val + root.data;
  

  //Leaf node
  if(root.left == null && root.right == null)
  {
      ans.add(val);

      return;
  }
  printAllPathUtil(root.left,val, ans);
  printAllPathUtil(root.right,val, ans);
}
public ArrayList<Integer> printAllPath(Node root) {
  if(root == null)
      return null;
  

  ArrayList<Integer> ans = new ArrayList<>();
  
  printAllPathUtil(root,0, ans);
  return ans;
}

//----------------------------------------------------------------

//----------------------------------------------------------------
//Derived FindMaxNumberInAllPath
void sumOfPathGivenNumberUtil(Node root,long val, long givenNumber, long[] ans)
{
  if(root == null)
      return;
  
  val = 10*val + root.data;

  //Leaf node
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
public long sumOfPathGivenNumber(Node root, long givenNumber) {
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
//----------------------------------------------------------------

    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int inorder[] = {4,2,5,1,6,3};
        int preorder[] = {1,2,4,5,3,6};
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        Node root = ctf.createTree(inorder, preorder);
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
