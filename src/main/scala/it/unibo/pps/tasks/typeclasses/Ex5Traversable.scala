package it.unibo.pps.tasks.typeclasses

import it.unibo.pps.u03.Sequences.Sequence, Sequence.*
import it.unibo.pps.u03.Optionals.Optional, Optional.*

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  trait Traversable[T[_]]:
    def forEach[A](data: T[A])(f: A => Unit): Unit

  given Traversable[Optional] with
    def forEach[A](data: Optional[A])(f: A => Unit): Unit = data match
      case Just(v) => f(v);
      case _ => ()

  given Traversable[Sequence] with
    def forEach[A](data: Sequence[A])(f: A => Unit): Unit = data match
      case Cons(h,t) => f(h); forEach(t)(f)
      case _ => ()

  def log[A](a: A): Unit = println("The next element is: "+a)

  def logAll[A, T[_]: Traversable](data: T[A]): Unit =
    val traversable = summon[Traversable[T]]
    traversable.forEach(data)(log)

  @main def testTraversable(): Unit =
    val opt = Just(1)
    logAll(opt) //1

    val empty: Optional[Int] = Empty()
    logAll(empty) //nothing

    val seq = Cons(1.0, Cons(2.0, Cons(3.0, Nil())))
    logAll(seq) //1.0 -> 2.0 -> 3.0

    val optSeq = Just(Cons("4", Cons("5", Cons("6", Nil()))))
    logAll(optSeq) //Cons(4,Cons(5,Cons(6,Nil())))

    val seqOpt = Cons(Just(7), Cons(Just(8), Cons(Empty(), Nil())))
    logAll(seqOpt) //Just(7) -> Just(8) -> Empty()


  
