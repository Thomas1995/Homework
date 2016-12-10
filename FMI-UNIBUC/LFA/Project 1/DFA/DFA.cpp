#ifndef DFA_IMPL
#define DFA_IMPL

#include "DFA.h"

DFA::DFA(const std::string& path)
{
    std::ifstream fin(path);

    // citire numar stari si alfabet
    fin>>statesNumber>>sigma;

    // redimensionare matrice
    M.resize(statesNumber);
    for(int i=0;i<statesNumber;++i)
    {
        M[i].resize(sigma);
        for(int j=0;j<sigma;++j)
            M[i][j] = -1;
    }

    // stare initiala si stari finale
    int nrF;
    fin>>q0>>nrF;
    F.resize(nrF);

    for(int i=0;i<nrF;++i)
        fin>>F[i];

    // tabel
    int a,b,c;
    while(fin>>a>>b>>c)
    {
        M[a][b] = c;
    }

    fin.close();
}

bool DFA::Test(const std::string& input) const
{
    // initializare stare initiala
    int state = q0;

    // interpretare input
    for(auto ch : input)
    {
        int c = (int)ch-'a';

        state = M[state][c];

        if(state == -1)
            return false;
    }

    // verificare stari finale
    for(auto it : F)
        if(state == it)
            return true;

    return false;
}

void DFA::PrintAccesibleStates(std::ostream& os)
{
    // initializare accessibleStates, in caz ca aceasta nu a fost facuta
    if(!accessibleStatesInit)
        AccesibleStates();

    // afisare stari disponibile
    for(auto it : accessibleStates)
        os<<it<<" ";
    os<<"\n";
}

void DFA::AccesibleStates()
{
    accessibleStatesInit = true;

    // foloseste temporar un vector de vizitari
    viz.resize(statesNumber);

    // initializare vector de vizitari
    for(int i=0;i<statesNumber;++i)
        viz[i] = false;

    DFS(q0);

    // verificare stari accesibile
    for(int i=0;i<statesNumber;++i)
    {
        if(viz[i])
            accessibleStates.push_back(i);
    }

    // goleste vectorul de vizitari
    viz.clear();
}

void DFA::DFS(const int& x)
{
    viz[x] = true;
    for(int i=0;i<sigma;++i)
        if(M[x][i] != -1 && !viz[M[x][i]])
            DFS(M[x][i]);
}

#endif // DFA_IMPL

