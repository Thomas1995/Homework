#include <iostream>
#include <string>
#include "action.cpp"
#include "string.cpp"
using namespace std;

int main()
{
    string s;
    cout<<"Please enter the initial text: ";
    getline(cin, s);
    String *S = String::CreateString(s);

    cout<<"\nCommands\n-------------------\nPRINT\nINSERT <text>\n"
    "ERASE <left position> <right position>\nUNDO\nEXIT\n\n";

    string cmd, param;
    int p1,p2;

    while(true)
    {
        cin>>cmd;

        if(cmd == "PRINT")
        {
            cout<<*S;
        }
        else if(cmd == "INSERT")
        {
            getline(cin, param);
            param.erase(0, 1);
            S->Execute(new Insert(param));
        }
        else if(cmd == "ERASE")
        {
            cin>>p1>>p2;
            S->Execute(new Erase(p1, p2));
        }
        else if(cmd == "UNDO")
        {
            S->Undo();
        }
        else if(cmd == "EXIT")
        {
            break;
        }
        else
        {
            cout<<cmd<<" is not a valid command\n";
        }

        cout<<"\n";
    }

    return 0;
}
