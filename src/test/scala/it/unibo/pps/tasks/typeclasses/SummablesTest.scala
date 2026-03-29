package it.unibo.pps.tasks.typeclasses

import it.unibo.pps.tasks.typeclasses.Ex4Summables.*
import it.unibo.pps.u03.Sequences.Sequence
import it.unibo.pps.u03.Sequences.Sequence.*
import org.junit.Assert.*
import org.junit.Test

class SummablesTest:
  @Test def testSumAllWithoutGiven(): Unit =
    case class Type(x: Int)
    val sf = Cons(Type(10), Cons(Type(20), Cons(Type(30), Nil())))
    // assertEquals(60, sumAll(sf))   //Remove comment to see the compile-time error

  @Test def testSumAllWithEmptySequence(): Unit =
    val emptyInt: Sequence[Int] = Nil()
    assertEquals(0, sumAll(emptyInt))
    val emptyDouble: Sequence[Double] = Nil()
    assertEquals(0.0, sumAll(emptyDouble), 0.0)
    val emptyString: Sequence[String] = Nil()
    assertEquals("", sumAll(emptyString))

  @Test def testSumAllWithOnlyZero(): Unit =
    val zeroInt = Cons(0, Nil())
    assertEquals(0, sumAll(zeroInt))
    val zeroDouble = Cons(0.0, Nil())
    assertEquals(0.0, sumAll(zeroDouble), 0.0)
    val zeroString = Cons("0", Nil())
    assertEquals("0", sumAll(zeroString))

  @Test def testSumAllWithInt(): Unit =
    val si = Cons(10, Cons(20, Cons(30, Nil())))
    assertEquals(60, sumAll(si))
    val siNeg = Cons(-10, Cons(-20, Cons(30, Nil())))
    assertEquals(0, sumAll(siNeg))

  @Test def testSumAllWithDouble(): Unit =
    val sd = Cons(10.0, Cons(20.0, Cons(30.0, Nil())))
    assertEquals(60.0, sumAll(sd), 0.0)
    val sdNeg = Cons(-10.0, Cons(-20.0, Cons(30.0, Nil())))
    assertEquals(0.0, sumAll(sdNeg), 0.0)

  @Test def testSumAllWithString(): Unit =
    val ss = Cons("10", Cons("20", Cons("30", Nil())))
    assertEquals("102030", sumAll(ss))
