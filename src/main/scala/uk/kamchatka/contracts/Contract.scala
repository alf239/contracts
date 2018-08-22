package uk.kamchatka.contracts

import uk.kamchatka.contracts.Obs.{at, konst}

trait Contract {

}

object Contract {
  def zero: Contract = Zero

  def one(k: Currency): Contract = One(k)

  def give(c: Contract): Contract = Give(c)

  def and(c: Contract, d: Contract): Contract = And(c, d)

  def or(c: Contract, d: Contract): Contract = Or(c, d)

  def cond(o: Obs[Boolean], c: Contract, d: Contract): Contract = Cond(obs, c, d)

  def scale(k: Obs[Double], c: Contract): Contract = Scale(k, c)

  def when(o: Obs[Boolean], c: Contract): Contract = When(o, c)

  def anytime(o: Obs[Boolean], c: Contract): Contract = Anytime(o, c)

  def until(o: Obs[Boolean], c: Contract): Contract = Until(o, c)

  def zcb(t: CDate, nominal: Double, k: Currency): Contract =
    when(at(t), scale(konst(nominal), one(k)))

  case object Zero extends Contract

  case class One(k: Currency) extends Contract

  case class Give(c: Contract) extends Contract

  case class Scale(o: Obs[Double], c: Contract) extends Contract

  case class And(c: Contract, d: Contract) extends Contract

  case class Or(c: Contract, d: Contract) extends Contract

  case class Cond(o: Obs[Boolean], c: Contract, d: Contract) extends Contract

  case class When(o: Obs[Boolean], c: Contract) extends Contract

  case class Anytime(o: Obs[Boolean], c: Contract) extends Contract

  case class Until(o: Obs[Boolean], c: Contract) extends Contract

}
