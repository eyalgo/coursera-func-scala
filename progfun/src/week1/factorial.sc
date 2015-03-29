package week1

object factorial {
  def fact(n: Int): Int =
    if (n == 0) 1 else n * fact(n - 1)            //> fact: (n: Int)Int

  def fact2(n: Int): Int = {
    def loop(acc: Int, n: Int): Int = {
      if (n == 0) acc
      else loop(acc * n, n - 1)
    }
    loop(1, n)
  }                                               //> fact2: (n: Int)Int

  fact2(5)                                        //> res0: Int = 120
}