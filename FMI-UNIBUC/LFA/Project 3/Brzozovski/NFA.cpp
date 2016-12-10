#include <unordered_map>
#include "NFA.h"

int NFA::getStatesNumber() {
  return statesNumber;
}

void NFA::printNFA() {
    std::cout<<"Numar stari: "<<statesNumber<<"\n";
    std::cout<<"Alfabet: 0.."<<sigma-1<<"\n";
    std::cout<<"Stari initiale: ";
    for(auto it : q0)
      std::cout<<it<<" ";
    std::cout<<"\n";
    std::cout<<"Stari finale: ";
    for(auto it : F)
      std::cout<<it<<" ";
    std::cout<<"\n";
    for(int i=0;i<statesNumber;++i) {
      for(auto p : M[i]) {
        std::cout<<i<<" -> "<<p.first<<" ("<<(char)(p.second+'a')<<")\n";
      }
    }
}

NFA NFA::reverse() {
  std::vector<std::vector< std::pair<int, int> >> newM;

  for(int i=0;i<statesNumber;++i)
    newM.push_back(std::vector< std::pair<int, int> >());

  for(int i=0;i<statesNumber;++i) {
    for(auto p : M[i]) {
      newM[p.first].push_back(std::make_pair(i, p.second));
    }
  }

  return NFA(statesNumber, sigma, q0, F, newM);
}

NFA::NFA(int _statesNumber, int _sigma, std::vector<int> _F, std::vector<int> _q0,
  std::vector<std::vector< std::pair<int, int> >> _M) {

  statesNumber = _statesNumber;
  sigma = _sigma;
  F = _F;
  q0 = _q0;
  M = _M;
}

NFA::NFA(const std::string& input_file) {
    std::ifstream fin(input_file);

    // citire numar stari si alfabet
    fin>>statesNumber>>sigma;

    // redimensionare lista listelor de vecini
    M.resize(statesNumber);

    // stare initiala si stari finale
    int q;
    fin>>q;
    q0.push_back(q);

    int nrF;
    fin>>nrF;
    F.resize(nrF);

    for(int i=0;i<nrF;++i)
        fin>>F[i];

    // vecini
    int a,b,c;
    while(fin>>a>>b>>c) {
        M[a].push_back(std::make_pair(b,c));
    }

    fin.close();
}

NFA NFA::ToDFA() {
    viz.clear();

    std::set<int> Q0;
    for(auto it : q0) {
      Q0.insert(it);
      add_edges(Q0, it, lambda);
    }

    DFS(Q0);

    std::unordered_map<long long, int> hash;
    int n = 0;

    for(auto it : viz) {
        hash[it] = n++;
    }

    int q = hash.find(mask(Q0))->second;
    std::vector<int> newQ0;
    newQ0.push_back(q);

    std::vector<int> newF;
    for(auto m : nF) {
      newF.push_back(hash.find(m)->second);
    }

    std::vector<std::vector< std::pair<int, int> >> newM;

    for(int i=0;i<n;++i) {
      newM.push_back(std::vector< std::pair<int, int> >());
    }
    for(auto it : G) {
      int a,b,c;
      a = hash.find(it.first.first)->second;
      b = hash.find(it.first.second)->second;
      c = it.second;

      newM[a].push_back(std::make_pair(b,c));
    }

    viz.clear();
    nF.clear();
    G.clear();

    return NFA(n, sigma, newF, newQ0, newM);
}

void NFA::add_edges(std::set<int>& Q, int q, int c) {
    for(auto it : M[q]) {
        if(it.second == c && Q.find(it.first) == Q.end()) {
            Q.insert(it.first);
            add_edges(Q, it.first, lambda);
        }
    }
}

long long NFA::mask(std::set<int>& Q) {
    long long m = 0;
    for(auto q : Q) {
        m |= (1<<q);
    }
    return m;
}

void NFA::isFinalState(std::set<int>& Q, int m) {
    for(auto q : Q)
        for(auto f : F)
            if(q == f) {
                nF.push_back(m);
                return;
            }
}

void NFA::DFS(std::set<int> Q) {
    long long m = mask(Q);

    if(viz.find(m) != viz.end())
        return;

    viz.insert(m);

    isFinalState(Q, m);

    std::set<int> Qaux;

    for(int i=0;i<sigma;++i) {
        Qaux.clear();

        for(auto q : Q) {
            add_edges(Qaux,q,i);
        }

        long long m2 = mask(Qaux);
        if(!Qaux.empty()) {
            G.push_back(std::make_pair(std::make_pair(m, m2), i));

            DFS(Qaux);
        }
    }
}
