#include <fstream>
#include "NFA.h"
using namespace std;

int main() {
    NFA A("NFA.data");
    cout<<A.ToRegEx()<<endl;

    return 0;
}
