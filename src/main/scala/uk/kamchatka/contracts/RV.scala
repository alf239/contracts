package uk.kamchatka.contracts

trait RV[A] {
  def map[B](f: A => B): RV[B]
}

object RV {

  case class K[A](a: A) extends RV[A] {
    override def map[B](f: A => B): RV[B] = K(f(a))
  }

}