
trait Account {
  def no: String
  def name: String
  def bank: Bank
  def address: Address
  def dateOfOpening: Date
  def dateOfClose: Option[Date]
}

case class CheckingAccount(
  no: String,
  name: String,
  bank: Bank,
  address: Address,
  dateOfOpening: Date,
  dateOfClose: Option[Date]
) extends Account

case class SavingAccount(
  no: String,
  name: String,
  bank: Bank,
  address: Address,
  dateOfOpening: Date,
  dateOfClose: Option[Date],
  rateOfInterest: BigDecimal
) extends Account

case class MoneyMarketAccount extends Account

object Account {
  def apply() = {
    // instantiate Checking, Savings or MoneyMarket Account
    // depending on parameters
  }
}

trait AccountService {
  def debit(a: Account, amount: Amount): Try[Account] = ???
  def credit(a: Account, amount: Amount): Try[Account] = ???
  def transfer(from: Account, to: Account, amount: Amount) = for {
    d <- debit(from, amount)
    c <- credit(to, amount)
  } yield (d, c)
}

trait AccountRepository {
  def query(accountNo: String): Option[Account]
  def query(criteria: Criteria[Account]): Seq[Account]
  def write(accounts: Seq[Account]): Boolean
  def delete(account: Account): Boolean
}
