package BackEnd;

import java.util.*;

public class Tree {
    private final Map<Character, Tree> letter; // Lưu kí tự của nó và cây tiếp theo
    private String word;// Lưu từ được hình thành khi xét đến nó
    private boolean end = false;// Check xem kết thúc từ chưa

    public Tree() {
        this(null);
    }

    private Tree(String word) {
        this.word = word;
        letter = new HashMap<Character, Tree>();
    }

    /**
     * Thêm 1 kí tự.
     */
    private void add(char character) {
        String s;
        if (this.word == null) s = Character.toString(character);
        else s = this.word + character;
        letter.put(character, new Tree(s));
    }

    /**
     * Thêm 1 từ vào cây.
     */
    public void insert(String diagnosis) {
        if (diagnosis == null) throw new IllegalArgumentException("Null diagnoses entries are not valid.");
        Tree node = this;
        for (char c : diagnosis.toCharArray()) {
            if (!node.letter.containsKey(c)) node.add(c);
            node = node.letter.get(c);
        }
        node.end = true;
    }

    /**
     * Kiểm tra prefix có trong cây không và lấy những từ tạo thành từ nó ra.
     */
    public List<String> autoComplete(String prefix) {
        Tree trieNode = this;
        for (char c : prefix.toCharArray()) {
            if (!trieNode.letter.containsKey(c)) return null;
            trieNode = trieNode.letter.get(c);
        }
        return trieNode.allPrefixes();
    }

    /**
     * Lấy tất cả từ có chứa prefix ra.
     */
    private List<String> allPrefixes() {
        List<String> diagnosisResults = new ArrayList<String>();
        if (this.end) diagnosisResults.add(this.word);
        for (Map.Entry<Character, Tree> entry : letter.entrySet()) { // Lấy key và value ra
            Tree child = entry.getValue();
            Collection<String> childPrefixes = child.allPrefixes();// Đệ quy tìm tiếp các con
            diagnosisResults.addAll(childPrefixes);
        }
        return diagnosisResults;
    }
}