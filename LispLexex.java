import java.util.*;

public class LispLexer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la expresión LISP (finalice con una línea vacía):");

        StringBuilder inputBuilder = new StringBuilder();
        String line;
        
        while (!(line = scanner.nextLine()).isEmpty()) {
            inputBuilder.append(line).append(" ");
        }

        String input = inputBuilder.toString().trim();
        scanner.close();

        Lexer lexer = new Lexer(input);
        if (lexer.esBalanceado()) {
            System.out.println("La expresión es correcta.");
            System.out.println("Tokens: " + lexer.tokenizar());
        } else {
            System.out.println("La expresión es incorrecta (paréntesis desbalanceados).");
        }
    }
}

class Lexer {
    private final String input;
    private int index = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public boolean esBalanceado() {
        int balance = 0;
        for (char c : input.toCharArray()) {
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    public List<String> tokenizar() {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        while (index < input.length()) {
            char c = input.charAt(index);
            if (c == '(' || c == ')') {
                if (token.length() > 0) {
                    tokens.add(token.toString());
                    token.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else if (Character.isWhitespace(c)) {
                if (token.length() > 0) {
                    tokens.add(token.toString());
                    token.setLength(0);
                }
            } else {
                token.append(c);
            }
            index++;
        }

        if (token.length() > 0) {
            tokens.add(token.toString());
        }

        return tokens;
    }
}
    

