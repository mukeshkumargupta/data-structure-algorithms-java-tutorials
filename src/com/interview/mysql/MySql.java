package com.interview.mysql;

/*
 * 
 * https://www.youtube.com/watch?v=zajIPJwLb78&list=PLIA-9QRQ0RqFnyCxA9_IDBaAjOOIg_CB5&index=4
 * https://leetcode.com/problems/swap-salary/
 * Category: Easy, mysql
 * # Write your MySQL query statement below
 * update salary SET sex = IF(sex = "m", "f", "m")
 * #update salary SET sex = CASE WHEN sex = "m" THEN "f" ELSE "m" END
 * 
 * https://leetcode.com/problems/combine-two-tables/
 * 
 * https://leetcode.com/problems/combine-two-tables
 * select FirstName, LastName, City, State from Person left join Address on Person.PersonId = Address.PersonId
 * or
 * # Write your MySQL query statement below
 * select FirstName, LastName, City, State from Person p left join Address a on p.PersonId = a.PersonId
 * 
 * https://leetcode.com/problems/second-highest-salary/
 * Second Highest Salary
 * select max(Salary) as SecondHighestSalary from Employee where Salary not in (select max(Salary) from Employee)
 * 
 * 2. Department wise highest Salary (Top 50 SQL Interview Questions) | GeeksforGeeks
 * select max(sal), deptno from emp group by deptno
 * 
 * No of employee in each department
 * select count(*), deptno from emp group by deptno
 * 
 * 3. Display Alternate Records (Top 50 SQL Interview Questions) | GeeksforGeeks
 * 
 * select * from (select empname, ename, salary rownum  rn from  emp order by rn)  where mod(rn, 2) != 0;
 * 
 * display ro no and details descending order
 * select rownum , empno, salary, ename from emp order by salary desc
 * 
 * 4. Display Duplicate of a Column| GeeksforGeeks, you cant use where clause with group by
 * select ename, count(*) from emp group by ename having count(*) > 1
 * 
 * you can print only duplicate name also like 
 * select ename from (select ename, count(*) from emp group by ename having count(*) > 1)
 * 
 * display count by descendeding order
 * select ename, count(*) from emp group by ename order by count(*) desc
 * 
 * 8. union vs uninonall (Top 5 SQL Interview Questions)| GeeksforGeeks, union same column same val type
 * select city, country from sample1
 * union
 * select city, country from sample2
 * Note: no duplicate row shoud come only union and unique row will display
 * 
 * union all show duplicate value
 * 
 * select city from  sample1
 * union all
 * select city from sample2 
 * Note if hyderabad in one table and hyderabad in othertable both will diplay
 * 
 * display matching deptno (inner join)
 * select ename, salary dept.deptno, dname, loc from emp, dept where emp.deptno = dept.deptno;
 * using alias with same result
 * select ename, salary d.deptno, dname, loc from emp e, dept d where e.deptno = d.deptno;
 * 
 * display matching record but location cichago
 * select ename, salary d.deptno, dname, loc from emp e, dept d where e.deptno = d.deptno and loc = 'CHACAGO';
 * 
 * select dept wise all salary from both table  (inner join)
 * select dname , sum(sal) from emp e, dept d where e.deptno=d.deptno group by dname
 * 
 * self join example joint to sleft table
 * display emp detains getting higher salary than their manager
 * select e1.name "employee" , e2.ename "manager" , sal from emp e1, emp e2 where e1.mgr=e2.empno and e1.sal>e2.sal;
 * 
 * display the emplyee details who join to their manager
 * select e1.name "employee" , e2.ename "manager" , sal from emp e1, emp e2 where e1.mgr=e2.empno and e1.hiredate>e2.hiredate;
 * 
 * Left join
 * 
 * select rownum, Empno, ename Emp.depno, dname, loc, job from
 * emp LEFT JOIN 
 * dept 
 * on Emp.depptno= Dept.deptno and dame='Sales'
 * 
 * here all left table val will come and right join not matched val will come null
 * 
 * full join means both table matched and unmached all will come
 */



public class MySql {
    
}
