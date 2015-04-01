package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 12) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c > r) throw new ArrayIndexOutOfBoundsException(s"Column: %s Row: %s, $c, $r")
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balance(counter: Int, rest: List[Char]): Boolean = {
      if (counter < 0) false
      else if (rest.isEmpty) counter == 0
      else if (rest.head == '(') balance(counter+1, rest.tail)
      else if (rest.head == ')') balance(counter-1, rest.tail)
      else balance(counter, rest.tail);
    }

    balance(0, chars)

  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0) 0
    else if (money > 0 && coins.isEmpty) 0
    else countChange(money, coins.tail) + countChange(money-coins.head, coins)
  }

}
