#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <assert.h>

void new(int fd, int m, int n) {
  assert(lseek(fd, 0, SEEK_SET) != -1);
  assert(write(fd, &m, sizeof(int)) != -1);
  assert(write(fd, &n, sizeof(int)) != -1);

  int i, j, zero = 0;
  for(i = 0; i < m; ++i)
    for(j = 0; j < n; ++j)
      assert(write(fd, &zero, sizeof(float)) != -1);
}

void signature(int fd, int* m, int* n) {
  assert(lseek(fd, 0, SEEK_SET) != -1);
  assert(read(fd, m, sizeof(int)) != -1);
  assert(read(fd, n, sizeof(int)) != -1);
}

float get(int fd, int i, int j) {
  int m, n;
  float x;
  signature(fd, &m, &n);

  off_t p = sizeof(int) * 2 + sizeof(float) * (i * n + j);
  assert(lseek(fd, p, SEEK_SET) != -1);
  assert(read(fd, &x, sizeof(float)) != -1);

  return x;
}

void set(int fd, int i, int j, float x) {
  int m, n;
  signature(fd, &m, &n);

  off_t p = sizeof(int) * 2 + sizeof(float) * (i * n + j);
  assert(lseek(fd, p, SEEK_SET) != -1);

  assert(write(fd, &x, sizeof(float)) != -1);
}
