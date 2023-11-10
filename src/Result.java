import java.io.Serializable;

public record Result(byte[] seed, byte[] hash) implements Serializable {

}
