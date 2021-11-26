/*
블랙잭
블랙잭 프로그램을 만든다.

우선 Card 클래스를 만든다. Card class에는 두개의 생성자를 둔다.

Card()
Card(String face, String rank)
Card의 생성자는 다음의 특성을 갖는다.

Card() 생성자는 임의의 카드를 생성하고 내부에 this를 이용하여 다른 생성자를 호출해야 함,
Card(face, rank)는 face와 rank에 맞는 카드를 생성한다.
face는 H, C, S, D 중 하나이며
rank는 2,3,4,5,6,7,8,9,10,J,Q,K,A 중 하나이다.
각 카드는 다음과 같은 가치를 갖는다.

숫자카드 (2~10)은 그 숫자와 동일한 가치를 갖는다.
그림카드(J, Q, K)는 가치가 10이다.
A카드의 가치는 11이다. (1이 될 수도 있으나 그 예외는 추후 설명)
블랙잭에서 딜러의 규칙은 다음과 같다.

블랙잭에서 딜러는 카드를 하나씩 받을때마다 그 가치를 누적하여 계산한다.
예1)3,K를받은경우누적가치:13
예2)2,A,3을받은경우누적가치:16
딜러는 누적가치가 17 이상이 되면 딜을 마친다. 누적가치가 21을 초과할 경우 파산한다.
실행예는 다음과 같다.

예1)
딜러가 카드를 받았습니다: H 3

누적가치: 3

딜러가 카드를 받았습니다: : D K

누적가치: 13

딜러가 카드를 받았습니다: : S 7

누적가치: 20

딜을 마칩니다.

• 수행 예 2


딜러가 카드를 받았습니다: : D 5

누적가치: 5

딜러가 카드를 받았습니다: : D K

누적가치: 15

딜러가 카드를 받았습니다: : S 8

누적가치: 23

파산하였습니다.



추가 규칙

 A의 가치는 원칙적으로 11이지만, A의 가치를 11로 따질 때 파산하는 경우는 A의 가치를 1로 낮추어 누적한다.
• 추가규칙수행예

딜러가 카드를 받았습니다: : D A

누적가치: 11

딜러가 카드를 받았습니다: : H 5

누적가치: 16

딜러가 카드를 받았습니다: : H 8

누적가치: 14

딜러가 카드를 받았습니다: : C 4

누적가치: 18

딜을 마칩니다.
*/

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
