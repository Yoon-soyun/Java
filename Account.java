import jav.io*;

class TransferAgent
{
  static double DOLLER_RATE = 1100;
  
  static void transfermoney(CheckingAccount from, Account to, double money)
  {
    if(from.getAmount() < money)
    {
      System.out.println("Too much money to transfer.");
      return;
    }
    
    // 원 계좌에서 적절한 돈 인출
    double won = money;
    
    if(from instanceof DollerAccount)
    {
      won = money * DOLLER_RATE;
    }
    
    form.withdraw(money);
    
    // 상대 계좌에서 적절한 돈 입금
    if(to instanceof DollarAccount)
    {
      to.deposit(won/DOLLER_RATE);
    }
    else
    {
      to.deposit(won);
    }
  }
}

class DollerAccount extends CHeckingAccount
{
  public DollerAccount(amount)
  {
    super(amount);
  }
}

class CheckingAccount extends Account
{
  public CheckingAccount(double amount)
  {
    super(amount);
  }
  
  public void transfer(double money, Account other)
  {
    TransferAgent.transferMoney(this, other, money);
  }
}

class Account
{
  protected double amount;
  
  public Account(double amount)
  {
    this.amount = amount;
  }
  
  public void withdraw(double money)
  {
    if(money <= amount)
    {
      this.amount -= money;
    }
    else
    {
      System.out.println("Too much money to withdraw.");
    }
  }
  
    public void deposit(double money)
    {
      amount += money;
    }
    
    public double getAmount()
    {
      return amount;
    }
}

class Account
{
  public static void main(String[] args) throws Exception
  {
    CheckingAccount W = new CheckingAccount(100000);
    CheckingAccount D = new DollarAccount(100.00);
    
    Systwm.out.printf("W: %f\n", W.getAmount());
    System.out.printf("D: %f\n", D.getAmount());
    
    W.transfer(1000, D);
    
    System.out.printf("After W -> D: 1000Won\n");
    System.out.printf("W: %f\n", W.getAmount());
    System.out.printf("D: %f\n", D.getAmount());
    
    D.transfer(10, W);
    
     System.out.printf("After D -> W: $10.00\n");
    System.out.printf("W: %f\n", W.getAmount());
    System.out.printf("D: %f\n", D.getAmount());
  }
}
