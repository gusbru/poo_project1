public class RemoveBlancks {
  
  // remove blanck spaces
  public static String removeBlanck(String inputExpression) {
    StringBuilder expressionString = new StringBuilder();
    for (int i = 0; i < inputExpression.length(); i++) {
      if (!(inputExpression.charAt(i) == ' '))
        expressionString.append(inputExpression.charAt(i));
    }
    return expressionString.toString();
  }
}