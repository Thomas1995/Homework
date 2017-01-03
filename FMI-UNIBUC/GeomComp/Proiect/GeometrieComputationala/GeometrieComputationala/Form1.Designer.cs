namespace GeometrieComputationala
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.panel = new System.Windows.Forms.Panel();
            this.menu = new System.Windows.Forms.Panel();
            this.undoBtn = new System.Windows.Forms.Button();
            this.resetBtn = new System.Windows.Forms.Button();
            this.convexHullBtn = new System.Windows.Forms.Button();
            this.menu.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel
            // 
            this.panel.BackColor = System.Drawing.Color.Black;
            this.panel.Location = new System.Drawing.Point(0, 0);
            this.panel.Name = "panel";
            this.panel.Size = new System.Drawing.Size(785, 444);
            this.panel.TabIndex = 0;
            this.panel.Paint += new System.Windows.Forms.PaintEventHandler(this.panel_Paint);
            this.panel.MouseClick += new System.Windows.Forms.MouseEventHandler(this.panel_MouseClick);
            // 
            // menu
            // 
            this.menu.Controls.Add(this.convexHullBtn);
            this.menu.Controls.Add(this.resetBtn);
            this.menu.Controls.Add(this.undoBtn);
            this.menu.Location = new System.Drawing.Point(0, 444);
            this.menu.Name = "menu";
            this.menu.Size = new System.Drawing.Size(785, 53);
            this.menu.TabIndex = 1;
            // 
            // undoBtn
            // 
            this.undoBtn.Location = new System.Drawing.Point(12, 6);
            this.undoBtn.Name = "undoBtn";
            this.undoBtn.Size = new System.Drawing.Size(149, 38);
            this.undoBtn.TabIndex = 0;
            this.undoBtn.Text = "Undo";
            this.undoBtn.UseVisualStyleBackColor = true;
            this.undoBtn.Click += new System.EventHandler(this.undoBtn_Click);
            // 
            // resetBtn
            // 
            this.resetBtn.Location = new System.Drawing.Point(177, 6);
            this.resetBtn.Name = "resetBtn";
            this.resetBtn.Size = new System.Drawing.Size(149, 38);
            this.resetBtn.TabIndex = 1;
            this.resetBtn.Text = "Reset";
            this.resetBtn.UseVisualStyleBackColor = true;
            this.resetBtn.Click += new System.EventHandler(this.resetBtn_Click);
            // 
            // convexHullBtn
            // 
            this.convexHullBtn.Location = new System.Drawing.Point(623, 6);
            this.convexHullBtn.Name = "convexHullBtn";
            this.convexHullBtn.Size = new System.Drawing.Size(149, 38);
            this.convexHullBtn.TabIndex = 2;
            this.convexHullBtn.Text = "Compute convex hull";
            this.convexHullBtn.UseVisualStyleBackColor = true;
            this.convexHullBtn.Click += new System.EventHandler(this.convexHullBtn_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 498);
            this.Controls.Add(this.menu);
            this.Controls.Add(this.panel);
            this.Name = "MainForm";
            this.Text = "Geometrie Computationala";
            this.menu.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel;
        private System.Windows.Forms.Panel menu;
        private System.Windows.Forms.Button convexHullBtn;
        private System.Windows.Forms.Button resetBtn;
        private System.Windows.Forms.Button undoBtn;
    }
}

