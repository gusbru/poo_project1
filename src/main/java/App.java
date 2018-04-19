import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App
{

    public static void main(String[] args) throws Exception
    {

        // TODO : the expression needs to be read from the user input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type an expression:");
        String inputExpressionString = in.readLine();
//        String inputExpressionString = "10  +  (  2      * 3  -     4 ) ^  2  / 4 +   6   * 2	";

        ExpressionSolver expressionSolver = new ExpressionSolver(inputExpressionString);
        System.out.println("Expression to be solved: " + expressionSolver.toString());
        expressionSolver.solver();
        expressionSolver.printResult();
    }
}
