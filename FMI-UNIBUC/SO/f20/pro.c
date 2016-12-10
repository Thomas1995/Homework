#include "f20.h"

int main(int argc, char** argv) {
  if(argc != 3) {
    exit(1);
  }

  int fd = creat(argv[1], 0);

  if(fd == -1) {
    exit(1);
  }

  new(fd, atoi(argv[2]), atoi(argv[3]));

  return 0;
}
