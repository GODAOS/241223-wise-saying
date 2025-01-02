import wiseSaying.SystemController;
import wiseSaying.WiseSayingController;

import java.util.*;

public class App {

    private final WiseSayingController wiseSayingController;
    private final SystemController systemController;
    private final Scanner sc;

    public App(){
        sc = new Scanner(System.in);
        wiseSayingController = new WiseSayingController();
        systemController = new SystemController();
    }

    public void run() {// 앱 실행

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine();

            if (command.equals("종료")) {
                systemController.exit();
                break;
            } else if (command.equals("등록")) {
                wiseSayingController.add();
            } else if (command.equals("목록")) {
                wiseSayingController.showList();
            } else if (command.startsWith("삭제?id=")) {
                wiseSayingController.deleteWise(command);
            } else if (command.startsWith("수정?id=")) {
                wiseSayingController.modifyWise(command);
            } else if (command.equals("빌드")) {
                wiseSayingController.wiseBuild();
            } else {
                System.out.println("잘못된 명령입니다.");
            }
        }
    }
}


