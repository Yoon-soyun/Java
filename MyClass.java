import java.io*;
import java.util.Scanner;
import java.util.LinkedList;

class Student
{
  final int id;
  final String name;
  final int kor;
  final int eng;
  final int math;
  
  Student(int id, String name, int kor, int eng, int math)
  {
    this.id = id;
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
  }
}

public class MyClass
{
  public static void main(String[] args)
  {
    /* 학생 목록 */
    LinkedList<Student> student = new LinkedList<Student>();
    
    /* 학생 입력 */
    Scanner scan = new Scanner(System.in);
    
    while(true)
    {
      String name - scan.nextLine();
      
      if(name.equals("Z") || name.equals("z"))
      {
        break;
      }
      
      // 학번, 국영수 성적 입력
      int id = scan.nextInt();
      int kor = scan.nextInt();
      int eng = scan.nextInt();
      int math = scan.nextInt();
      
      scan.nextLine();
      
      student.add(new Student(id, name, kor, eng, math));
      System.out.printf("id = %d, kor = %d, eng = %d, math = %d\n", id, kor, eng, math);
    }
    // 입력 완료
    
    System.out.println("학번 범위를 입력하세요:");
    int fromId = scan.nextInt();
    int toId = scan.nextInt();
    
    //출력
    int korSum = 0;
    int engSum = 0;
    int mathSum = 0;
    double num = 0;
    
    System.out.println("   학번       이름      국어   영어   수학   총점   평균");
    System.out.println("=======================================================");
    for(Student s: student)
    {
      if(s.id < fromId || s.id > toId) continue;
      
      int sum = s.kor + s.eng + s.math;
      korSum += s.kor;
      engSum += s.eng;
      mathSum +=s.math;
      num += 1.0;
      
      System.out.printf("%10d %6s %6d %6d %6d %6d %6.2f\n", s.id, s.name, s.kor, s.eng, s.math, sum, sum/3.0);
    }
    
    System.out.println("======================================================");
    System.out.printf("평균                    %6.2f %6.2f %6.2f     %6.2f\n", korSum/num, engSum/num, mathSum/num, (korSum +engSum+mathSum)/3.0/num);
    
