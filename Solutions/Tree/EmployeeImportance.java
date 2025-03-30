package Solutions.Tree;

import java.util.HashMap;
import java.util.List;

//690. Employee Importance
//https://leetcode.com/problems/employee-importance
public class EmployeeImportance {
    /*
     * // Definition for Employee.
     * class Employee {
     * public int id;
     * public int importance;
     * public List<Integer> subordinates;
     * };
     */

    class Solution {
        HashMap<Integer, Employee> map;

        public int getImportance(List<Employee> employees, int id) {
            map = new HashMap<Integer, Employee>();
            for (Employee emp : employees) {
                map.put(emp.id, emp);
            }
            int result = getImp(map, id);
            return result;
        }

        public int getImp(HashMap<Integer, Employee> map, int id) {
            Employee emp = map.get(id);
            int result = 0;
            result += emp.importance;
            for (int sub : emp.subordinates) {
                result += getImp(map, sub);
            }
            return result;
        }
    }
}

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
