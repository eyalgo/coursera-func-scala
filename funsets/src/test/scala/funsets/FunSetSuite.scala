package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  test("negative set contains negative number") {
    assert(contains(x => x < 0, -2))
  }

  test("negative set does not contain positive number") {
    assert(!contains(x => x < 0, 5))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = (x: Int) => x == 0 || x == 1 || x == 2
    val s5 = (x: Int) => x == 0 || x == 2 || x == 3
    val s6 = singletonSet(4)

    val s1001 = singletonSet(1001)
    val sMinus1001 = singletonSet(-1001)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersection contains elements that in both sets") {
    new TestSets {
      val s = intersect(s4, s5)
      assert(contains(s, 0), "Intersect 0")
      assert(contains(s, 2), "Intersect 2")
      assert(!contains(s, 1), "Intersect 1")
      assert(!contains(s, 3), "Intersect 3")
    }
  }

  test("difference contains elements in first set, which are not in other") {
    new TestSets {
      val s = diff(s4, s5)
      assert(!contains(s, 0), "Difference 0")
      assert(contains(s, 1), "Difference 1")
      assert(!contains(s, 2), "Difference 2")
      assert(!contains(s, 3), "Difference 3")
    }
  }

  test("union several sets") {
    new TestSets {
      val s = union(s6, union(s3, union(s1, s2)))
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(contains(s, 3), "Union 3")
      assert(contains(s, 4), "Union 4")
      assert(!contains(s, 5), "Union 5")
    }
  }

  test("Filter set contains elements in set that pass filter") {
    val even = (x: Int) => x % 2 == 0
    new TestSets {
      val s = filter(union(s6, union(s3, union(s1, s2))), even)
      assert(!contains(s, 1), "Filter 1")
      assert(contains(s, 2), "Filter 2")
      assert(!contains(s, 3), "Filter 3")
      assert(contains(s, 4), "Filter 4")
    }
  }

  test("forAll works") {
    new TestSets {
      val s = union(union(s1001, sMinus1001), union(s3, union(s1, s2)))
      assert(forall(s, x => x < 4), "all < 4")
      assert(!forall(s, x => x < 2), "all < 2")
    }
  }

  test("exists works") {
    new TestSets {
      val s = union(union(s1001, sMinus1001), union(s3, union(s1, s2)))
      assert(exists(s, x => x == 3), "3 exists")
      assert(!exists(s, x => x == 4), "4 does not exist")
    }
  }
  
  test("map works") {
    new TestSets {
      val s = map(s1, x => x +1)
      assert(contains(s, 2), "1 was mapped to 1+1")
      assert(!contains(s, 3), "1 was not mapped to 3")
      
      val sm = map(s, x => x*x)
      assert(contains(sm, 4), "2 was mapped to 2*2")
    }
  }
}
