import java.util.Scanner;

public class App {
    public void run() {
        String wise;
        String author;

        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");
        System.out.print("명언 : ");
        wise = sc.nextLine();// 입력값 가져온다. enter로 마무리
        System.out.print("작가 : ");
        author = sc.nextLine();
        System.out.println("1번 명언이 등록되었습니다.");
        System.out.println("명령) 종료");
    }
}