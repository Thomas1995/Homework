/*
Fie A1A2A3A4 un patrulater convex Ai(xi,yi) apartinand lui R^2.
a) Sa se precizeze pozitia punctului A4 fata de cercul circumscris triunghiului A1A2A3.
b) Sa se decida daca patrulaterul A1A2A3A4 este circumscriptibil.
*/

#include <iostream>
#include <cmath>
using namespace std;

struct Vector2D {
  double x, y;
};

// > 180 interior
// = 180 pe cerc
// < 180 exterior

double distBetween(Vector2D A, Vector2D B) {
  return sqrt((A.x-B.x)*(A.x-B.x) + (A.y-B.y)*(A.y-B.y));
}

double norm(Vector2D A) {
  return sqrt(A.x * A.x + A.y * A.y);
}

double cosBetween(Vector2D A, Vector2D B, Vector2D O) {
  A.x -= O.x;
  A.y -= O.y;
  B.x -= O.x;
  B.y -= O.y;
  double dotProduct = A.x * B.x + A.y * B.y;
  dotProduct /= norm(A) * norm(B);
  return dotProduct;
}

int main() {
  Vector2D A[4];

  for(int i = 0; i < 4; ++i) {
    cin >> A[i].x >> A[i].y;
  }

  double c1 = cosBetween(A[0], A[2], A[1]);
  double c2 = cosBetween(A[0], A[2], A[3]);
  double sum = c1 + c2;

  if(abs(sum) < 1e-9) {
    cout << "Punctul A4 este pe cerc.\n";
  } else
  if(sum > 0) {
    cout << "Punctul A4 este in exteriorul cercului.\n";
  } else
  if(sum < 0) {
    cout << "Punctul A4 este in interiorul cercului.\n";
  }

  double s1 = distBetween(A[0], A[1]) + distBetween(A[1], A[2]);
  double s2 = distBetween(A[0], A[3]) + distBetween(A[3], A[2]);

  if(abs(s1 - s2) < 1e-9) {
    cout << "Patrulaterul e circumscriptibil.\n";
  }
  else {
    cout << "Patrulaterul nu e circumscriptibil.\n";
  }

  return 0;
}
