class Car {
    var brand = ""
    var model = ""
    var year = 0
}

class Car2 (var brand: String, var model: String, var year: Int)

// inheritance
// superclass
open class ParentClass {
    val x = 5
}

class ChildClass: ParentClass() {
    fun myFunction() {
        println(x)
    }
}

// encapsulation
class Example {
    private var privateVar: Int = 0
    protected var protectedVar: Int = 0
    internal var internalVar: Int = 0
    var publicVar: Int = 0
}

// polymorphism
// Base class
open class Animal {
    // Method to make sound
    open fun makeSound() {
        println("Animal makes a sound")
    }
}

// Subclass Dog inheriting from Animal
class Dog : Animal() {
    // Override makeSound method
    override fun makeSound() {
        println("Dog barks")
    }
}

// overloading
class Calculator {
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun add(a: Int, b: Int, c: Int): Int {
        return a + b + c
    }

    fun add(a: Double, b: Double): Double {
        return a + b
    }
}

// abstraction
// abstract
abstract class Shape {
    abstract fun draw()
    fun move() = println("Shape move")
}

class Circle : Shape() {
    override fun draw() {
        println("Drawing a circle")
    }
}

// interface
interface Drawable {
    fun draw()
}

class Square : Drawable {
    override fun draw() {
        println("Drawing a square")
    }
}

fun main() {
    val c1 = Car()
    c1.brand = "Toyota"
    c1.model = "Alphard"
    c1.year = 2025

    print(c1.brand + " ")
    print(c1.model + " ")
    println(c1.year)

    val c2 = Car()
    c2.brand = "Daihatsu"
    c2.model = "Xenia"
    c2.year = 2026

    print(c2.brand + " ")
    print(c2.model + " ")
    println(c2.year)

    val c3 = Car2("Honda", "CR-V", 2026)
    println(c3.brand + " " + c3.model + " " + c3.year)

    // anonymous object
    val anonymous = object {
        val x: Int = 10
        val y: Int = 20
    }
    println("${anonymous.x} + ${anonymous.y} = ${anonymous.x + anonymous.y}")

    // inheritance
    val myObj = ChildClass()
    myObj.myFunction()

    val dog = Dog()
    dog.makeSound()

    // overloading
    val calculator = Calculator()
    val sum1 = calculator.add(2,5)
    val sum2 = calculator.add(2,5,4)
    val sum3 = calculator.add(2.5,5.0)
    println(sum1)
    println(sum2)
    println(sum3)

    // abstraction
    val c = Circle()
    c.draw()
    c.move()

    val s: Drawable = Square()
    s.draw()
}