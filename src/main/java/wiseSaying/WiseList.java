package wiseSaying;

public class WiseList {
    private static int idCount = 0; // ID 자동증가를 위한 카운트 정적 변수
    private int id;// 명언 ID
    private String author;// 작가 이름
    private String text;// 명언 텍스트

    public WiseList(String text, String author) {
        this.id = ++idCount;
        this.author = author;
        this.text = text;
    }

    public void display() {
        System.out.println(id + " / " + text + " / " + author);
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        WiseList.idCount = idCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
