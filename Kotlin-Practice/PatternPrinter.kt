fun main(){
    println("=".repeat(50))
    println("Pattern Printer")
    println("=".repeat(50))

    println("Enter the pattern size: (1-20)")
    val size= readLine()?.toIntOrNull()

    if(size==null || size<1 || size>20){
        println("Invalid size. Please enter a number between 1 and 20.")
        return
    }
    
    println("Pattern1: Incrasing number")
    println("-".repeat(30))
    pattern1forloop(size)

    println("Pattern2: same number")
    println("-".repeat(30))
    pattern2whileloop(size)

    println("Pattern3: Pyramid")
    println("-".repeat(30))
    pattern3dowhile(size)

    println("Pattern4: Diamond Pattern")
    println("-".repeat(30))
    pattern4mixedLooop(size)

    println("Pattern 5: Binary Triangle")
    println("-".repeat(30))
    pattern5Advanced(size)

}

fun pattern1forloop(n: Int){
    for(i in 1..n){
        for(j in 1..i){
            print("$j")
        }
        println()
    }
}

fun pattern2whileloop(n: Int){
    var i=1
    while(i<=n){
        var j=1
        while(j<=i){
            print("$i")
            j++
        }
        println()
        i++
    }
}

fun pattern3dowhile(n: Int){
    var i =1
    do{
        var space = n -i
        while(space > 0){
            print(" ")
            space--
        }

        var num = 1
        do{
            print("$num")
            num++
        }while(num <= i)
            println()
            i++
       
    }while(i <= n)
}

fun pattern4mixedLooop(n : Int){
    for(i in 1..n){
        for(j in i until n){
            print(" ")
        }
        for(j in 1..(2*i -1)){
            print(j)
        }
        println()
    }

    for(i in n-1 downTo 1){
        for(j in n downTo i+1 ){
            print(" ")
        }
        for(j in 1..(2*i -1)){
            print(j)
        }
        println()
    }
}

fun pattern5Advanced(n: Int){
    for(i in 1..n){

        var start= if(i % 2 == 0) 0 else 1

        for(j in 1..i){
                print("$start")
                start = 1 - start
        }
        
        println()
    }   
}