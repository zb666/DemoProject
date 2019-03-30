package com.example.bod.demoproject

sealed class Operation {
    class Add(value: Int) : Operation()
    class Divide(value: Int) : Operation()
}