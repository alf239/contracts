package uk.kamchatka.contracts

trait Obs[T] {

}

object Obs {
  def konst[A](a: A): Obs[A] = ???

  def lift[A, B](f: A => B): Obs[A] => Obs[B] = ???

  def lift2[A, B, C](f: (A, B) => C): (Obs[A], Obs[B]) => Obs[C] = ???

  def date: Obs[CDate] = ???

  def at(t: CDate): Obs[Boolean] =
    eqO(date)(konst(t))

  def between(t1: CDate, t2: CDate): Obs[Boolean] =
    lift2[Boolean, Boolean, Boolean](_ && _)(gteqO(date)(konst(t1)), lteqO(date)(konst(t2)))

  def eqO[A](a: Obs[A])(b: Obs[A]): Obs[Boolean] =
    lift2[A, A, Boolean](_ == _)(a, b)

  def gteqO[A](a: Obs[A])(b: Obs[A])(implicit ev: Ordering[A]): Obs[Boolean] =
    lift2(ev.gteq)(a, b)

  def lteqO[A](a: Obs[A])(b: Obs[A])(implicit ev: Ordering[A]): Obs[Boolean] =
    lift2(ev.lteq)(a, b)

  implicit def numericObservable[N](implicit ev: Numeric[N]): Numeric[Obs[N]] = new Numeric[Obs[N]] {
    override def plus(x: Obs[N], y: Obs[N]): Obs[N] = lift2(ev.plus)(x, y)

    override def minus(x: Obs[N], y: Obs[N]): Obs[N] = lift2(ev.minus)(x, y)

    override def times(x: Obs[N], y: Obs[N]): Obs[N] = lift2(ev.times)(x, y)

    override def negate(x: Obs[N]): Obs[N] = lift(ev.negate)(x)

    override def fromInt(x: Int): Obs[N] = konst(ev.fromInt(x))

    override def toInt(x: Obs[N]): Int = ???

    override def toLong(x: Obs[N]): Long = ???

    override def toFloat(x: Obs[N]): Float = ???

    override def toDouble(x: Obs[N]): Double = ???

    override def compare(x: Obs[N], y: Obs[N]): Int = ???
  }
}
