package week3

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