package com.interview.mysql;

/*
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
 */



public class MySql {
    
}
