package lab4;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть шлях до вихідного файлу: ");
        String inputPath = scanner.nextLine();

        System.out.print("Введіть шлях до результуючого файлу: ");
        String outputPath = scanner.nextLine();

        try {
            System.out.println("Читання файлу...");
            String content = Files.readString(Path.of(inputPath));

            content = content.replaceAll("\\bpublic\\b", "private");

            Pattern pattern = Pattern.compile("/\\*(.*?)\\*/", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(content);
            StringBuilder result = new StringBuilder();

            while (matcher.find()) {
                String commentContent = matcher.group(1);
                String[] lines = commentContent.split("\\r?\\n");
                StringBuilder newComment = new StringBuilder();
                for (int i = 0; i < lines.length; i++) {
                    newComment.append("//").append(lines[i]);
                    if (i < lines.length - 1) {
                        newComment.append(System.lineSeparator());
                    }
                }
                matcher.appendReplacement(result, Matcher.quoteReplacement(newComment.toString()));
            }
            matcher.appendTail(result);

            System.out.println("Запис у файл...");
            Files.writeString(Path.of(outputPath), result.toString());

            System.out.println("Готово!");

        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}