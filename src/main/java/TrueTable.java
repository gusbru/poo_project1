public class TrueTable
{

    /**
     * @input op1    symbol from the top of the stack
     * @input op2    symbol from the sequence
     */
    public static Boolean table(String op1, String op2)
    {
        switch (op1)
        {
            case "(":
                return ")".contains(op2);
            case "^":
            case "*":
            case "/":
                return "*/+-)".contains(op2);
            case "+":
            case "-":
                return "+-)".contains(op2);
            default:
                return false;
        }
    }

    public static void test(String operations)
    {
        String op1, op2;
        // table true table
        for (int i = 0; i < operations.length(); i++)
        {
            for (int j = 0; j < operations.length(); j++)
            {
                op1 = String.valueOf(operations.charAt(i));
                op2 = String.valueOf(operations.charAt(j));
                System.out.println(op1 + " " + op2 + " " + table(op1, op2));
            }
        }
    }
}