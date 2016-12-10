#include "f20.h"
#include <stdio.h>

int main(int argc, char** argv) {
  if(argc != 4) {
    perror("try: sum f1 f2 f");
    exit(1);
  }

  int fdSrc1 = open(argv[1], O_RDONLY);
  if(fdSrc1 == -1) {
    perror("Cannot open f1");
    exit(1);
  }

  int fdSrc2 = open(argv[2], O_RDONLY);
  if(fdSrc2 == -1) {
    perror("Cannot open f2");
    exit(1);
  }

  int m1, n1, m2, n2;
  signature(fdSrc1, &m1, &n1);
  signature(fdSrc2, &m2, &n2);

  printf("%d %d\n%d %d\n", m1, n1, m2, n2);

  if(m1 != m2 || n1 != n2) {
    perror("Cannot make the sum of the input matrixes");
    exit(1);
  }

  int fdDest = creat(argv[3], O_WRONLY);
  if(fdDest == -1) {
    perror("Cannot open f");
    exit(1);
  }

  int m = m1, n = n1;

  new(fdDest, m, n);

  int i, j, a, b;
  for(i = 0; i < m; ++i) {
    for(j = 0; j < n; ++j) {
      a = get(fdSrc1, i, j);
      b = get(fdSrc2, i, j);
      set(fdDest, i, j, a + b);
    }
  }

  close(fdSrc1);
  close(fdSrc2);
  close(fdDest);

  return 0;
}
