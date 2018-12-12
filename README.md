## Java List Prefetching Plugin
Hayden Hudgins and Christopher Siu (`{hrhudgin, cesiu}`@calpoly.edu), CSC 515, Fall 2018

This proof-of-concept plugin for the OpenJDK 11 compiler fakes prefetching data in sequential Java lists by adding calls to `LinkedList.peek()` after calls to `LinkedList.poll()` and by adding calls to `ArrayList.get(i + 1)` after calls to `ArrayList.get(i)` (any expression is acceptable; the object expression and the index expression need not be simple identifiers).

We begrudgingly admit that Oracle was right: this provides no speedup in Java.
