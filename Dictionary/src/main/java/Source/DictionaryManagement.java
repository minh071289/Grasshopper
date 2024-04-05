package Source;

import Tree.Tree;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    private Tree tree = new Tree();

    public void sortDictionary(Dictionary dictionary) {
        dictionary.sort(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWordTarget().compareTo(o2.getWordTarget());
            }
        });
    }

    /**
     * Đẩy từ trong path vào dictionary.
     */
    public void insertFromFile(Dictionary dictionary, String path) {
        try {
            String line;
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String englishWord = bufferedReader.readLine();
            englishWord = englishWord.replace(".", "");

            while ((line = bufferedReader.readLine()) != null) {
                Word word = new Word();
                word.setWordTarget(englishWord.trim());
                String meaning = line + "\n";

                while ((line = bufferedReader.readLine()) != null) {
                    if (!line.startsWith(".")) {
                        meaning += line + "\n";
                    }
                    else {
                        englishWord = line.replace(".", "");
                        break;
                    }
                }
                word.setWordExplain(meaning.trim());
                dictionary.add(word);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    /**
     * Đẩy từ từ dictionary ra path.
     */
    public void exportToFile(Dictionary dictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Word word : dictionary) {
                bufferedWriter.write("." + word.getWordTarget() + "\n" + word.getWordExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    /**
     * Tìm keyWord trong dictionary bằng chặt nhị phân.
     */
    public int searchWord(Dictionary dictionary, String keyWord) {
        try {
            sortDictionary(dictionary);

            int left = 0;
            int right = dictionary.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int res = dictionary.get(mid).getWordTarget().compareTo(keyWord);

                if (res == 0) {
                    return mid;
                }
                if (res <= 0) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
        return -1;
    }

    /**
     * Tìm các từ chứa key trong dictionary.
     */
    public List<String> lookupWord(Dictionary dictionary, String key ) {
        List<String> results = tree.autoComplete(key);
        return results;
    }

    /**
     * Cập nhật từ ở vị trí index rồi đẩy vào path.
     */
    public void updateWord(Dictionary dictionary, int index, String meaning, String path) {
        try {
            dictionary.get(index).setWordExplain(meaning);
            exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    /**
     * Thêm từ mới vào path.
     */
    public void addWord(Word word, String path) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("." + word.getWordTarget() + "\n" + word.getWordExplain());
            bufferedWriter.newLine();
            tree.insert(word.getWordTarget());
        } catch (IOException e) {
            System.out.println("IOException.");
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    /**
     * Xoá từ ở vị trí index, đẩy vào path và làm lại cây.
     */
    public void deleteWord(Dictionary dictionary, int index, String path) {
        try {
            dictionary.remove(index);
            tree = new Tree();
            setTree(dictionary);
            exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    /**
     * Tạo ra thời gian delay.
     */
    public void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    /**
     * Tạo cây mới từ dictionary.
     */
    public void setTree(Dictionary dictionary) {
        try {
            for (Word word : dictionary) {
                tree.insert(word.getWordTarget());
            }
        } catch (NullPointerException e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    /** Thêm word từ commandline. */
    public void insertFromCommandline(Dictionary dictionary) {
        Scanner input = new Scanner(System.in);

        int wordCount = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= wordCount; i++) {
            String word = input.nextLine();
            String means = input.nextLine();
            dictionary.add(new Word(word, means));
        }
    }
}
