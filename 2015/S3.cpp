// 15/30 on DMOJ
// O(n^2) solution
#include <bits/stdc++.h>

using namespace std;

bool gates [(int)pow(10, 5)];
int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);

    int g, p;

    cin >> g >> p;

    // Fill the first g elements of the array with true
    memset(gates, true, g);

    int sum = 0;
    for (int i = 0; i < p; i++) {

        int plane;
        cin >> plane;

        for (int j = plane - 1; j >= 0; j--) {

            if (gates[j]) {
                gates[j] = false;
                sum++;
                break;
            }

            else if (j == 0) {
                cout << sum << "\n";
                return 0;
            }

        }

    }

    cout << sum << "\n";

    return 0;

}
