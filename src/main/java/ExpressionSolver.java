import java.util.StringTokenizer;

class ExpressionSolver {
  final String operations = "(^*/+-)"; // Allowed operations
  private String expressionString;
  private Stack<String> stack;
  private Queue<String> queue;

  // Constructor
  public ExpressionSolver(String inputExpressionString) throws Exception {
    if (inputExpressionString.isEmpty())
      throw new Exception("Expression to solve must be not null");

    try {
      expressionString = removeBlanck(inputExpressionString);
      stack = new Stack<String>(expressionString.length());
      queue = new Queue<String>(expressionString.length());
    } catch (Exception error) {
      System.out.println("Error " + error);
      System.exit(10);
    }
  }

  public void solver() {

    // Tokenizer
    StringTokenizer expression = new StringTokenizer(expressionString, operations, true);

    while (expression.hasMoreTokens()) {
      System.out.println("-----------------------------------");
      System.out.println("Stack     = " + stack.toString());
      System.out.println("Queue     = " + queue.toString());

      String token = expression.nextToken().toString();

      try {
        Double number = Double.valueOf(token);
        addToken(token, queue);
        System.out.println("Number    =  " + number);
      } catch (Exception e) {
        // check if the token is a valid operation
        if (!operations.contains(token)) {
          System.out.println("Malformed expression " + e);
          System.exit(0);
        }

        try {
          switch (token) {
          case "(":
            stack.addItem(token);
            System.out.println("Operation =  " + token);
            break;
          case ")":
            operationClose(token, stack, queue);
            break;
          default:
            operationDefault(token, stack, queue);
          }
        } catch (Exception error) {
          System.out.println("Error " + error);
          System.exit(0);
        }
      }

      System.out.println("-----------------------------------");
    }

    // empty the stack
    try {
      while (!stack.isEmpty()) {
        addToken(stack.getItem(), queue);
        stack.removeItem();
      }
    } catch (Exception error) {
      System.out.println("Error " + error);
      System.exit(0);
    }

    System.out.println("Stack     = " + stack.toString());
    System.out.println("Queue     = " + queue.toString());

    // Calculadora de expressao
    System.out.println("Starting the Expression Calculator");

    while (!queue.isEmpty()) {
      while (true) {
        try {
          Double.valueOf(queue.getItem());
          stack.addItem(queue.getItem());
          System.out.println("Value removed " + queue.getItem());
          queue.removeItem();
        } catch (Exception e) {
          try {
            Character op = queue.getItem().toCharArray()[0];
            queue.removeItem();

            Double v2 = Double.valueOf(stack.getItem());
            stack.removeItem();

            Double v1 = Double.valueOf(stack.getItem());
            stack.removeItem();

            stack.addItem(calc(v1, v2, op).toString());
            System.out.println("Operation: " + v1 + " " + op + " " + v2 + " = " + calc(v1, v2, op));
            System.out.println("Stack = " + stack.toString());
            break;
          } catch (Exception error) {
            System.out.println("Error" + error);
            System.exit(0);
          }
        }
      }
    }

    System.out.println("Calculation Finished!");
    System.out.println("Expression = " + stack.toString());
  }

  private void addToken(String token, Queue<String> queue) throws Exception {
    if ("()".contains(token))
      throw new Exception("Malformed Expression");

    queue.addItem(token);
  }

  private void operationClose(String token, Stack<String> stack, Queue<String> queue) throws Exception {
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

  private void operationDefault(String token, Stack<String> stack, Queue<String> queue) throws Exception {
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

  private Double calc(Double v1, Double v2, Character op) {
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

  private String removeBlanck(String inputExpression) throws Exception {
    StringBuilder expressionString = new StringBuilder();
    //TODO: check if after a number there is a character which belongs to operations
    for (int i = 0; i < inputExpression.length(); i++) {
      if (!(inputExpression.charAt(i) == ' '))
        expressionString.append(inputExpression.charAt(i));
    }
    return expressionString.toString();
  }

  public void printExpression() {
    System.out.println("Expression to evaluate: " + expressionString);
  }

}