/**
 * 630. Course Schedule III
Hard

There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = 
[durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must 
be finished before or on lastDayi.

You will start on the 1st day and you cannot take two or more courses simultaneously.

Return the maximum number of courses that you can take.

 

Example 1:

Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
Output: 3
Explanation: 
There are totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the 
next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take 
the next course on the 1101st day. 
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day. 
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.

Example 2:

Input: courses = [[1,2]]
Output: 1

Example 3:

Input: courses = [[3,2],[4,3]]
Output: 0

 

Constraints:

    1 <= courses.length <= 104
    1 <= durationi, lastDayi <= 104

https://leetcode.com/problems/course-schedule-iii/
 */
public class CourseScheduleIII {
    /**
     * Based on sorting and pq. 
     * First sort array by the end date, so we're picking the soonest one first
     * 
     * Then when checked that next element is within the end date we roll with the current time
     * and add the duration to the pq. Pq is max pq, so course with higher duration will be on top. 
     * 
     * if end date if sooner we may still include current class, just have to check if max duration course (from the pq)
     * is longer than the current one, if so - kick it out from pq and add current one in it's place
     * 
     * time - O(nlgn) - sorting, heap operations are lgn for add n times - nlgn
     * space - O(n) - max of all elements are in the pq
     * 
     * @param courses
     * @return
     */
    public int scheduleCourse(int[][] courses) {
        int cur = 0;
        int res = 0;
        
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int[] c : courses) {
            if (cur + c[0] <= c[1]) {
                cur += c[0];
                pq.add(c[0]);
            } else if (!pq.isEmpty() && pq.peek() > c[0]) {
                cur = cur - pq.poll() + c[0];
                pq.add(c[0]);
            }
        }
        return pq.size();
    }
}
