package uk.kamchatka.contracts

trait Process[A] {
  self =>
  def value(t: CDate): RV[A]

  def map[B](f: A => B): Process[B] =
    (t: CDate) => self.value(t) map f
}


object Process {

  case class K[A](a: A) extends Process[A] {
    override def value(t: CDate): RV[A] = RV.K(a)
  }

  implicit class DoubleProcessOps(val p: Process[Double]) extends AnyVal {
    def unary_- : Process[Double] = p.map(-_)

    def *(p: Process[Double]): Process[Double] =
  }

}