#include "f20.h"
#include <stdio.h>

int main(int argc, char** argv) {
  if(argc != 4) {
    printf("try: pro f1 f2 f\n");
    exit(1);
  }

  int fdSrc1 = open(argv[1], O_RDONLY);
  if(fdSrc1 == -1) {
    printf("Cannot open f1: %s\n", strerror(errno));
    exit(1);
  }

  int fdSrc2 = open(argv[2], O_RDONLY);
  if(fdSrc2 == -1) {
    printf("Cannot open f2: %s\n", strerror(errno));
    exit(1);
  }

  int m1, n1, m2, n2;
  signature(fdSrc1, &m1, &n1);
  signature(fdSrc2, &m2, &n2);

  if(n2 != m1) {
    printf("Cannot make the product of the input matrices\n");
    exit(1);
  }

  int fdDest = open(argv[3], O_RDWR | O_CREAT, 0666);
  if(fdDest == -1) {
    printf("Cannot open f: %s\n", strerror(errno));
    exit(1);
  }

  int m = m1, n = n2;

  new(fdDest, m, n);

  int i, j, k, a, b;
  for(i = 0; i < m1; ++i)
    for(j = 0; j < n2; ++j) {
      float sum = 0;
      for(k = 0; k < n1; ++k) {
        a = get(fdSrc1, i, k);
        b = get(fdSrc2, k, j);
        sum += a * b;
      }

      set(fdDest, i, j, sum);
    }

  close(fdSrc1);
  close(fdSrc2);
  close(fdDest);

  return 0;
}
