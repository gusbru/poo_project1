
import java.util.StringTokenizer;

public class App {

  public static void addToken(String token, Queue<String> queue) throws Exception {
    if ("()".contains(token))
      throw new Exception("Malformed Expression");

    queue.addItem(token);
  }

  public static void operationClose(String token, Stack<String> stack, Queue<String> queue) throws Exception {
    if (!(")".contains(token)))
      throw new Exception("Invalid close symbol \")\" ");

    System.out.println(") found!!!!");

    while (!("(".equals(stack.getItem()))) {
      System.out.println("Removing " + stack.getItem() + " from stack");
      System.out.println("(" + "(".equals(stack.getItem()));
      // queue.addItem(stack.getItem());
      try {
        addToken(stack.getItem(), queue);
      } catch (Exception error) {
        System.out.println("Error " + error);
        System.exit(0);
      }

      stack.removeItem();
    }
    System.out.println("Removing " + stack.getItem() + " from stack");
    stack.removeItem();
  }

  public static void operationDefault(String token, Stack<String> stack, Queue<String> queue) throws Exception {
    if ("+-*/^".contains(token)) {
      System.out.println("Operation =  " + token);

      if (stack.isEmpty()) {
        stack.addItem(token);
      } else {
        System.out.println(
            "The value " + stack.getItem() + " and " + token + " is " + TrueTable.table(stack.getItem(), token));

        while (TrueTable.table(stack.getItem(), token)) {
          // queue.addItem(stack.getItem());
          try {
            addToken(stack.getItem(), queue);
          } catch (Exception error) {
            System.out.println("Error " + error);
            System.exit(0);
          }
          System.out.println("Adding " + stack.getItem() + " to queue and removing from stack");
          System.out.println("Stack ====>" + stack.toString());
          stack.removeItem();
          if (stack.isEmpty())
            break;
        }
        stack.addItem(token);

      }
    } else {
      throw new Exception("Operation " + token + " not allowed");
    }
  }

  public static Double calc(Double v1, Double v2, Character op) {
    switch (op) {
    case '+':
      return v1 + v2;
    case '-':
      return v1 - v2;
    case '*':
      return v1 * v2;
    case '/':
      return v1 / v2;
    default:
      return Math.pow(v1, v2);
    }
  }

  public static void main(String[] args) throws Exception {

    // TestDataStructure test = new TestDataStructure();
    // test.testAll();

    // Allowed operations
    final String operations = "(^*/+-)";

    // TODO : the expression needs to be read from the user input
    String inputExpressionString = "10   + (  2      * 3  -     4 ) ^  2  / 4 +   6   * 2	";

    // remove blanck
    String expressionString = RemoveBlancks.removeBlanck(inputExpressionString);

    System.out.println("Expression to evaluate: " + expressionString + ".");

    // Tokenizer
    StringTokenizer expression = new StringTokenizer(expressionString, operations, true);

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
        // queue.addItem(token);
        try {
          addToken(token, queue);
        } catch (Exception error) {
          System.out.println("Error " + error);
          System.exit(0);
        }
        System.out.println("Number    =  " + number);
      } catch (Exception e) {

        // check if the token is a valid operation
        if (!operations.contains(token)) {
          System.out.println("Malformed expression " + e);
          System.exit(0);
        }

        switch (token) {
        case "(":
          stack.addItem(token);
          System.out.println("Operation =  " + token);
          break;

        case ")":
          try {
            operationClose(token, stack, queue);
          } catch (Exception error) {
            System.out.println("Error " + error);
            System.exit(0);
          }
          break;

        default:
          try {
            operationDefault(token, stack, queue);
          } catch (Exception error) {
            System.out.println("Error " + error);
            System.exit(0);
          }

        }

        // if (token.equals("(")) {
        //   stack.addItem(token);
        //   System.out.println("Operation =  " + token);
        // } else if (token.equals(")")) {
        //   System.out.println(") found!!!!");

        //   while (!("(".equals(stack.getItem()))) {
        //     System.out.println("Removing " + stack.getItem() + " from stack");
        //     System.out.println("(" + "(".equals(stack.getItem()));
        //     // queue.addItem(stack.getItem());
        //     try {
        //       addToken(stack.getItem(), queue);
        //     } catch (Exception error) {
        //       System.out.println("Error " + error);
        //       System.exit(0);
        //     }

        //     stack.removeItem();
        //   }
        //   System.out.println("Removing " + stack.getItem() + " from stack");
        //   stack.removeItem();
        // } else {
        //   if ("+-*/^".contains(token)) {
        //     System.out.println("Operation =  " + token);

        //     if (stack.isEmpty()) {
        //       stack.addItem(token);
        //     } else {
        //       System.out.println(
        //           "The value " + stack.getItem() + " and " + token + " is " + TrueTable.table(stack.getItem(), token));

        //       while (TrueTable.table(stack.getItem(), token)) {
        //         // queue.addItem(stack.getItem());
        //         try {
        //           addToken(stack.getItem(), queue);
        //         } catch (Exception error) {
        //           System.out.println("Error " + error);
        //           System.exit(0);
        //         }
        //         System.out.println("Adding " + stack.getItem() + " to queue and removing from stack");
        //         System.out.println("Stack ====>" + stack.toString());
        //         stack.removeItem();
        //         if (stack.isEmpty())
        //           break;
        //       }
        //       stack.addItem(token);

        //     }
        //   } else {
        //     System.out.println("Operation " + token + " not allowed");
        //     System.exit(0);
        //   }
        // }
      }

      System.out.println("-----------------------------------");
    }

    // empty the stack
    while (!stack.isEmpty()) {
      // queue.addItem(stack.getItem());
      try {
        addToken(stack.getItem(), queue);
      } catch (Exception error) {
        System.out.println("Error " + error);
        System.exit(0);
      }
      stack.removeItem();
    }
    System.out.println("Stack     = " + stack.toString());
    System.out.println("Queue     = " + queue.toString());

    // Calculadora de expressao
    double v1, v2;
    Character op;

    System.out.println("Starting the calculator");

    while (!queue.isEmpty()) {
      while (true) {
        try {
          number = Float.valueOf(queue.getItem());
          stack.addItem(queue.getItem());
          System.out.println("Value removed " + queue.getItem());
          queue.removeItem();
        } catch (Exception e) {
          op = queue.getItem().toCharArray()[0];
          queue.removeItem();

          v2 = Double.valueOf(stack.getItem());
          stack.removeItem();

          v1 = Double.valueOf(stack.getItem());
          stack.removeItem();

          stack.addItem(calc(v1, v2, op).toString());
          System.out.println("Operation: " + v1 + " " + op + " " + v2 + " = " + calc(v1, v2, op));
          System.out.println("Stack = " + stack.toString());
          break;
        }
      }

    }

    System.out.println("Calculation Finished!");
    System.out.println("Expression = " + stack.toString());

  }
}
