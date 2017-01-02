#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>

int read_next_int(int fd, int* i) {
  ssize_t bytes;
  char c;
  int sign = 1;
  int digits = 0;
  *i = 0;

  while(1) {
    bytes = read(fd, &c, sizeof(char));

    if(bytes == -1) {
      printf("Error reading from file: %s\n", strerror(errno));
      exit(1);
    }

    if(bytes == 0)
      break;

    if(c == '-') {
      sign = -1;
      continue;
    }

    if(c >= '0' && c <= '9') {
      ++digits;
      (*i) *= 10;
      (*i) += c - '0';
      continue;
    }

    break;
  }

  (*i) *= sign;
  return digits;
}

int main(int argc, char** argv) {
  if(argc != 3) {
    printf("The program needs two arguments.\n");
    exit(1);
  }

  int fdTextFile = open(argv[1], O_RDONLY);
  int fdBinaryFile = open(argv[2], O_RDWR | O_CREAT, 0666);

  if(fdTextFile == -1) {
    printf("Could not open %s: %s\n", argv[1], strerror(errno));
    exit(1);
  }

  if(fdBinaryFile == -1) {
    printf("Could not open %s: %s\n", argv[2], strerror(errno));
    exit(1);
  }

  int nr;
  while(read_next_int(fdTextFile, &nr)) {
    int bytes = write(fdBinaryFile, &nr, sizeof(nr));
    if(bytes == -1) {
      printf("Error writing in file: %s\n", strerror(errno));
      exit(1);
    }
  }

  close(fdTextFile);
  close(fdBinaryFile);

  return 0;
}
