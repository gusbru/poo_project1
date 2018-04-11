
import java.util.StringTokenizer;

public class App {

  public static void main(String[] args) throws Exception {

    // TestDataStructure test = new TestDataStructure();
    // test.testAll();

    // Allowed operations
    String operations = "(^*/+-)";

    // TODO : the expression needs to be read from the user input
    String inputExpressionString = "10   + (  2      * 3  -     4 ) ^  2  / 4 +   6   * 2";

    // remove blanck
    String expressionString = RemoveBlancks.removeBlanck(inputExpressionString);

    System.out.println("Expression to evaluate: " + expressionString);

    // Tokenizer
    StringTokenizer expression = new StringTokenizer(expressionString, "+-*/^()", true);

    // put elements into the stack or queue
    String token;
    Float number;
    while (expression.hasMoreTokens()) {
        token = expression.nextToken().toString();
        if (token.equals("(")) {
            System.out.println("Go to stack  " + token);
        } else {
            try {
                number = Float.valueOf(token);
                System.out.println("Number    =  " + number);
            } catch (Exception e) {
                if (operations.contains(token)) {
                    System.out.println("Operation =  " + token);
                } else {
                    // TODO: throw new Exception here!
                    System.out.println("Operation not allowed");
                }
            }
        }

    }

    

  }
}
