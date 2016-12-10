#include "NFA.h"

NFA minimize(NFA A) {
  return A.reverse().ToDFA().reverse().ToDFA();
}

int main() {
  minimize(NFA("NFA.data")).printNFA();
  return 0;
}
