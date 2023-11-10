import java.security.MessageDigest;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Task implements Callable<Result> {

    @Override
    public Result call() throws Exception {
        byte[] input = new byte[32];
        MessageDigest sha256 = MessageDigest.getInstance("SHA256");
        byte[] hash = new byte[32];
        int sum = 0;
        do {
            ThreadLocalRandom.current().nextBytes(input);
            sha256.update(input, 0, 32);
            sha256.digest(hash, 0, 32);
            sum = 0;
            for (byte b : hash) {
                sum |= b & 0xff;
            }
        } while (Integer.bitCount(sum) >= 7);
        System.out.printf("%02x, %d%n", sum, Integer.bitCount(sum));
        return new Result(input, hash);
    }

}
