#ifndef ACTION
#define ACTION

#include <string>
#include <cstring>

class Action {
protected:
    std::string* stringPointer;
public:
    void Initialize(std::string* S)
    {
        stringPointer = S;
    }
    virtual void Execute() {};
    virtual void Undo() {};
    virtual ~Action() {};
};

class Insert : public Action {
    int insertionSize = 0;
    std::string insertionString;
public:
    Insert(const std::string& S) : insertionString(S) {}

    void Execute()
    {
        insertionSize = insertionString.size();
        *stringPointer += insertionString;
    }

    void Undo()
    {
        int initLength = (*stringPointer).size() - insertionSize;
        *stringPointer = (*stringPointer).substr(0,initLength);
    }
};

class Erase : public Action {
    int left, right;
    std::string erasedString;
public:
    Erase(const int l, const int r) : left(l), right(r) {}
    void Execute()
    {
        left = std::max(1, left);
        right = std::min((int)(*stringPointer).size(), right);

        erasedString = (*stringPointer).substr(left-1, right-left+1);
        (*stringPointer).erase(left-1, right-left+1);
    }

    void Undo()
    {
        std::string str1 = (*stringPointer).substr(0, left-1);
        std::string str2 = (*stringPointer).substr(left-1);
        *stringPointer = str1 + erasedString + str2;
    }
};

#endif // ACTION
