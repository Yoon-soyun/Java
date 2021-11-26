import java.io*;
import java.util.Random;

class Card
// Card 클래스
{
  public final String face; // instance variable; instance field
  public final String rank; // instance variable; instance field
  // final 사용 이유: 클래스 상속을 제한 -> 한번 선언 한 후 변하지 않는 immutable class
  
  private static String intToFace(int faceNum)
  // 랜덤으로 받은 숫자를 카드의 face로 전환해주는 함수
  // private: 클래스 내부에서만 사용할 수 있도록 제한
  // static: 해당 객체를 공유하겠단 의미
  // 0~3에 해당하는 정수
  {
    String face;
    
    switch(faceNum)
    {
      case 0: 
        face = "H";
        break;
      case 1:
        face = "C";
        break;
      case 2:
        face = "S";
        break;
      case 3:
        face = "D";
        break;
    }
    
    return face;
  }
  
  private static String intToRank(rankNum)
  // 1~13에 해당하는 정수
  {
    String rank;
    
    if(rankNum>=2 && rankNum<=10)
    // 2~10일 경우 
    {
      rank = Inteager.toString(rankNum);
    }
    else
    {
      switch(rankNum)
      {
        case 1:
          rank = "A";
          break;
        case 11:
          rank = "J";
          break;
        case 12:
          rank = "Q";
          break;
        case 13:
          rank = "K";
          break;
      }
    }
    
    return rankNum;
  }
  
  public int getvalue()
  {
    switch(rank)
    {
      case "A":
        return 11; 
      case "J":
      case "Q":
      case "K":
        return 10;
      default: // 정수를 rank로 가지고 있음
        return Inteager.parseInt(rank);
    }
  }
  
  private static Random rand = new Random(); // 임의의 숫자를 생성하는 Random 객체 rand
  
  public Card()
  // 생성자
  {
    this(rand.nextInt(4), rand.nextInt(13) + 1); // 임의의 숫자 생성
  }
  
  public Card(int faceNum, int rankNum)
  // 함수 생성자
  {
    this(intToFace(faceNum), intToRank(rankNum));
  }
  
  public Card(String face, String rank)
  // instance 함수는 제일 마지막에 호출해야 함
  {
    this.face = face;
    this.rank = rank;
  }
}

public class BlackJack
{
  public static void main(String[] args) throw Exception
  {
    int score = 0;
    int As = 0;
    
    while(score < 17)
    {
      Card c = new Card();
      
      System.out.printf("딜러가 카드를 받았습니다: %s %s\n", c.face, c.face);
      
      if(c.rank.equals("A"))
      {
        As++;
      }
      
      score += c.getValue();
      
      if(score > 21 && As > 0)
      {
        As--;
        score -=10;
      }
      
      System.out.printf("누적가치: %d\n", score);
    }
    
    if(score <= 21)
    {
      System.out.println("딜을 마칩니다.");
    }
    else
    {
      System.out.println("파산하였습니다.");
    }
  }
}
