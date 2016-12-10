#ifndef ALLOCATOR
#define ALLOCATOR

#include <iostream>
#include <set>
#include <cstdlib>

class Allocator {
    void* adr;
    const size_t size;
    std::set< std::pair<size_t,size_t> > memory;
    bool flag = false;

    Allocator(const size_t t = 10);
    ~Allocator();
    Allocator(const Allocator&);
    void operator = (const Allocator&);

public:
    static Allocator* AllocateMemoryBlock(const size_t t = 10);
    void* mallocx(size_t t);
    void freex(void* ptr);
};

#endif // ALLOCATOR
