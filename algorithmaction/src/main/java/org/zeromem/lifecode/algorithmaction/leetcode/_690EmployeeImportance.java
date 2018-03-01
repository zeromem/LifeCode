package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.*;

/**
 * @author zeromem
 * @date 2018/3/1
 */
public class _690EmployeeImportance {
    public static void main(String[] args) {
        _690EmployeeImportance test = new _690EmployeeImportance();

    }

    public int getImportance(List<Employee> employees, int id) {
        int result = 0;
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }

        Queue<Employee> queue = new LinkedList<>();
        queue.add(map.get(id));
        while (!queue.isEmpty()) {
            Employee cur = queue.poll();
            result += cur.importance;
            for (Integer subordinate : cur.subordinates) {
                queue.add(map.get(subordinate));
            }
        }
        return result;
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
}

