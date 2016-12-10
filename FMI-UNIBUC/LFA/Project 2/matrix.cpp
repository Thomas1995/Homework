#include <string>
#include <vector>

class Matrix {

    class MatrixCell {

    public:
        std::string expr;

        MatrixCell operator + (const MatrixCell& c) {
            MatrixCell m;

            if(expr.empty() || c.expr.empty())
                m.expr = expr + c.expr;
            else
                m.expr = expr + "+" + c.expr;

            return m;
        }

        MatrixCell operator + (const char& c) {
            MatrixCell m;

            if(!expr.empty())
                m.expr = expr + "+";

            m.expr.push_back(c);

            return m;
        }

        MatrixCell operator * (const MatrixCell& c) {
            MatrixCell m;

            if(!expr.empty() && !c.expr.empty())
                m.expr = process(expr) + process(c.expr);

            return m;
        }

        void concat(const MatrixCell& a, const MatrixCell& b) {
            if(!a.expr.empty() && !b.expr.empty())
                expr = process(a.expr) + "*" + process(b.expr);
        }

    private:

        std::string process(std::string str) {
            if(str.empty() || str == NFA::lambda_str)
                return "";
            if(str.size() == 1)
                return str;
            return "(" + str + ")";
        }
    };

    std::vector<std::vector<MatrixCell>> M;

public:
    Matrix(int size = 0, int size2 = 0) {
        M.resize(size);
        for(int i=0;i<size;++i)
            M[i].resize(size2);
    }

    void add(int i, int j, char c) {
        M[i][j] = M[i][j] + c;
    }

    Matrix operator + (const Matrix& c) {
        Matrix m(M.size(), M[0].size());

        for(int i=0;i<M.size();++i)
            for(int j=0;j<M[i].size();++j) {
                m.M[i][j] = M[i][j] + c.M[i][j];
            }

        return m;
    }

    Matrix operator * (const Matrix& c) {
        Matrix m(M.size(), c.M[0].size());

        for(int i=0;i<m.M.size();++i)
          for(int j=0;j<m.M[i].size();++j)
            for(int i2=0;i2<M[i].size();++i2)
              for(int j2=0;j2<c.M.size();++j2)
                  m.M[i][j] = m.M[i][j] + M[i][i2] * c.M[j2][j];

        return m;
    }

    Matrix lastLine() {
        Matrix m(1, M.size());

        m.M[0] = M.back();

        return m;
    }

    Matrix lastCol() {
        Matrix m(M.size(), 1);

        for(int i=0;i<M.size();++i)
            m.M[i][0] = M[i].back();

        return m;
    }

    void init(Matrix& other) {
        int n = M.size() - 1;
        if(!M[n][n].expr.empty()) {
            for(int i=0;i<n;++i) {
                M[n][i].concat(M[n][n], M[n][i]);
            }

            other.M[n][0].concat(M[n][n], other.M[n][0]);
        }
    }

    void resize() {
        if(M.size() == 1)
            return;

        if(M[0].size() == M.size()) {
            for(int i=0;i<M.size();++i)
                M[i].resize(M[i].size() - 1);
        }

        M.resize(M.size() - 1);
    }

    std::string getRegEx() {
        return M[0][0].expr;
    }

    void print() {
        for(int i=0;i<M.size();++i) {
            for(int j=0;j<M[i].size();++j)
                std::cout<<M[i][j].expr<<" ";
            std::cout<<"\n";
        }
    }
};
