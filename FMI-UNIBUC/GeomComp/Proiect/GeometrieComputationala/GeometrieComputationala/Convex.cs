
using System;
using System.Collections.Generic;
using System.Linq;

struct Point
{
	public float x, y;

	public Point(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	static public float cross (Point A, Point B)
	{
		return A.x * B.y - B.x * A.y;
	}

	static public float cross (Point A, Point B, Point C)
	{
		return cross(B - A, C - B);
	}

	static public Point operator- (Point src, Point other)
	{
		return new Point(src.x - other.x, src.y - other.y);
	}

	static public bool operator== (Point A, Point B)
	{
		return A.x == B.x && A.y == B.y;
	}

	static public bool operator!= (Point A, Point B)
	{
		return A.x != B.x || A.y != B.y;
	}

	override public string ToString()
	{
		return base.ToString() + ": " + x.ToString() + " " + y.ToString();
	}
}


class ConvexHull
{
	public static List<Point> compute(List<Point> points)
	{
		int n = points.Count;
		int best = 0;
		for(int i = 0; i < n; ++i)
		{
			if (points[i].x < points[best].x)
			{
				best = i;
			}
		}

		List<int> ret = new List<int>();
		ret.Add(best);

		int last = best;

		while(true)
		{
			int next = (last + 1) % n;
			for(int i = 0; i < n; ++i)
			{

				float cross = Point.cross(points[last], points[i], points[next]);
				if (cross > 0)
				{
					next = i;
				}
			}

			if (next != ret.First())
			{
				ret.Add(next);
				last = next;
			}
			else
			{
				break;
			}
		}

		List<Point> pointRet = new List<Point>();
		foreach(int i in ret)
		{
			pointRet.Add(points[i]);
		}
		return pointRet;
	}

	/*static void Main()
	{
		List<Point> points = new List<Point>();
		points.Add(new Point(0, 0));
		points.Add(new Point(10, 10));
		points.Add(new Point(10, 0));
		points.Add(new Point(9, 5));

		List<Point> hull = compute(points);

		hull.ForEach(delegate(Point p) 
				{
					Console.WriteLine(p.ToString());
				});
	}*/
}

