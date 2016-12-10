#ifndef NFA_IMPL
#define NFA_IMPL

#include "NFA.h"
#include "matrix.cpp"

const std::string NFA::lambda_str = "0";
const char NFA::lambda = lambda_str[0];

NFA::NFA(const std::string& input_file) {
    std::ifstream fin(input_file);

    // citire numar stari si alfabet
    fin>>statesNumber>>sigma;

    // redimensionare lista listelor de vecini
    M.resize(statesNumber);

    // stare initiala si stari finale
    int nrF;
    fin>>q0>>nrF;
    F.resize(nrF);

    for(int i=0;i<nrF;++i) {
        fin>>F[i];
        check(F[i]);
    }

    // vecini
    int a,b;
    char c;
    while(fin>>a>>b>>c)
    {
        check(a);
        check(b);
        M[a].push_back(std::make_pair(b,c));
    }

    fin.close();
}

void NFA::check(int& state) {
    if(state == 0)
      state = q0;
    else
      if(state == q0)
        state = 0;
}

std::string NFA::ToRegEx() {
    Matrix A(statesNumber, statesNumber), B(statesNumber, 1);

    for(int state=0;state<statesNumber;++state) {
        for(auto it : M[state]) {
            A.add(state, it.first, it.second);
        }
    }

    for(auto f : F) {
        B.add(f, 0, lambda);
    }

    for(int i=0;i<statesNumber-1;++i) {
        A.init(B);

        B = B + A.lastCol() * B.lastLine();
        A = A + A.lastCol() * A.lastLine();

        A.resize();
        B.resize();
    }
    A.init(B);

    return B.getRegEx();
}

#endif // NFA_IMPL
