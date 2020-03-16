#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <iterator>
#include <sstream>
#include <stdio.h>
using namespace std;
/*
1375. Bulb Switcher III

Difficulty: Medium
There is a room with n bulbs, numbered from 1 to n, arranged in a row from left to right. 
Initially, all the bulbs are turned off.

At moment k (for k from 0 to n - 1), we turn on the light[k] bulb. A bulb change color 
to blue only if it is on and all the previous bulbs (to the left) are turned on too.

Return the number of moments in which all turned on bulbs are blue.

Example 1:



Input: light = [2,1,3,5,4]
Output: 3
Explanation: All bulbs turned on, are blue at the moment 1, 2 and 4.
Example 2:

Input: light = [3,2,4,1,5]
Output: 2
Explanation: All bulbs turned on, are blue at the moment 3, and 4 (index-0).
Example 3:

Input: light = [4,1,2,3]
Output: 1
Explanation: All bulbs turned on, are blue at the moment 3 (index-0).
Bulb 4th changes to blue at the moment 3.
Example 4:

Input: light = [2,1,4,3,6,5]
Output: 3
Example 5:

Input: light = [1,2,3,4,5,6]
Output: 6
 

Constraints:

n == light.length
1 <= n <= 5 * 10^4
light is a permutation of  [1, 2, ..., n]
*/
class Solution {
    //  [2,1,3]
    //[1,0,0,0]
    //[1,0,1,0]  max = 2
public:
    //Idea - keep the array of bool that indicates if bulb is on (not blue).
    //keep index of the latest blue bulb. Update the latest index of blue, so next time
    //we'll go from latest index to the bulb that we just turned on 
    int numTimesAllBlue(vector<int>& light) {
        int lastIndex = 0;
        bool arr [light.size() + 1];
        for (int i = 1; i < light.size() + 1; i++)
            arr[i] = false;
        
        arr[0] = true;
        
        int res = 0;
        int maxCur = 0;
        for (int i = 0; i < light.size(); i++) {
            int bIdx = light.at(i);
            arr[bIdx] = true;
            maxCur = max(maxCur, bIdx);
            if (arr[bIdx - 1]) {
                for (int j = lastIndex + 1;j <= maxCur; j++) {
                    if(!arr[j]) {
                        j--;
                        break;
                    }
                    lastIndex = j;
                }
                if (lastIndex == maxCur)
                    res++;
            }
        }
        return res;
    }
};

int main() {
    Solution obj;
    vector<int> v {1,2,3,4,5,6};
    cout<<obj.numTimesAllBlue(v);
    return 0;
}