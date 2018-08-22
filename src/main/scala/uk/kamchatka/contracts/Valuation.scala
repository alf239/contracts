package uk.kamchatka.contracts

import uk.kamchatka.contracts.Contract._

object Valuation {

  def value(contract: Contract, k: Currency): Process[Double] = contract match {
    case Zero => Process.K(0.0)
    case One(k2) => exch(k, k2)
    case Give(c) => -value(c, k)
    case Scale(o, c) => value(o) * value(c, k)
  }

  def value[A](o: Obs[A]): Process[A] = ???

  def exch(k: Currency, k2: Currency): Process[Double] = ???
}
