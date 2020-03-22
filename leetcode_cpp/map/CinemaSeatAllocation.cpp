#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <iterator>
#include <sstream>
#include <queue> 
#include <stdio.h>
#include <unordered_map>
#include <bitset>
using namespace std;

class CinemaSeatAllocation {
public:
    int maxNumberOfFamilies(int n, vector<vector<int>>& reservedSeats) {
        unordered_map<int, bitset<16>> seats;
        
        for (vector<int> rSeat : reservedSeats) {
            if (rSeat[1] == 1 || rSeat[1] == 10)
                continue;   
            seats[rSeat[0]].set(rSeat[1]);
        }
        
        int res = 2*n;
        
        for (auto &[i, takenSeats] : seats) {
            
            //check from left oto right
            bool right = true, center = true, left = true;
            if (takenSeats.test(2) || takenSeats.test(3))
                left = false;
            if (takenSeats.test(4) || takenSeats.test(5) ) {
                left = false;
                center = false;
            }
            if (takenSeats.test(6) || takenSeats.test(7) ) {
                right = false;
                center = false;
            }
            if (takenSeats.test(8) || takenSeats.test(9)) {
                right = false;
            }
            
            if (right && left) {
                continue;
            } else if (right || left || center) {
                --res;   
            } else {
                res-=2;
            }
        }
        return res;
    }
};

int main() {
    CinemaSeatAllocation obj;
    vector<vector<int>> seats {{2,1},{1,8},{2,6}};
    cout<<obj.maxNumberOfFamilies(2, seats);
    return 0;
}