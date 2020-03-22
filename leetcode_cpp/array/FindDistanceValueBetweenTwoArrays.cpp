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
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        sort(arr2.begin(), arr2.end());
        int res = 0;
        for (int n : arr1) {
            int l = 0, r = arr2.size() - 1;
            int dist = n - d;
            while (l <= r) {
                int m = l + (r - l)/2;
                if (arr2[m] >= dist) {
                    r = m - 1;    
                } else {
                    l = m + 1;
                }
            }
            if (l >= arr2.size() || abs(n - arr2[l]) > d)
                res++;
        }
        return res;
    }
};

int main() {
    Solution obj;
    vector<int> arr1 {1,4,2,3}, arr2 {-4,-3,6,10,20,30}; 
    int d = 3;
    cout<< obj.findTheDistanceValue(arr1, arr2, d);
    return 0;
}