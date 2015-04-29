package week3

object rationals {
  val x = new Rational(1, 3)                      //> x  : <error> = 1/3
  val y = new Rational(5, 7)                      //> y  : <error> = 5/7
  val z = new Rational(3, 2)                      //> z  : <error> = 3/2
  x + y                                           //> res0: <error> = 22/21
  -y                                              //> res1: <error> = 5/-7

  x - y                                           //> res2: <error> = 8/-21

  y - x                                           //> res3: <error> = 8/21

  x - x                                           //> res4: <error> = 0/1

  x - y - z                                       //> res5: <error> = -79/42

  y + y                                           //> res6: <error> = 10/7

  x < y                                           //> res7: <error> = true
  x.max(y)                                        //> res8: <error> = 5/7

  x max y                                         //> res9: <error> = 5/7
  y max x                                         //> res10: <error> = 5/7

  new Rational(2)                                 //> res11: <error> = 2/1

}