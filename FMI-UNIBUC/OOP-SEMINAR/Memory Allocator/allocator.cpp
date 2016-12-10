#ifndef ALLOCATOR_IMPL
#define ALLOCATOR_IMPL

#include "allocator.h"

Allocator::Allocator(const size_t t) : size(1<<t)
{
    adr = malloc(size);
}

Allocator::~Allocator()
{
    free(adr);
}

Allocator::Allocator(const Allocator&) : size(0) {}

void Allocator::operator = (const Allocator&) {}

Allocator* Allocator::AllocateMemoryBlock(const size_t t)
{
    static Allocator A(t);
    return &A;
}

void* Allocator::mallocx(size_t sz)
{
    if(flag) return malloc(sz);

    size_t space = 0;
    for(auto it : memory)
    {
        if(it.first <= space + sz)
            space = it.first + it.second;
    }

    if(space + sz > size) return NULL;

    flag = true;
    memory.insert(std::make_pair(space, sz));
    flag = false;

    return adr + space;
}

void Allocator::freex(void* ptr)
{
    if(flag) return free(ptr);

    size_t space = (size_t)ptr - (size_t)adr;
    size_t sz = 0;

    for(auto it : memory)
    {
        if(it.first == space)
        {
            sz = it.second;
            break;
        }
    }

    flag = true;
    memory.erase(std::make_pair(space, sz));
    flag = false;
}


Allocator *A = Allocator::AllocateMemoryBlock();

void* operator new(size_t sz)
{
    return A->mallocx(sz);
}

void operator delete(void* adr)
{
    return A->freex(adr);
}

#endif // ALLOCATOR_IMPL
