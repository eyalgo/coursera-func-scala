import week4._

object NthInList {
  def nth[T](n: Int, list: List[T]): T = {
    if (list.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) list.head
    else nth(n - 1, list.tail)
  }                                               //> nth: [T](n: Int, list: week4.List[T])T

  def singleton[T](elem: T) = new Cons(elem, new Nil[T])
                                                  //> singleton: [T](elem: T)week4.Cons[T]

  def l5 = new Cons(5, new Nil())                 //> l5: => week4.Cons[Int]
  def l3 = new Cons(3, l5)                        //> l3: => week4.Cons[Int]
  def l1 = new Cons(1, l3)                        //> l1: => week4.Cons[Int]

  nth(4, l1)                                      //> java.lang.IndexOutOfBoundsException
                                                  //| 	at NthInList$$anonfun$main$1.nth$1(NthInList.scala:5)
                                                  //| 	at NthInList$$anonfun$main$1.apply$mcV$sp(NthInList.scala:16)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at NthInList$.main(NthInList.scala:3)
                                                  //| 	at NthInList.main(NthInList.scala)
  println("Welcome to the Scala worksheet")
}