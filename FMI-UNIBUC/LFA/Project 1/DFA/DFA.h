#ifndef DFA_H
#define DFA_h

#include <vector>
#include <string>
#include <fstream>
#include <iostream>

class DFA
{
    int statesNumber, sigma;
    std::vector<std::vector<int>> M;
    std::vector<int> F;
    int q0;

public:
    DFA(const std::string& path);
    bool Test(const std::string& input) const;
    void PrintAccesibleStates(std::ostream& os = std::cout);

private:
    std::vector<int> accessibleStates;
    bool accessibleStatesInit = false;
    std::vector<bool> viz;

    void AccesibleStates();
    void DFS(const int& x);
};

#endif // DFA_H
