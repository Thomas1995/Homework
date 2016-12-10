#include <iostream>
#include "DFA.h"
using namespace std;

int main()
{
    DFA A("DFA.data");

    cout<<A.Test("abaaceaf")<<"\n";
    cout<<A.Test("acefde")<<"\n";

    A.PrintAccesibleStates();

    return 0;
}
