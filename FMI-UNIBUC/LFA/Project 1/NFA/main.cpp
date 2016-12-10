#include <fstream>
#include "NFA.h"
using namespace std;

int main()
{
    NFA A("NFA.data");
    A.ToDFA("DFA.data");

    NFA A2("NFA2.data");
    A2.ToDFA("DFA2.data");

    return 0;
}
