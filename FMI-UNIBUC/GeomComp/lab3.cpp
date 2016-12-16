/*
	Aflati intersectia intre 2 segmente.
*/

#include <iostream>
#include <algorithm>
using namespace std;

struct point {
	int idx;
	double x, y;

	point() {}
	point(double X, double Y) : x(X), y(Y) {}

	bool operator < (const point& p) const {
		return x < p.x;
	}
};

bool isOnSegment(point P, point A, point B) {
	if(A.x == B.x) {
		if(P.y >= A.y && P.y <= B.y)
			return true;
		if(P.y <= A.y && P.y >= B.y)
			return true;
	}
	else {
		if(P.x >= A.x && P.x <= B.x)
			return true;
		if(P.x <= A.x && P.x >= B.x)
			return true;
	}

	return false;
}

int main() {
	point P[4];

	for(int i = 0; i < 4; ++i) {
		cin>>P[i].x>>P[i].y;
		P[i].idx = i / 2;
	}

	double a1, b1, c1, a2, b2, c2;

	if(P[0].y != P[1].y) {
		a1 = 1;
		b1 = -(P[0].x-P[1].x)/(P[0].y-P[1].y);
		c1 = -P[0].x-b1*P[0].y;
	}
	else {
		a1 = 0;
		b1 = 1;
		c1 = -P[0].y;
	}

	if(P[2].y != P[3].y) {
		a2 = 1;
		b2 = -(P[2].x-P[3].x)/(P[2].y-P[3].y);
		c2 = -P[2].x-b2*P[2].y;
	}
	else {
		a2 = 0;
		b2 = 1;
		c2 = -P[2].y;
	}

	double det1 = a1 * b2 - b1 * a2;

	if(det1 != 0) {
		int x = -(c1 * b2 - b1 * c2) / det1;
		int y = -(a1 * c2 - a2 * c1) / det1;

		if ( isOnSegment(point(x, y), P[0], P[1]) && isOnSegment(point(x, y), P[2], P[3]) ) {
			cout << "Intersectia este punctul ( "<<x<<" , "<<y<<" ).\n";
		}
		else {
			cout << "Intersectia este multimea vida.\n";
		}
	}
	else {
		double det2 = a1 * c2 - a2 * c1;
		double det3 = b1 * c2 - b2 * c1;

		if(det2 != 0 || det3 != 0) {
			cout << "Intersectia este multimea vida.\n";
		}
		else {
			sort(P, P + 4);

			if(P[0].idx == P[1].idx) {
				cout << "Intersectia este multimea vida.\n";
			}
			else {
				cout << "Intersectia este dreapta cu capetele "<<"("<<P[1].x<<","<<P[1].y<<") si ("<<P[2].x<<","<<P[2].y<<").\n";
			}
		}
	}

	return 0;
}
