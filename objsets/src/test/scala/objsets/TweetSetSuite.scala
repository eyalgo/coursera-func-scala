package objsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TweetSetSuite extends FunSuite {
  trait TestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
    val set6 = set5.incl(new Tweet("max", "max body", 21))
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  test("mostRetweeted: on empty set") {
    new TestSets {
      intercept[NoSuchElementException] {
        set1.mostRetweeted
      }
    }
  }

  test("filter: on empty set") {
    new TestSets {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("empty: on empty set") {
    new TestSets {
      assert(set1.empty)
    }
  }

  test("filter: a on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }
  //TODO add tests for filter

  test("union: set4c and set4d") {
    new TestSets {
      def uniounSet = set4c.union(set4d)
      assert(size(uniounSet) === 4)
      assert(uniounSet contains new Tweet("c", "c body", 7))
      assert(uniounSet contains new Tweet("d", "d body", 9))
      assert(uniounSet contains new Tweet("a", "a body", 20))
      assert(uniounSet contains new Tweet("b", "b body", 20))
    }
  }

  test("union: with empty set (1)") {
    new TestSets {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2)") {
    new TestSets {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("mostRetweeted: on set6") {
    new TestSets {
      def maxRetweetTweet = set6.mostRetweeted
      assert(maxRetweetTweet.text equals "max body")
      assert(maxRetweetTweet.retweets equals 21)
      assert(maxRetweetTweet.user equals "max")
    }
  }

  test("empty: on empty set6") {
    new TestSets {
      assert(!set6.empty)
    }
  }

  test("descending: set5") {
    new TestSets {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }
}
