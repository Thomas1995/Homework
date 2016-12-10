#include <iostream>
using std::cout;
using std::endl;

#include <algorithm>
using std::sort;

#include <vector>
#include <list>
#include <deque>
using std::vector;
using std::list;
using std::deque;

#include <stdlib.h>
#include <time.h>

template <typename Container, int containerSize = 10>
void addRandomValues(Container& container) {
    for(int i=1;i<=containerSize;++i)
      container.push_back(rand() % 100);
}

template <typename Container>
void printContainerContent(Container container) {
    for(auto it : container) {
        cout<<it<<' ';
    }
    cout<<endl;
}

template <typename Container>
void sortRandomContainer() {
      Container container;
      addRandomValues(container);
      sort(container.begin(), container.end());
      printContainerContent(container);
}

struct myObject {
    int a, b;

    // commeting this will generate an error
    bool operator < (const myObject& o) const {
        if(a == o.a)
          return b < o.b;
        else
          return a < o.a;
    }
};

// yes, this is not safe, it is just for proving a point
template <typename object, int maxSize = 15>
class myContainer {
private:
    int size = 0;
    object array[maxSize];

public:
    // no need for this
    /*object& operator [] (int pos) {
        return array[pos];
    }*/

    object* begin() {
        return array;
    }

    object* end() {
        return array + size;
    }

    void push_back(object o) {
        array[size++] = o;
    }
};

int main() {

    srand(time(NULL));

    sortRandomContainer<vector<int>>();
    sortRandomContainer<deque<int>>();

    // this won't work
    // sortRandomContainer<list<int>>();

    sortRandomContainer<myContainer<int>>();

    myContainer<myObject> container;
    sort(container.begin(), container.end());

    return 0;
}
