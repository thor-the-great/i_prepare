/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 690. Employee Importance
 * Easy
 *
 * You are given a data structure of employee information, which includes the employee's unique
 * id, his importance value and his direct subordinates' id.
 *
 * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee
 * 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure
 * like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that
 * although employee 3 is also a subordinate of employee 1, the relationship is not direct.
 *
 * Now given the employee information of a company, and an employee id, you need to return the
 * total importance value of this employee and all his subordinates.
 *
 * Example 1:
 *
 * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * Output: 11
 * Explanation:
 * Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee
 * 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3
 * = 11.
 *
 *
 * Note:
 *
 * One employee has at most one direct leader and may have several subordinates.
 * The maximum number of employees won't exceed 2000.
 */
public class EmployeeImportance {

    /**
     * Build graph of employees, start iterating from the given id unless there are no
     * subordinates
     * @param employees
     * @param id
     * @return
     */
    public int getImportance(List<Employee> employees, int id) {
        int[] imp = new int[2001];
        List<Integer>[] graph = new ArrayList[2001];

        for (Employee empl : employees) {
            imp[empl.id] = empl.importance;
            List<Integer> sub = new ArrayList();
            for (int oneSub : empl.subordinates) {
                sub.add(oneSub);
            }
            graph[empl.id] = sub;
        }
        int res = 0;
        Queue<Integer> q = new LinkedList();
        q.add(id);
        while (!q.isEmpty()) {
            int empId = q.poll();
            res+=imp[empId];
            for (int oneSub : graph[empId]) {
                q.add(oneSub);
            }
        }
        return res;
    }
}

class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
