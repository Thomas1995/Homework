#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>

int main(int argc, char** argv) {
  if(argc != 3) {
    printf("The program needs two arguments.\n");
    exit(1);
  }

  int fdBinaryFile = open(argv[2], O_RDWR | O_CREAT, 0666);

  if(fdBinaryFile == -1) {
    printf("Could not open %s: %s\n", argv[2], strerror(errno));
    exit(1);
  }

  int m, n;
  float nr;

  FILE* f = fopen(argv[1], "r");

  if(f == NULL) {
    printf("Could not open %s: %s\n", argv[1], strerror(errno));
    exit(1);
  }

  fscanf(f, "%d %d", &m, &n);
  write(fdBinaryFile, &m, sizeof(m));
  write(fdBinaryFile, &n, sizeof(n));

  while(!feof(f)) {
    fscanf(f, "%f", &nr);
    write(fdBinaryFile, &nr, sizeof(nr));
  }

  close(fdBinaryFile);

  return 0;
}
