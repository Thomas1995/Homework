#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>

void new(int fd, int m, int n) {
  lseek(fd, 0, SEEK_SET);
  write(fd, &m, sizeof(int));
  write(fd, &n, sizeof(int));

  int i, j, zero = 0;
  for(i = 0; i < m; ++i)
    for(j = 0; j < n; ++j)
      write(fd, &zero, sizeof(float));
}

void signature(int fd, int* m, int* n) {
  lseek(fd, 0, SEEK_SET);
  read(fd, m, sizeof(int));
  read(fd, n, sizeof(int));
}

float get(int fd, int i, int j) {
  int m, n;
  float x;
  signature(fd, &m, &n);

  off_t p = sizeof(int) * 2 + sizeof(float) * (i * n + j);
  lseek(fd, p, SEEK_SET);
  read(fd, &x, sizeof(float));

  return x;
}

void set(int fd, int i, int j, float x) {
  int m, n;
  signature(fd, &m, &n);
  printf("in function: %d %d\n", m, n);

  /*off_t p = sizeof(int) * 2 + sizeof(float) * (i * n + j);
  lseek(fd, p, SEEK_SET);

  write(fd, &x, sizeof(float));*/
}
