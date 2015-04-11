package week2

object exercise1 {
  def product(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1 else f(a) * product(f)(a + 1, b) //> product: (f: Int => Int)(a: Int, b: Int)Int

  product(x => x * x)(3, 5)                       //> res0: Int = 3600

  def fact(n: Int) = product(x => x)(1, n)        //> fact: (n: Int)Int

  fact(5)                                         //> res1: Int = 120

  def sum(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)     //> sum: (f: Int => Int)(a: Int, b: Int)Int

  def general(f: Int => Int, op: (Int, Int) => Int, unit: Int)(a: Int, b: Int): Int =
    if (a > b) unit else op(f(a), general(f, op, unit)(a + 1, b))
                                                  //> general: (f: Int => Int, op: (Int, Int) => Int, unit: Int)(a: Int, b: Int)In
                                                  //| t
  general(x => x * x, (a, b) => a * b, 1)(3, 5)   //> res2: Int = 3600

  def product2(f: Int => Int)(a: Int, b: Int): Int = general(f, (x, y) => x * y, 1)(a, b)
                                                  //> product2: (f: Int => Int)(a: Int, b: Int)Int
  
  product2(x => x * x)(3, 5)                      //> res3: Int = 3600

}