#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>

#define STDIN 0
#define STDOUT 1
#define STDERR 2

void writeNumber(int nr, int isNegative, int fd) {
  if(isNegative) {
    nr = -nr;
  }

  write(fd, &nr, sizeof(nr));
}

int main(int argc, char** argv) {
  if(argc != 3) {
    exit(1);
  }

  int fdTextFile = open(argv[1], O_RDONLY);
  int fdBinaryFile = creat(argv[2], 0);

  if(fdTextFile == -1) {
    exit(1);
  }

  if(fdBinaryFile == -1) {
    exit(1);
  }

  char c;
  int negative = 0;
  int separator = 0;
  int nr = 0;

  while(read(fdTextFile, &c, 1) > 0) {
    if(c == '-') {
      negative = 1;
      continue;
    }

    if(c >= '0' && c <= '9') {
      separator = 0;
      nr = nr * 10 + c - '0';
      continue;
    }

    if(!separator) {
      writeNumber(nr, negative, fdBinaryFile);
      negative = nr = 0;
      separator = 1;
    }
  }

  if(!separator) {
    writeNumber(nr, negative, fdBinaryFile);
  }

  close(fdTextFile);
  close(fdBinaryFile);

  return 0;
}
