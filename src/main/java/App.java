
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
    Stack<String> stack = new Stack<String>(expressionString.length());
    Queue<String> queue = new Queue<String>(expressionString.length());

    while (expression.hasMoreTokens()) {
      System.out.println("-----------------------------------");
      System.out.println("Stack     = " + stack.toString());
      System.out.println("Queue     = " + queue.toString());

      token = expression.nextToken().toString();

      try {
        number = Float.valueOf(token);
        queue.addItem(token);
        System.out.println("Number    =  " + number);
      } catch (Exception e) {
        if (token.equals("(")) {
          stack.addItem(token);
          System.out.println("Operation =  " + token);
        } else if (token.equals(")")) {
          System.out.println(") found!!!!");
          
          while(!("(".equals(stack.getItem()))) {
            System.out.println("Removing " + stack.getItem() + " from stack");
            System.out.println("(" + "(".equals(stack.getItem()));
            queue.addItem(stack.getItem());
            stack.removeItem();
          }
          System.out.println("Removing " + stack.getItem() + " from stack");
          stack.removeItem();
        } else {
          if ("+-*/^".contains(token)) {
            System.out.println("Operation =  " + token);

            if (stack.isEmpty()) {
              stack.addItem(token);
            } else {
              System.out.println("The value " + stack.getItem() + " and " + token + " is " + TrueTable.table(stack.getItem(), token));
              
              while (TrueTable.table(stack.getItem(), token)) {
                queue.addItem(stack.getItem());
                System.out.println("Adding " + stack.getItem() + " to queue and removing from stack");
                System.out.println("Stack ====>" + stack.toString());
                stack.removeItem();
                if(stack.isEmpty()) break;
              }
              stack.addItem(token);


            }
          } else {
            // TODO: throw new Exception here!
            System.out.println("Operation " + token + " not allowed");
          }
        }
      }

      System.out.println("-----------------------------------");
    }

    // empty the stack
    while(!stack.isEmpty()) {
      queue.addItem(stack.getItem());
      stack.removeItem();
    }
    System.out.println("Stack     = " + stack.toString());
    System.out.println("Queue     = " + queue.toString());

  }
}
