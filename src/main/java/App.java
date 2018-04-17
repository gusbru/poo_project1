


public class App {

  public static void main(String[] args) throws Exception {

    // TODO : the expression needs to be read from the user input
    String inputExpressionString = "10   + (  2      * 3  -     4 ) ^  2  / 4 +   6   * 2	";

    ExpressionSolver expressionSolver = new ExpressionSolver(inputExpressionString);
    expressionSolver.printExpression();
    expressionSolver.solver();

  }
}
