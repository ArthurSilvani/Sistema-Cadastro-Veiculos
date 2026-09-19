/**
 * Input
 */
public class Input {

    public static int readInt(String message) {
        while (true) {
            try {
                String valor = IO.readln(message);
                return Integer.parseInt(valor);
            } catch (Exception e) {
                IO.println("Valor inválido, digite um numero inteiro!");
            }
        }

    }

}