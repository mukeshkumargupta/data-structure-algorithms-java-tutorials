package com.interview.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 06/01/2017
 * @author Mukesh Kumar Gupta
 * 
 * Construct suffix tree using Ukkonen's algorithm
 * 
 * Solution
 * Rule 1: For phase i+1 if S[j..i] ends at last character of leaf edge then add S[i+1] at 
 * the end.
 * Rule 2: For phase i+1 if S[j..i] ends somewhere in middle of edge and next character is
 * not S[i+1] then a new leaf edge with label S[i+1] should be created
 * Rule 3: For phase i+1 if S[j..i] ends somewhere in middle of edge and next character is
 * S[i+1] then do nothing(resulting in implicit tree)
 * 
 * Suffix Link:
 * For every TreeNode with label x@ where x is a single character and @ is possibly empty substring
 * there is another TreeNode with label x. This TreeNode is suffix link of first TreeNode. If @ is
 * empty then suffix link is root.
 * 
 * Trick1
 * Skip/Count trick
 * While traveling down if number of characters on edge is less than number of characters
 * to traverse then skip directly to the end of the edge. If number of characters on label
 * is more than number of characters to traverse then go directly to that character
 * we care about.
 * 
 * Edge-label compression
 * Instead of storing actual characters on the path store start and end indices on the 
 * path.
 * 
 * Trick2 - Stop process as soon as you hit rule 3. Rule 3 is show stopper
 * 
 * Trick3 - Keep a global end on leaf to do rule 1 extension.
 * 
 * Active point - It is the point from which traversal starts for next extension or next phase.
 * Active point always starts from root. Other extension will get active point set up
 * correctly by last extension.
 * 
 * Active TreeNode - TreeNode from which active point will start
 * Active Edge - It is used to choose the edge from active TreeNode. It has index of character. 
 * Active Length - How far to go on active edge.
 * 
 * Active point rules
 * 1) If rule 3 extension is applied then active length will increment by 1 if active length is not greater then length of path on edge.
 * 2) If rule 3 extension is applied and if active length gets greater than length path of edge then change active TreeNode, active edge and active length
 * 3) If active length is 0 then always start looking for the character from root.
 * 4) If rule 2 extension is applied and if active TreeNode is root then active edge is active edge + 1 and active length is active lenght -1
 * 5) If rule 2 extension is applied and if active TreeNode is not root then follow suffix link and make active TreeNode as suffix link and do no change 
 * anything.
 * 
 * Test cases 
 * adeacdade
 * abcabxabcd
 * abcdefabxybcdmnabcdex
 * abcadak
 * dedododeodo
 * abcabxabcd
 * mississippi
 * banana
 * ooooooooo
 * 
 * References
 * http://web.stanford.edu/~mjkay/gusfield.pdf
 * http://www.geeksforgeeks.org/ukkonens-suffix-tree-construction-part-6/
 * https://www.cs.helsinki.fi/u/ukkonen/SuffixT1withFigs.pdf
 * https://gist.github.com/axefrog/2373868
 */
public class SuffixTree {

    public static void main(String args[]){
        SuffixTree st = new SuffixTree("mississippi".toCharArray());
        st.build();
        st.dfsTraversal();
        System.out.println(st.validate());
    }
    
    private SuffixTreeNode root;
    private Active active;
    private int remainingSuffixCount;
    private End end;
    private char input[];
    private static char UNIQUE_CHAR = '$';
    
    public SuffixTree(char input[]){
        this.input = new char[input.length+1];
        for(int i=0; i < input.length; i++){
            this.input[i] = input[i];
        }
        this.input[input.length] = UNIQUE_CHAR;
    }
    
    public void build(){
        root = SuffixTreeNode.createTreeNode(1, new End(0));
        root.index = -1;
        active = new Active(root);
        this.end = new End(-1);
        //loop through string to start new phase
        for(int i=0; i < input.length; i++){
            startPhase(i);
        }

        if (remainingSuffixCount != 0) {
            System.out.print("Something wrong happened");
        }
        //finally walk the tree again and set up the index.
        setIndexUsingDfs(root, 0, input.length);
    }
    
    private void startPhase(int i){
        //set lastCreatedInternalTreeNode to null before start of every phase.
        SuffixTreeNode lastCreatedInternalTreeNode = null;
        //global end for leaf. Does rule 1 extension as per trick 3 by incrementing end.
        end.end++;
        
        //these many suffixes need to be created.
        remainingSuffixCount++;
        while(remainingSuffixCount > 0){
            //if active length is 0 then look for current character from root.
            if(active.activeLength == 0){
                //if current character from root is not null then increase active length by 1 
                //and break out of while loop. This is rule 3 extension and trick 2 (show stopper)
                if(selectTreeNode(i) != null){
                    active.activeEdge = selectTreeNode(i).start;
                    active.activeLength++;
                    break;
                } //create a new leaf TreeNode with current character from leaf. This is rule 2 extension.
                else {
                    root.child[input[i]] = SuffixTreeNode.createTreeNode(i, end);
                    remainingSuffixCount--;
                }
            } else{
                //if active length is not 0 means we are traversing somewhere in middle. So check if next character is same as
                //current character. 
                try {
                    char ch = nextChar(i);
                    //if next character is same as current character then do a walk down. This is again a rule 3 extension and
                    //trick 2 (show stopper).
                    if(ch == input[i]){
                        //if lastCreatedInternalTreeNode is not null means rule 2 extension happened before this. Point suffix link of that TreeNode
                        //to selected TreeNode using active point.
                        //TODO - Could be wrong here. Do we only do this if when walk down goes past a TreeNode or we do it every time.
                        if(lastCreatedInternalTreeNode != null){
                            lastCreatedInternalTreeNode.suffixLink = selectTreeNode();
                        }
                        //walk down and update active TreeNode if required as per rules of active TreeNode update for rule 3 extension.
                        walkDown(i);
                        break;
                    }
                    else {
                        //next character is not same as current character so create a new internal TreeNode as per 
                        //rule 2 extension.
                        SuffixTreeNode TreeNode = selectTreeNode();
                        int oldStart = TreeNode.start;
                        TreeNode.start = TreeNode.start + active.activeLength;
                        //create new internal TreeNode
                        SuffixTreeNode newInternalTreeNode = SuffixTreeNode.createTreeNode(oldStart, new End(oldStart + active.activeLength - 1));

                        //create new leaf TreeNode
                        SuffixTreeNode newLeafTreeNode = SuffixTreeNode.createTreeNode(i, this.end);

                        //set internal TreeNodes child as old TreeNode and new leaf TreeNode.
                        newInternalTreeNode.child[input[newInternalTreeNode.start + active.activeLength]] = TreeNode;
                        newInternalTreeNode.child[input[i]] = newLeafTreeNode;
                        newInternalTreeNode.index = -1;
                        active.activeTreeNode.child[input[newInternalTreeNode.start]] = newInternalTreeNode;

                        //if another internal TreeNode was created in last extension of this phase then suffix link of that
                        //TreeNode will be this TreeNode.
                        if (lastCreatedInternalTreeNode != null) {
                            lastCreatedInternalTreeNode.suffixLink = newInternalTreeNode;
                        }
                        //set this guy as lastCreatedInternalTreeNode and if new internalTreeNode is created in next extension of this phase
                        //then point suffix of this TreeNode to that TreeNode. Meanwhile set suffix of this TreeNode to root.
                        lastCreatedInternalTreeNode = newInternalTreeNode;
                        newInternalTreeNode.suffixLink = root;

                        //if active TreeNode is not root then follow suffix link
                        if(active.activeTreeNode != root){
                            active.activeTreeNode = active.activeTreeNode.suffixLink;
                        }
                        //if active TreeNode is root then increase active index by one and decrease active length by 1
                        else{
                            active.activeEdge = active.activeEdge  + 1;
                            active.activeLength--;
                        }
                        remainingSuffixCount--;
                    }
           
                } catch (EndOfPathException e) {

                    //this happens when we are looking for new character from end of current path edge. Here we already have internal TreeNode so
                    //we don't have to create new internal TreeNode. Just create a leaf TreeNode from here and move to suffix new link.
                    SuffixTreeNode TreeNode = selectTreeNode();
                    TreeNode.child[input[i]] = SuffixTreeNode.createTreeNode(i, end);
                    if (lastCreatedInternalTreeNode != null) {
                        lastCreatedInternalTreeNode.suffixLink = TreeNode;
                    }
                    lastCreatedInternalTreeNode = TreeNode;
                    //if active TreeNode is not root then follow suffix link
                    if(active.activeTreeNode != root){
                        active.activeTreeNode = active.activeTreeNode.suffixLink;
                    }
                    //if active TreeNode is root then increase active index by one and decrease active length by 1
                    else{
                        active.activeEdge = active.activeEdge + 1;
                        active.activeLength--;
                    }
                    remainingSuffixCount--;
                }
            }
        }
    }
    
    private void walkDown(int index){
        SuffixTreeNode TreeNode = selectTreeNode();
        //active length is greater than path edge length.
        //walk past current TreeNode so change active point.
        //This is as per rules of walk down for rule 3 extension.
        if(diff(TreeNode) < active.activeLength){
            active.activeTreeNode = TreeNode;
            active.activeLength = active.activeLength - diff(TreeNode);
            active.activeEdge = TreeNode.child[input[index]].start;
        }else{
            active.activeLength++;
        }
    }
    
    //find next character to be compared to current phase character.
    private char nextChar(int i) throws EndOfPathException{
        SuffixTreeNode TreeNode = selectTreeNode();
        if(diff(TreeNode) >= active.activeLength){
            return input[active.activeTreeNode.child[input[active.activeEdge]].start + active.activeLength];
        }
        if(diff(TreeNode) + 1 == active.activeLength ){
            if(TreeNode.child[input[i]] != null){
                return input[i];
            }
        }
        else{
            active.activeTreeNode = TreeNode;
            active.activeLength = active.activeLength - diff(TreeNode) -1;
            active.activeEdge = active.activeEdge + diff(TreeNode)  +1;
            return nextChar(i);
        }
        
        throw new EndOfPathException();
    }
    
    private static class EndOfPathException extends Exception{
        
    }
    
    private SuffixTreeNode selectTreeNode(){
        return active.activeTreeNode.child[input[active.activeEdge]];
    }
    
    private SuffixTreeNode selectTreeNode(int index){
        return active.activeTreeNode.child[input[index]];
    }
    
    
    private int diff(SuffixTreeNode TreeNode){
        return TreeNode.end.end - TreeNode.start;
    }
  
    private void setIndexUsingDfs(SuffixTreeNode root,int val, int size){
        if(root == null){
            return;
        }
        
        val += root.end.end - root.start + 1;
        if(root.index != -1){
            root.index = size - val;
            return;
        }
        
        for(SuffixTreeNode TreeNode : root.child){
            setIndexUsingDfs(TreeNode, val, size);
        }
    }
    
    /**
     * Do a DFS traversal of the tree.
     */
    public void dfsTraversal(){
        List<Character> result = new ArrayList<>();
        for(SuffixTreeNode TreeNode : root.child){
            dfsTraversal(TreeNode, result);
        }
    }
    
    private void dfsTraversal(SuffixTreeNode root, List<Character> result){
        if(root == null){
            return;
        }
        if(root.index != -1){
            for(int i=root.start; i <= root.end.end; i++){
                result.add(input[i]);
            }
            result.stream().forEach(System.out::print);
            System.out.println(" " + root.index);
            for(int i=root.start; i <= root.end.end; i++){
                result.remove(result.size()-1);
            }
            return;
        }
        
        for(int i=root.start; i <= root.end.end; i++){
            result.add(input[i]);
        }
        
        for(SuffixTreeNode TreeNode : root.child){
            dfsTraversal(TreeNode, result);
        }
        
        for(int i=root.start; i <= root.end.end; i++){
            result.remove(result.size()-1);
        }
       
    }
    
    /**
     * Do validation of the tree by comparing all suffixes and their index at leaf TreeNode.
     */
    private boolean validate(SuffixTreeNode root, char[] input, int index, int curr){
        if(root == null){
            System.out.println("Failed at " + curr + " for index " + index);
            return false;
        }
        
        if(root.index != -1){
            if(root.index != index){
                System.out.println("Index not same. Failed at " + curr + " for index " + index);
                return false;        
            }else{
                return true;
            }
        }
        if(curr >= input.length){
            System.out.println("Index not same. Failed at " + curr + " for index " + index);
            return false;        
        }
        
        SuffixTreeNode TreeNode = root.child[input[curr]];
        if(TreeNode == null){
            System.out.println("Failed at " + curr + " for index " + index);
            return false;
        }
        int j = 0;
        for(int i=TreeNode.start ; i <= TreeNode.end.end; i++){
            if(input[curr+j] != input[i] ){
                System.out.println("Mismatch found " + input[curr+j] + " " + input[i]);
                return false;
            }
            j++;
        }
        curr += TreeNode.end.end - TreeNode.start + 1;
        return validate(TreeNode, input, index, curr);
    }
    
    public boolean validate(){
        for(int i=0; i < this.input.length; i++){
            if(!validate(this.root, this.input, i, i)){
                System.out.println("Failed validation");
                return false;
            }
        }
        return true;
    }
}

class SuffixTreeNode{
    
    private SuffixTreeNode(){
    }
    
    private static final int TOTAL = 256;
    SuffixTreeNode[] child = new SuffixTreeNode[TOTAL];
    
    int start;
    End end;
    int index;
    
    SuffixTreeNode suffixLink;
    
    public static SuffixTreeNode createTreeNode(int start, End end){
        SuffixTreeNode TreeNode = new SuffixTreeNode();
        TreeNode.start = start;
        TreeNode.end = end;
        return TreeNode;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        int i=0;
        for(SuffixTreeNode TreeNode : child){
            if(TreeNode != null){
                buffer.append((char)i + " ");
            }
            i++;
        }
        return "SuffixTreeNode [start=" + start + "]" + " " + buffer.toString();
    }
 }

class End{
    public End(int end){
        this.end = end;
    }
    int end;
}

class Active{
    Active(SuffixTreeNode TreeNode){
        activeLength = 0;
        activeTreeNode = TreeNode;
        activeEdge = -1;
    }
    
    @Override
    public String toString() {
        
        return "Active [activeTreeNode=" + activeTreeNode + ", activeIndex="
                + activeEdge + ", activeLength=" + activeLength + "]";
    }

    SuffixTreeNode activeTreeNode;
    int activeEdge;
    int activeLength;
}