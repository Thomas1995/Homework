#ifndef STRING_E
#define STRING_E

#include <iostream>
#include <string>
#include <stack>
#include "action.cpp"

class String {
    std::string crtString;
    std::stack<Action*> actionStack;

    String(const std::string& S) : crtString(S) {}
    String(const String& ) {}
    void operator = (const String& ) {}
    ~String() {}

public:

    static String* CreateString(const std::string& S = "")
    {
        static String createdString(S);
        return &createdString;
    }

    void Execute(Action* a)
    {
        a->Initialize(&crtString);
        actionStack.push(a);
        a->Execute();
    }

    void Undo()
    {
        if(actionStack.empty()) return;

        actionStack.top()->Undo();
        delete actionStack.top();
        actionStack.pop();
    }

    friend std::ostream& operator<<(std::ostream& os, const String& S)
    {
        return os << S.crtString << "\n";
    }

};

#endif // STRING_E
