/*
Se dau punctele A1, A2, A3 in R^3.
a) Sa se decida daca punctele sunt coliniare
b) In caz afirmativ, sa se exprime unul dintre ele ca o combinatie afina de celelalte 2.
*/

#include <iostream>
using namespace std;

struct vect {
  int x, y, z;
};

void dif(vect& l, const vect& p1, const vect& p2) {
  l.x = p2.x - p1.x;
  l.y = p2.y - p1.y;
  l.z = p2.z - p1.z;
}

/*
|v1 w1 e1|
|v2 w2 e2|
|v3 w3 e3|
*/

vect cross_product(const vect& l1, const vect& l2) {
  vect ret;

  ret.x = l1.y * l2.z - l1.z * l2.y;
  ret.y = l2.x * l1.z - l2.z * l1.x;
  ret.z = l1.x * l2.y - l1.y * l2.x;

  return ret;
}

int main() {
  vect p[3];
  int i = 0;
  for(int i = 0; i < 3; ++i) {
    cin >> p[i].x >> p[i].y >> p[i].z;
  }

  vect l1, l2;
  dif(l1, p[0], p[1]);
  dif(l2, p[1], p[2]);
  vect cp = cross_product(l1, l2);

  if(cp.x == 0 && cp.y == 0 && cp.z == 0) {
    cout << "Punctele sunt coliniare.\n";

    if(p[0].x == p[1].x && p[0].y == p[1].y && p[0].z == p[1].z) {
      cout << "A = " << 1 << " * B + " << 0 << " * C\n";
    }
    else {
      double r = 0;

      if(l1.x != 0)
        r = (double)l2.x / l1.x;

      if(l1.y != 0)
        r = (double)l2.y / l1.y;

      if(l1.z != 0)
        r = (double)l2.z / l1.z;

      cout << "C = " << r + 1 << " * B - " << r << " * A\n";
    }
  }
  else {
    cout << "Punctele nu sunt coliniare\n";
  }

  return 0;
}
