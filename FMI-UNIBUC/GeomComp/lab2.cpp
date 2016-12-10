/*
Fie patrulaterul A1, A2, A3, A4, unde Ai(xi,yi) apartine R^2 a.i. oricare 3 puncte sunt necoliniare.
a) Sa se decida daca patrulaterul este convex.
b) Sa se decida daca un punct dat apartine acoperirii convexe a multimii {A1, A2, A3, A4}.
*/

#include <iostream>
#include <cmath>
using namespace std;

struct vect {
  int x, y;
};

vect p[4];

double cross_product(vect O, vect A, vect B) {
    return (A.x - O.x) * (B.y - O.y) - (B.x - O.x) * (A.y - O.y);
}

int lastsgn = 0;

bool solveConvex(int idx) {
  int sgn = cross_product(p[idx], p[(idx + 1) % 4], p[(idx + 2) % 4]);
  if(sgn * lastsgn < 0) {
    return false;
  }
  lastsgn = sgn;
  return true;
}

double computeArea(vect A, vect B, vect C) {
  return 0.5f * fabs( A.x * B.y + A.y * C.x + B.x * C.y - B.y * C.x - A.x * C.y - A.y * B.x);
}

bool solveArea(int idx, vect P) {
  double A = computeArea(p[idx], p[(idx + 1) % 4], p[(idx + 2) % 4]);
  double A1 = computeArea(P, p[(idx + 1) % 4], p[(idx + 2) % 4]);
  double A2 = computeArea(p[idx], P, p[(idx + 2) % 4]);
  double A3 = computeArea(p[idx], p[(idx + 1) % 4], P);

  if(A1 + A2 + A3 == A) {
    return true;
  }

  return false;
}

int main() {
  int i = 0;
  for(int i = 0; i < 4; ++i) {
    cin >> p[i].x >> p[i].y;
  }

  bool isConvex = true;
  for(int i = 0; i < 4; ++i) {
    isConvex &= solveConvex(i);
  }

  if(isConvex) {
    cout << "Patrulaterul este convex.\n";
  }
  else {
    cout << "Patrulaterul nu este convex.\n";
  }

  bool ok = false;
  vect o;
  cin >> o.x >> o.y;

  for(int i = 0; i < 4; ++i) {
    if(solveArea(i, o)) {
      cout << "Punctul este in interior.\n";
      ok = true;
      break;
    }
  }

  if(!ok) {
    cout << "Punctul nu este in interior.\n";
  }

  return 0;
}
