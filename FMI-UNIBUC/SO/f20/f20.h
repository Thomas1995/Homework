#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

#define STDIN 0
#define STDOUT 1
#define STDERR 2

void new(int fd, int m, int n) {
  write(fd, &m, sizeof(int));
  write(fd, &n, sizeof(int));

  int i, j, zero = 0;
  for(i = 0; i < m; ++i)
    for(j = 0; j < n; ++j)
      write(fd, &zero, sizeof(int));
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

  printf("%f\n", x);

  return x;
}

void set(int fd, int i, int j, float x) {

}
