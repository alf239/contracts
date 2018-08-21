package uk.kamchatka.contracts

import uk.kamchatka.contracts.Obs.{at, konst}

trait Contract {

}

object Contract {
  def zero: Contract = ???

  def one(cur: Currency): Contract = ???

  def give(c: Contract): Contract = ???

  def and(a: Contract, b: Contract): Contract = ???

  def or(a: Contract, b: Contract): Contract = ???

  def cond(obs: Obs[Boolean], a: Contract, b: Contract): Contract = ???

  def scale(k: Obs[Double], c: Contract): Contract = ???

  def when(obs: Obs[Boolean], c: Contract): Contract = ???

  def anytime(obs: Obs[Boolean], c: Contract): Contract = ???

  def until(obs: Obs[Boolean], c: Contract): Contract = ???

  def zcb(t: CDate, nominal: Double, cur: Currency): Contract =
    when(at(t), scale(konst(nominal), one(cur)))
}
