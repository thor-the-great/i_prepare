/**
 * 1377. Frog Position After T Seconds
Hard

Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from 
the vertex 1. In one second, the frog jumps from its current vertex to another unvisited vertex if 
they are directly connected. The frog can not jump back to a visited vertex. In case the frog can 
jump to several vertices it jumps randomly to one of them with the same probability, otherwise, 
when the frog can not jump to any unvisited vertex it jumps forever on the same vertex. 

The edges of the undirected tree are given in the array edges, where edges[i] = [fromi, toi] means 
that exists an edge connecting directly the vertices fromi and toi.

Return the probability that after t seconds the frog is on the vertex target.

 

Example 1:



Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
Output: 0.16666666666666666 
Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 probability to the vertex 2 after second 1 and then jumping with 1/2 probability to vertex 4 after second 2. Thus the probability for the frog is on the vertex 4 after 2 seconds is 1/3 * 1/2 = 1/6 = 0.16666666666666666. 
Example 2:



Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
Output: 0.3333333333333333
Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping 
with 1/3 = 0.3333333333333333 probability to the vertex 7 after second 1. 
Example 3:

Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6
Output: 0.16666666666666666
 

Constraints:

1 <= n <= 100
edges.length == n-1
edges[i].length == 2
1 <= edges[i][0], edges[i][1] <= n
1 <= t <= 50
1 <= target <= n
Answers within 10^-5 of the actual value will be accepted as correct.
 */
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <iterator>
#include <sstream>
#include <queue> 
#include <stdio.h>
using namespace std;

class Solution {
    
public:
    
    long double p = 0;
    int target;
    vector<vector<int>> adjList;
    vector<bool> visited;
    
    bool dfs(int node, int depth, long double p) {
        if (depth < 0)
            return false;
        
        visited[node] = true;
        
        if (node == target) {
            if (depth == 0 || adjList[node].size() == (node != 1)) {
                this->p = p;
            }
            return true;
        }
        for (int n : adjList[node]) {
            if (!visited[n] && dfs(n, depth-1, p*(long double)1/(adjList[node].size()-(node != 1))))		//DFS to each children with carrying the probablity to reach them. (Early exit if found)
                return true;
        }
        return false;
    }
    
    double frogPosition(int n, vector<vector<int>>& edges, int t, int target) {
        adjList.resize(n+1);
        visited.resize(n+1,false);
        this->target=target;
        for(vector<int> &v:edges) {		//Create adjacency list.
            adjList[v[0]].push_back(v[1]);
            adjList[v[1]].push_back(v[0]);
        }
        
        dfs(1, t, 1);
        return p;
    }
};