#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>
#include <limits.h>

int mymkstemp(char* template) {
  int templateSize = strlen(template);

  if(templateSize < 6) {
    errno = EINVAL;
    return -1;
  }

  int i;
  for(i = 1; i <= 6; ++i)
    if(template[templateSize - i] != 'X') {
      errno = EINVAL;
      return -1;
    }

  time_t t;
  srand((unsigned) time(&t));

  int characterSetSize = ('Z' - 'A' + 1) + ('z' - 'a' + 1) + ('9' - '0' + 1);
  long long step = 32416189391LL;
  long long possibilites = 1LL;

  for(i = 1; i <= 6; ++i)
    possibilites *= characterSetSize;

  long long randPos = (1LL * INT_MAX * rand() + rand()) % possibilites + 1;

  long long li;
  for(li = 1; li <= possibilites; ++li) {
    long long randPosCopy = randPos;

    for(i = 1; i <= 6; ++i) {
      int randint = randPosCopy % characterSetSize;
      char randchar;

      if(randint <= 'Z' - 'A')
        randchar = randint + 'A';
      else {
        randint -= 'Z' - 'A' + 1;
        if(randint <= 'z' - 'a')
          randchar = randint + 'a';
        else {
          randint -= 'z' - 'a' + 1;
          randchar = randint + '0';
        }
      }

      template[templateSize - i] = randchar;

      randPosCopy /= characterSetSize;
    }

    if(access(template, F_OK) == -1)
      return open(template, O_EXCL | O_CREAT, 0600);

    randPos = (randPos + step) % possibilites + 1;
  }

  errno = EEXIST;
  return -1;
}

int main() {
  char template[] = "abcXXXXXX";

  int fd = mymkstemp(template);

  if(fd == -1)
    printf("Error in mymkstemp: %s\n", strerror(errno));
  else
    printf("A file with %s name was created.\n", template);

  return 0;
}
