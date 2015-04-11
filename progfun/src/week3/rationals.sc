package week3

object rationals {
  val x = new Rational(1, 3)                      //> x  : week3.Rational = 1/3
  val y = new Rational(5, 7)                      //> y  : week3.Rational = 5/7
  val z = new Rational(3, 2)                      //> z  : week3.Rational = 3/2
  x + y                                           //> res0: week3.Rational = 22/21
  -y                                              //> res1: week3.Rational = 5/-7

  x - y                                           //> res2: week3.Rational = 8/-21

  y - x                                           //> res3: week3.Rational = 8/21

  x - x                                           //> res4: week3.Rational = 0/1

  x - y - z                                       //> res5: week3.Rational = -79/42

  y + y                                           //> res6: week3.Rational = 10/7

  x < y                                           //> res7: Boolean = true
  x.max(y)                                        //> res8: week3.Rational = 5/7

  x max y                                         //> res9: week3.Rational = 5/7
  y max x                                         //> res10: week3.Rational = 5/7

  new Rational(2)                                 //> res11: week3.Rational = 2/1

}

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be positive")

  def this(x: Int) = this(x, 1)
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  def numer = x
  def denom = y

  // not often
  // def numer = x / gcd(x, y)
  // def denom = y / gcd(x, y)

  // use val
  // val numer = x / gcd(x, y)
  // val denom = y / gcd(x, y)

  def +(that: Rational): Rational =
    new Rational(
      numer * that.denom + denom * that.numer,
      denom * that.denom)

  def -(that: Rational): Rational =
    this + -that

  def unary_- : Rational =
    new Rational(
      -1 * numer,
      denom)

  def <(that: Rational) =
    numer * that.denom < that.numer * denom

  def max(that: Rational) =
    if (this < that) that else this

  override def toString = {
    val g = gcd(x, y)
    numer / gcd(x, y) + "/" + denom / gcd(x, y)
  }
}