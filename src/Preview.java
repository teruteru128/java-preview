import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.concurrent.Executors;
import java.util.random.RandomGenerator;

/**
 * Preview
 */
public class Preview {

    public static void main(String[] args) throws Exception {
        var format = HexFormat.of();
        try (var executor = Executors.newCachedThreadPool()) {
            ArrayList<Task> tasks = new ArrayList<>(16);
            for (int i = 0; i < 16; i++) {
                tasks.add(new Task());
            }
            var result = executor.invokeAny(tasks);
            int sum = 0;
            for (byte b : result.hash()) {
                sum |= b & 0xff;
            }
            System.out.printf("(%02x)%s: %s%n", sum, format.formatHex(result.hash()), format.formatHex(result.seed()));
        }
    }

}
