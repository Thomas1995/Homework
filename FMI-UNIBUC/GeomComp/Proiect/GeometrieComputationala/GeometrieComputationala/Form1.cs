using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing;

namespace GeometrieComputationala
{
    public partial class MainForm : Form
    {
        List<Point> points = new List<Point>();
        List<Point> convexHull;

        public MainForm()
        {
            InitializeComponent();
        }

        private void panel_Paint(object sender, PaintEventArgs e)
        {
            foreach (Point point in points)
            {
                e.Graphics.DrawEllipse(new Pen(Brushes.White), 
                    new RectangleF(point.x, point.y, 1, 1));
            }

            if(convexHull != null)
            {
                for (int i = 0; i < convexHull.Count; ++i)
                {
                    int j = (i + 1) % convexHull.Count;
                    e.Graphics.DrawLine(new Pen(Brushes.Red), 
                        new PointF(convexHull[i].x, convexHull[i].y),
                        new PointF(convexHull[j].x, convexHull[j].y));
                }
            }
        }

        private void panel_MouseClick(object sender, MouseEventArgs e)
        {
            points.Add(new Point(e.X, e.Y));
            panel.Invalidate();
        }

        private void undoBtn_Click(object sender, EventArgs e)
        {
            points.RemoveAt(points.Count - 1);
            panel.Invalidate();
        }

        private void resetBtn_Click(object sender, EventArgs e)
        {
            convexHullBtn.Enabled = true;
            undoBtn.Enabled = true;
            convexHull = null;
            points.Clear();
            panel.Invalidate();
        }

        private void convexHullBtn_Click(object sender, EventArgs e)
        {
            convexHullBtn.Enabled = false;
            undoBtn.Enabled = false;
            convexHull = ConvexHull.compute(points);
            panel.Invalidate();
        }
    }
}
