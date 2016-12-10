#ifndef NFA_IMPL
#define NFA_IMPL

#include "NFA.h"

NFA::NFA(const std::string& input_file)
{
    std::ifstream fin(input_file);

    // citire numar stari si alfabet
    fin>>statesNumber>>sigma;

    // redimensionare lista listelor de vecini
    M.resize(statesNumber);

    // stare initiala si stari finale
    int nrF;
    fin>>q0>>nrF;
    F.resize(nrF);

    for(int i=0;i<nrF;++i)
        fin>>F[i];

    // vecini
    int a,b,c;
    while(fin>>a>>b>>c)
    {
        M[a].push_back(std::make_pair(b,c));
    }

    fin.close();
}

void NFA::ToDFA(const std::string& output_file)
{
    std::ofstream fout(output_file);

    viz.clear();

    std::set<int> Q0;
    Q0.insert(q0);
    add_edges(Q0, q0, lambda);

    DFS(Q0);

    fout<<"Stari ("<<viz.size()<<"):\n";
    for(auto it : viz)
    {
        print(it, fout);
        fout<<"\n";
    }
    fout<<"\nAlfabet: 0.."<<sigma-1<<"\n";

    fout<<"\nStare initiala:\n";
    print(mask(Q0), fout);
    fout<<"\n";

    fout<<"\nStari finale ("<<nF.size()<<"):\n";
    for(auto m : nF)
    {
        print(m, fout);
        fout<<"\n";
    }
    fout<<"\n";

    fout<<"Automat:\n";

    for(auto it : G)
    {
        print(it.first.first, fout);
        fout<<" -> ";
        print(it.first.second, fout);
        fout<<" ("<<it.second<<")\n";
    }

    viz.clear();
    nF.clear();
    G.clear();

    fout.close();
}

void NFA::print(long long m, std::ostream& os)
{
    os<<"{ ";
    for(int i=0;i<64;++i)
    {
        if(m & (1LL<<i))
            os<<i<<" ";
    }
    os<<"}";
}

void NFA::add_edges(std::set<int>& Q, int q, int c)
{
    for(auto it : M[q])
    {
        if(it.second == c && Q.find(it.first) == Q.end())
        {
            Q.insert(it.first);
            add_edges(Q, it.first, lambda);
        }
    }
}

long long NFA::mask(std::set<int>& Q)
{
    long long m = 0;
    for(auto q : Q)
    {
        m |= (1<<q);
    }
    return m;
}

void NFA::isFinalState(std::set<int>& Q, int m)
{
    for(auto q : Q)
        for(auto f : F)
            if(q == f)
            {
                nF.push_back(m);
                return;
            }
}

void NFA::DFS(std::set<int> Q)
{
    long long m = mask(Q);

    if(viz.find(m) != viz.end())
        return;

    viz.insert(m);

    isFinalState(Q, m);

    std::set<int> Qaux;

    for(int i=0;i<sigma;++i)
    {
        Qaux.clear();

        for(auto q : Q)
        {
            add_edges(Qaux,q,i);
        }

        long long m2 = mask(Qaux);
        if(!Qaux.empty())
        {
            G.push_back(std::make_pair(std::make_pair(m, m2), i));

            DFS(Qaux);
        }
    }
}

#endif // NFA_IMPL
