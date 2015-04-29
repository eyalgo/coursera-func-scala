package week3

import sun.invoke.empty.Empty
import scala.collection.script.Include

object intsets {

  val t1 = new NonEmpty(3, Empty, Empty)          //> t1  : week3.intsets.NonEmpty = {.3.}
  val t2 = t1 incl 4                              //> t2  : week3.intsets.IntSet = {.3{.4.}}
  
  abstract class IntSet {
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    def union(other: IntSet): IntSet
  }

  object Empty extends IntSet {
    def contains(x: Int): Boolean = false
    def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
    override def union(other: IntSet) = other
    override def toString = "."
  }

  class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
    def contains(x: Int): Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true

    def incl(x: Int): IntSet =
      if (x < elem) new NonEmpty(elem, left incl x, right)
      else if (x > elem) new NonEmpty(elem, left, right incl x)
      else this

    override def union(other: IntSet) =
      other union (left union (right)) incl (elem)

    override def toString = "{" + left + elem + right + "}"
  }
}