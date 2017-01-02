#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <errno.h>
#include <string.h>
#include <sys/stat.h>

off_t fsize(const char path[]) {
  struct stat st;

  if (stat(path, &st) == 0)
    return st.st_size;

  printf("Cannot determine size of %s: %s\n",
    path, strerror(errno));

  return -1;
}

off_t processDirectory(const char path[]) {
  off_t totalSize = 0;

  DIR* crtDir = opendir(path);
  size_t pathSize = strlen(path);

  if(crtDir == NULL) {
    printf("Error opening directory %s: %s\n",
      path, strerror(errno));
    exit(1);
  }

  struct dirent* ent;

  do {
    errno = 0;
    ent = readdir(crtDir);

    if(ent == NULL) {
      if(errno != 0) {
        printf("Error reading from directory %s: %s\n",
          path, strerror(errno));
        exit(1);
      }
      else
        break;
    }

    if(!strcmp(ent->d_name, ".") || !strcmp(ent->d_name, ".."))
      continue;

    size_t dirSize = strlen(ent->d_name);
    char newPath[pathSize + dirSize + 2];

    strncpy(newPath, path, pathSize);
    strncpy(newPath + pathSize, ent->d_name, dirSize);

    if(ent->d_type == DT_DIR) {
      newPath[pathSize + dirSize] = '/';
      newPath[pathSize + dirSize + 1] = 0;

      totalSize += processDirectory(newPath);
    }

    if(ent->d_type == DT_REG) {
      newPath[pathSize + dirSize] = 0;
      totalSize += fsize(newPath);
    }

  } while(1);

  return totalSize;
}

int main(int argc, char** argv) {
  if(argc != 2) {
    printf("The program needs one argument.\n");
    exit(1);
  }

  size_t argSize = strlen(argv[1]);

  char argDir[argSize + 2];
  strncpy(argDir, argv[1], argSize);

  if(argDir[argSize - 1] != '/') {
    argDir[argSize] = '/';
    ++argSize;
  }

  argDir[argSize] = 0;

  printf("Total size of all files in %s is %jd bytes.\n",
    argDir, processDirectory(argDir));

  return 0;
}
