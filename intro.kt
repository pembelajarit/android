fun main() {
    print("Hello World")

    println("")
    print("Saya sedang berhitung: 3 + 3 = ")
    println(3+3)
    println("It is awesome!")

    // print("baris ini tidak akan dieksekusi")
    /*println("baris ini tidak dieksekusi")
    println("baris ini juga tidak dieksekusi")*/

    // var and val keywords
    var name = "John"
    var birthyear = 2000
    val id = 2
    println(name)
    println(birthyear)
    println (name + " id " + id)
    name = "Anton"
    println (name + " id " + id)

    // Data types
    val myNum: Int = 5                // Int
    val myDoubleNum: Double = 5.99    // Double
    val myLetter: Char = 'D'          // Char
    val myBoolean: Boolean = true     // Boolean
    val myText: String = "Hello"      // String

    println(myNum)
    println(myDoubleNum)
    println(myLetter)
    println(myBoolean)
    println(myText)

    // convert numeric data types
    val x: Int = 5
    val y: Long = x.toLong()
    println(y)

    // kotlin Operators
    var a = 100 + 50
    println(a)
    a += 3
    println(a)
    println(a == 7)

    // kotlin conditions
    // Example 1: Simple if-else
    println("# Simple if-else:")
    val age = 20
    if (age >= 18) {
        println("You are an adult")
    } else {
        println("You are a minor")
    }
    println()

    // Example 2: if-else if-else
    println("# if-else if-else:")
    val score = 75
    if (score >= 90) {
        println("Grade: A")
    } else if (score >= 80) {
        println("Grade: B")
    } else if (score >= 70) {
        println("Grade: C")
    } else if (score >= 60) {
        println("Grade: D")
    } else {
        println("Grade: E")
    }
    println()

    // Loop 
    // When 

    val day = 2
    val result = when (day) {
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            4 -> "Thursday"
            5 -> "Friday"
            6 -> "Saturday"
            7 -> "Sunday"
        else -> "Invalid day."
    }

    println(result)

    // While
    var i = 1
    while (i < 4) {
        println(i)
        i++
    } 

    var j = 1
    do {
        println(j)
        j++
    }
    while (j < 4) 

    // Break and Continue
    // Break
    var k = 0
    while (k < 10) {
        println(k)
        k++
        if (k == 4) {
            break
        }
    }

    // Continuer
    var l = 0
    while (l < 10) {
        if (l == 4) {
            l++
            continue
        }
        println(l)
        l++
    }

    // Arrays
    val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")
    println(cars[0])
    println(cars.size)

    if ("BMW" in cars){
        println("It exists!")
    } else {
        println("It doesn't exist.")
    }

    // For Loop
    val nums = arrayOf(1, 5, 10, 15, 20, 25)
    for (x in nums) {
        println(x)
    }

    for (nums in 5..10) {
        println(nums)
    }

    myfunction() 
    greeting ("Joko Susilo")
}

fun myfunction() {
    println("I am learning kotlin")
}

fun greeting(fname: String){
    println("Welcome " + fname)
}