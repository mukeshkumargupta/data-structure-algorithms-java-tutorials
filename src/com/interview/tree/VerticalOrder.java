package com.interview.tree;

import java.util.*;

/**
 * Reference concept: Good explanation using bfs:
 * https://www.youtube.com/watch?v=XiVhlOye0V0 dfs:
 * https://www.youtube.com/watch?v=kqHNP6NTzME&list=PLIA-9QRQ0RqG6CNfSJSzT0h5Pc_HvwZG5&index=110&t=13s
 * Derived question, print vertical order when row and column same then sorted,
 * if if same column then row first, Given a binary tree, return the vertical
 * order traversal of its TreeNodes' values. (ie, from top to bottom, column by
 * column). If two TreeNodes are in the same row and column, the order should be
 * from left to right. All derived question Top view, botton view, (left view
 * and right view and all boundry level order will be easy to print, vertical
 * order will not solve problem) https://www.youtube.com/watch?v=AIokcTT0LuE,,
 * https://leetcode.com/problems/find-bottom-left-tree-value/(You can solve this
 * question using bfs only without making hash map) but this method also u can
 * solve, also print bottom right most TreeNode print any column like 0th column on
 * -1 column or any row like 0 or any row then using dfs you can do also no need
 * of any map required. Map only required if you need all column val. try to
 * solve given row and column print using bfs since we have point so it seems it
 * is possible it. both way try it in future. Hint just add check and print that
 * is required only. https://leetcode.com/problems/find-bottom-left-tree-value/
 * this question can be solved just to traverse and put point based on criteria.
 * keep traversing and update result and keep change the result.
 *
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/ (Premium
 * question on leetcode) Category: Must Do, VVImp Status: Done
 */
public class VerticalOrder {
    public List<List<Integer>> verticalOrder_NotMine(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        int minVal = 0;
        int maxVal = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        Deque<TreeNode> queue = new LinkedList<>();
        Deque<Integer> verticalQueue = new LinkedList<>();
        
        queue.offerFirst(root);
        verticalQueue.offerFirst(0);
        int vertical;
        while (!queue.isEmpty()) {
            root = queue.pollFirst();
            vertical = verticalQueue.pollFirst();
            minVal = Math.min(minVal, vertical);
            maxVal = Math.max(maxVal, vertical);
            
            List<Integer> r = map.get(vertical);
            if (r == null) {
                r = new ArrayList<>();
                map.put(vertical, r);
            }
            r.add(root.val);
            
            if (root.left != null) {
                queue.offerLast(root.left);
                verticalQueue.offerLast(vertical - 1);
            }
            
            if (root.right != null) {
                queue.offerLast(root.right);
                verticalQueue.offerLast(vertical + 1);
            }
        }
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = minVal; i <= maxVal; i++) {
            List<Integer> r = map.get(i);
            result.add(r);
        }
        return result;
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    // Using bfs approach
    class Point {
        TreeNode root;
        int x;
        int y;
        
        public Point(TreeNode root, int x, int y) {
            this.root = root;
            this.x = x;
            this.y = y;
        }
    }
    
    class Employee {
        int salary;
        int age;
        
        public Employee(int salary, int age) {
            this.salary = salary;
            this.age = age;
        }
    }
    
    public List<List<Integer>> verticalTraversalBfs(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        
        Map<Integer, PriorityQueue<Point>> map = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(root, 0, 0));
        
        Comparator<Point> comparator = (p1, p2) -> { // Keep practicing this way
            if (p1.y == p2.y) { // sort based on val if y is same else sort based on y.
                return p1.root.val - p2.root.val;
            } else {
                return p1.y - p2.y;
            }
        };
        int minIdx = 0;
        int maxIdx = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            root = point.root;
            map.putIfAbsent(point.x, new PriorityQueue<>((p1, p2) -> { // Keep practicing this way
                if (p1.y == p2.y) { // sort based on val if y is same else sort based on y.
                    return p1.root.val - p2.root.val;
                } else {
                    return p1.y - p2.y;
                }
            }));
            map.get(point.x).add(point);
            minIdx = Math.min(minIdx, point.x);
            maxIdx = Math.max(maxIdx, point.x);
            if (root.left != null)
                queue.offer(new Point(root.left, point.x - 1, point.y + 1));
            if (root.right != null)
                queue.offer(new Point(root.right, point.x + 1, point.y + 1));
        }
        
        for (int i = minIdx; i <= maxIdx; i++) {
            PriorityQueue<Point> pq = map.get(i);
            List<Integer> list = new ArrayList<>();
            while (!pq.isEmpty()) {
                list.add(pq.poll().root.val);
            }
            ans.add(list);
        }
        
        return ans;
    }
    //////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////
    // Using dfs Note: Practice same question with so many method to make it clear
    public void verticalTraversalDfsUtil(Point point, Map<Integer, PriorityQueue<Point>> map, int[] minIdx,
            int[] maxIdx, Comparator<Point> comparator) {
        if (point.root == null)
            return;
        
        // Calculate min and max x value
        if (minIdx[0] > point.x) {
            minIdx[0] = point.x;
        }
        
        if (maxIdx[0] < point.x) {
            maxIdx[0] = point.x;
        }
        
        map.putIfAbsent(point.x, new PriorityQueue<>(comparator));
        map.get(point.x).add(point);
        
        verticalTraversalDfsUtil(new Point(point.root.left, point.x - 1, point.y + 1), map, minIdx, maxIdx, comparator);
        verticalTraversalDfsUtil(new Point(point.root.right, point.x + 1, point.y + 1), map, minIdx, maxIdx,
                comparator);
    }
    
    public List<List<Integer>> verticalTraversalDfs(TreeNode root) {
        
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, PriorityQueue<Point>> map = new HashMap<>();
        int[] minIdx = new int[1];
        minIdx[0] = 0;
        int[] maxIdx = new int[1];
        maxIdx[0] = 0;
        Comparator<Point> comparator = (p1, p2) -> { // Keep practicing this way
            if (p1.y == p2.y) { // sort based on val if y is same else sort based on y.
                return p1.root.val - p2.root.val;
            } else {
                return p1.y - p2.y;
            }
        };
        verticalTraversalDfsUtil(new Point(root, 0, 0), map, minIdx, maxIdx, comparator);
        
        for (int i = minIdx[0]; i <= maxIdx[0]; i++) {
            PriorityQueue<Point> pq = map.get(i);
            List<Integer> list = new ArrayList<>();
            while (!pq.isEmpty()) {
                list.add(pq.poll().root.val);
            }
            ans.add(list);
        }
        
        return ans;
    }
    
    public List<List<Point>> verticalTraversalWithPointsDfs(TreeNode root) {
        
        List<List<Point>> ans = new ArrayList<>();
        Map<Integer, PriorityQueue<Point>> map = new HashMap<>();
        int[] minIdx = new int[1];
        minIdx[0] = 0;
        int[] maxIdx = new int[1];
        maxIdx[0] = 0;
        Comparator<Point> comparator = (p1, p2) -> { // Keep practicing this way
            if (p1.y == p2.y) { // sort based on val if y is same else sort based on y.
                return p1.root.val - p2.root.val;
            } else {
                return p1.y - p2.y;
            }
        };
        verticalTraversalDfsUtil(new Point(root, 0, 0), map, minIdx, maxIdx, comparator);
        
        for (int i = minIdx[0]; i <= maxIdx[0]; i++) {
            PriorityQueue<Point> pq = map.get(i);
            List<Point> list = new ArrayList<>();
            while (!pq.isEmpty()) {
                list.add(pq.poll());
            }
            ans.add(list);
        }
        
        return ans;
    }
    
    // Sort employee using lamda function
    public void buildEmployeeSort() {
        Comparator<Employee> ascendingEmployeeSalaryAge = (e1, e2) -> {
            boolean isEqual = (e1.salary - e2.salary) == 0;
            if (!isEqual) {
                return (e1.salary - e2.salary);
            } else {
                return e1.age - e2.age;
            }
            // Below code as bulkey
            /*
             * if (e1.salary > e2.salary) { //Tricky remember; if first is bigger and you
             * return 1 then ascending order order return 1; } else if (e1.salary <
             * e2.salary) { return -1;
             * 
             * } else { if (e1.age > e2.age) { return 1; } else if (e1.age < e2.age) {
             * return -1; } else { return 0; }
             * 
             * }
             */
        };
        
        // Create priority queue
        List<Employee> employeeLists = new ArrayList<>();
        employeeLists.add(new Employee(5241, 5));
        employeeLists.add(new Employee(5241, 2));
        employeeLists.add(new Employee(5245, 7));
        employeeLists.add(new Employee(245, 7));
        Collections.sort(employeeLists, ascendingEmployeeSalaryAge);
        for (Employee el : employeeLists) {
            System.out.println(el.salary + "," + el.age);
        }
        PriorityQueue<Employee> employeeQueue = new PriorityQueue<Employee>(ascendingEmployeeSalaryAge);
        employeeQueue.offer(new Employee(5241, 5));
        employeeQueue.offer(new Employee(5241, 2));
        employeeQueue.offer(new Employee(5245, 7));
        employeeQueue.offer(new Employee(245, 7));
        while (!employeeQueue.isEmpty()) { // It will give sorted and during poll it will manage sorting
            Employee el = employeeQueue.poll();
            System.out.println(el.salary + "," + el.age);
        }
    }
    
    // Reference: https://www.youtube.com/watch?v=bToZH9pGP5Y
    void printLeftview(TreeNode root, int level, int[] maxLevel)// here maxLevel we need to take object otherwise it will
                                                             // not retain value.
    {
        if (root == null)
            return;
        
        if (maxLevel[0] < level) {
            maxLevel[0] = level;
            System.out.println(root.val);
        }
        printLeftview(root.left, level + 1, maxLevel); // left child called
        printLeftview(root.right, level + 1, maxLevel); // right child called
        
    }
    
    void leftView(TreeNode root) {
        // Max height of tree assumed to be 100
        // Therefore for skew tree, max levels = 100
        int[] maxLevel = new int[1];
        printLeftview(root, 1, maxLevel);
    }
    
    // Reference: https://www.youtube.com/watch?v=rnRRlhTflLs
    void printRightview(TreeNode root, int level, int[] maxLevel)// here maxLevel we need to take object otherwise it will
                                                             // not retain value.
    {
        if (root == null)
            return;
        
        if (maxLevel[0] < level) {
            maxLevel[0] = level;
            System.out.println(root.val);
        }
        printRightview(root.right, level + 1, maxLevel); // right child called
        printRightview(root.left, level + 1, maxLevel); // left child called
    }
    
    void rightView(TreeNode root) {
        // Max height of tree assumed to be 100
        // Therefore for skew tree, max levels = 100
        int[] maxLevel = new int[1];
        printRightview(root, 1, maxLevel);
    }
    
    void printLeaf(TreeNode root) {
        if (root == null)
            return;
        
        if (root.left == null && root.right == null) {//Leaf condition
            System.out.println(root.val);
        }
        printLeaf(root.left); 
        printLeaf(root.right); 
    }

    
    //////////////////////////////////////////////////////////////////////////////////
    
    public static void main(String[] args) {
        int inorder[] = { 2, 4, 7, 8, 5, 1, 3, 6 };
        int preorder[] = { 1, 2, 7, 4, 5, 8, 6, 3 };
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        TreeNode root = ctf.createTree(inorder, preorder);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        System.out.println();
        tt.preOrder(root);
        System.out.println();
        VerticalOrder vo = new VerticalOrder();
        List<List<Integer>> ans = vo.verticalTraversalBfs(root);
        // Output print
        for (List<Integer> listElement : ans) {
            StringBuilder sb = new StringBuilder();
            for (Integer element : listElement) {
                sb.append("" + element);
            }
            System.out.println(sb.toString());
            
        }
        
        ans = vo.verticalTraversalDfs(root);
        // Output print
        for (List<Integer> listElement : ans) {
            StringBuilder sb = new StringBuilder();
            for (Integer element : listElement) {
                sb.append("" + element);
            }
            System.out.println(sb.toString());
        }
        
        // Top View
        System.out.println("TopView");
        ans = vo.verticalTraversalDfs(root);
        // Output print
        for (List<Integer> listElement : ans) {
            for (Integer element : listElement) {
                System.out.println(element);
                break;
            }
        }
        
        // Bottom View
        System.out.println("BottomView");
        // Output print
        for (List<Integer> listElement : ans) {
            for (int i = listElement.size() - 1; i >= 0; i--) {
                System.out.println(listElement.get(i));
                break;
            }
        }
        // LeftView
        System.out.println("LeftView");
        vo.leftView(root);
        
        // RightView
        System.out.println("RightView");
        vo.rightView(root);
        System.out.println("Leaf printing");
        vo.printLeaf(root);
        
        // Just to ensure sorting part
        // vo.buildEmployeeSort();
    }
}