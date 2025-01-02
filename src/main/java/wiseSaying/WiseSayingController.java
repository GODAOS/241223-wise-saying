package wiseSaying;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner sc = new Scanner(System.in);
    private final Map<Integer, WiseList> wise_list = new HashMap<>();

    public WiseSayingController() {

    }

    // 등록 처리
    public void add() {
        System.out.print("명언 : ");
        String text = sc.nextLine();// 입력값 가져온다. enter로 마무리
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseList wiseList = new WiseList(text, author);

        wise_list.put(wiseList.getId(), wiseList);
        System.out.println(wiseList.getId() + "번 명언이 등록되었습니다.");
    }

    // 목록 처리
    public void showList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        if (wise_list.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        } else {
            for (int i = WiseList.getIdCount(); i > 0; i--) {
                if (wise_list.get(i) == null) { // 명언이 비어있을때
                    continue;
                }
                wise_list.get(i).display();// 목록 역순 출력
            }
        }
    }

    // 삭제처리
    public void deleteWise(String command) {
        // '삭제?id=' 분리
        String sb = command.substring(6);
        Integer id = Integer.parseInt(sb);

        // 해당 명언 존재 여부
        if (wise_list.get(id) == null) { // 해당 명언이 존재하지 않는다면
            System.out.println(id + "번 명언이 존재하지 않습니다.");
        } else if (wise_list.get(id) != null) {// 존재한다면
            wise_list.remove(id);
            System.out.println(id + "번 명언이 삭제되었습니다.");
        }

    }

    // 수정
    public void modifyWise(String command) {
        // '수정?id=' 분리
        String[] arr = command.split("=");
        Integer id = Integer.parseInt((arr[1]));

        if (wise_list.get(id) == null) { // 해당 명언이 존재하지 않는다면
            System.out.println(id + "번 명언이 존재하지 않습니다.");
        } else if (wise_list.get(id) != null) {// 존재한다면
            // 수정할 코드
            WiseList targetList = wise_list.get(id);
            System.out.println("명언(기존) : " + targetList.getText() + "\n명언 : ");
            targetList.setText(sc.nextLine().trim());// 수정할 명언 입력

            System.out.println("작가(기존)" + targetList.getAuthor() + "\n작가 : ");
            targetList.setAuthor(sc.nextLine().trim());// 수정할 작가 입력

            System.out.println(id + "번 명언 수정 완료");
        }

    }

    // 빌드
    public void wiseBuild() {
        // 파일 경로 설정
        String filePath = "db/wiseSaying/data.json";

        // JSON 배열 생성
        JSONArray wiseSayings = new JSONArray();
        String[] text = new String[wise_list.size()];

        // 생성된 아이디 크기만큼 반복
        for (int i = 0; i < WiseList.getIdCount(); i++) {
            // 값이 없을 경우
            if (wise_list.get(i)==null){
                text[i] = "";
                continue;
            }
            JSONObject saying1 = new JSONObject();

            // 여기서 부터 다시 작성
            //text[i] = wise_list.get(i).getText();

            saying1.put("id", wise_list.keySet());
            saying1.put("content", wise_list.get(i).getText());
            saying1.put("author", wise_list.get(i).getAuthor());
            wiseSayings.put(saying1);
        }

        JSONObject finalJson = new JSONObject();
        finalJson.put("wiseSayings", wiseSayings);


        // 파일 생성 및 덮어쓰기
        try {
            // 디렉토리 생성
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            // 파일 쓰기
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(finalJson.toString(4)); // 4는 들여쓰기 크기
                System.out.println("JSON 파일이 성공적으로 생성 및 덮어쓰기되었습니다: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("파일 처리 중 오류 발생: " + e.getMessage());
        }
    }
}
