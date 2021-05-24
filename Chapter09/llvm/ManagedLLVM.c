#include <stdlib.h>
#include <stdio.h>
int main() {
  char *string;
  char valueStr[10] = "Hello Graaaaaaal";
  strcpy(string, valueStr);
  printf("%s", string);
  free(string);
  return 0;
}