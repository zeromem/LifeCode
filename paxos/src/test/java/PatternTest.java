import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zeromem
 * @date 2017/10/23
 */
public class PatternTest {
    private static final Pattern pattern = Pattern.compile("(GET)\\s+(\\w+)\\s*|(SET)\\s+(\\w+)\\s+(\\w+)\\s*");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println(matcher.group(1));
                    System.out.println(matcher.group(2));
                    System.out.println(matcher.group(3));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
