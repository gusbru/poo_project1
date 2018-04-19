import java.util.StringTokenizer;

public class ExpressionSolver
{
    private String expressionString;
    private Stack<String> stack;
    private Queue<String> queue;

    // Constructor
    public ExpressionSolver(String inputExpressionString) throws Exception
    {
        if (inputExpressionString.isEmpty())
            throw new Exception("Expression to solve must be not null");

        try
        {
            expressionString = removeBlanck(inputExpressionString);
            stack = new Stack<String>(expressionString.length());
            queue = new Queue<String>(expressionString.length());
        } catch (Exception error)
        {
            System.err.println("Malformed expression " + error);
            System.exit(10);
        }
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        // Cast
        ExpressionSolver e = (ExpressionSolver) obj;
        return this.expressionString.equals(e.expressionString);
    }

    public String toString()
    {
        return expressionString;
    }

    public int hashCode()
    {
        int ret = 396;

        //for each attribute
        ret = ret * 17 + expressionString.hashCode();

        return ret;
    }

    public void solver()
    {
        // Tokenizer
        String operations = "(^*/+-)"; // Allowed operations
        StringTokenizer expression = new StringTokenizer(expressionString, operations, true);

        while (expression.hasMoreTokens())
        {
            String token = expression.nextToken();

            try
            {
                Double.valueOf(token);
                addToken(token, queue);
            } catch (Exception error)
            {
                if (!operations.contains(token))
                {
                    System.err.println("Malformed expression " + error);
                    System.exit(10);
                }
                doOperation(token);
            }
        }

        // empty the stack
        try
        {
            while (!stack.isEmpty())
            {
                addToken(stack.getItem(), queue);
                stack.removeItem();
            }
        } catch (Exception error)
        {
            System.err.println("Malformed expression " + error);
            System.exit(10);
        }

        // Calculadora de expressao
        try
        {
            while (!queue.isEmpty())
            {
                while (true)
                {
                    try
                    {
                        Double.valueOf(queue.getItem());
                        stack.addItem(queue.getItem());
                        // System.out.println("Value removed " + queue.getItem());
                        queue.removeItem();
                    } catch (Exception e)
                    {
                        Character op = queue.getItem().toCharArray()[0];
                        queue.removeItem();

                        Double v2 = Double.valueOf(stack.getItem());
                        stack.removeItem();

                        Double v1 = Double.valueOf(stack.getItem());
                        stack.removeItem();

                        stack.addItem(calc(v1, v2, op).toString());
                        break;
                    }
                }
            }
        } catch (Exception error)
        {
            System.err.println("Malformed expression " + error);
            System.exit(10);
        }

        try
        {
            checkResult();
        } catch (Exception error)
        {
            System.err.println("Malformed expression " + error);
            System.exit(10);
        }

    }

    public void printResult()
    {
        try
        {
            System.out.println("The value of the expression " + expressionString + " is " + stack.getItem().toString());
        } catch (Exception error)
        {
            System.err.println("Malformed expression " + error);
            System.exit(10);
        }
    }

    private void doOperation(String token)
    {
        try
        {
            switch (token)
            {
                case "(":
                    stack.addItem(token);
                    break;
                case ")":
                    operationClose(token);
                    break;
                default:
                    operationDefault(token);
            }
        } catch (Exception error2)
        {
            System.err.println("Malformed expression " + error2);
            System.exit(10);
        }
    }

    private void checkResult() throws Exception
    {
        if (queue.getSize() != 0 || stack.getSize() != 1)
            throw new Exception("Malformed expression");
    }

    private void addToken(String token, Queue<String> queue) throws Exception
    {
        if ("()".contains(token))
            throw new Exception("Malformed Expression");

        queue.addItem(token);
    }

    private void operationClose(String token) throws Exception
    {
        if (!(")".contains(token)))
            throw new Exception("Invalid close symbol \")\" ");

        try
        {
            while (!("(".equals(stack.getItem())))
            {
                addToken(stack.getItem(), queue);
                stack.removeItem();
            }
        } catch (Exception error)
        {
            System.err.println("Malformed expression " + error);
            System.exit(10);
        }

        stack.removeItem();
    }

    private void operationDefault(String token) throws Exception
    {
        if ("+-*/^".contains(token))
        {
            if (stack.isEmpty())
            {
                stack.addItem(token);
            } else
            {
                try
                {
                    while (TrueTable.table(stack.getItem(), token))
                    {
                        addToken(stack.getItem(), queue);
                        stack.removeItem();
                        if (stack.isEmpty())
                            break;
                    }
                } catch (Exception error)
                {
                    System.err.println("Malformed expression " + error);
                    System.exit(10);
                }
                stack.addItem(token);

            }
        } else
        {
            throw new Exception("Operation " + token + " not allowed");
        }
    }

    private Double calc(Double v1, Double v2, Character op)
    {
        switch (op)
        {
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

    private String removeBlanck(String inputExpression) throws Exception
    {
        StringBuilder expressionString = new StringBuilder();
        //TODO: check if after a number there is a character which belongs to operations
        for (int i = 0; i < inputExpression.length(); i++)
        {
            if (!(inputExpression.charAt(i) == ' '))
                expressionString.append(inputExpression.charAt(i));
        }
        return expressionString.toString();
    }

}