#ifndef NFA_H
#define NFA_h

#include <vector>
#include <fstream>
#include <iostream>

class NFA {
    int statesNumber, sigma;
    std::vector<int> F;
    int q0;
    std::vector<std::vector< std::pair<int, char> >> M;

public:
    static const std::string lambda_str;
    static const char lambda;

    NFA(const std::string& input_file);
    std::string ToRegEx();

private:
    void check(int& state);
};

#endif // NFA_H
