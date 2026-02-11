package project20280.stacksqueues;

class BracketChecker {
    private String input;

    public BracketChecker(String in)
    {
        input = in;
    }

    private boolean isLeft(char c)
    {
        return c == '(' || c == '[' || c == '{';
    }

    private boolean isRight(char c)
    {
        return c == ')' || c == ']' || c == '}';
    }

    private boolean matches(char left, char right)
    {
        return (left == '(' && right == ')')
                || (left == '[' && right == ']')
                || (left == '{' && right == '}');
    }


    public void check() {
        // TODO
        ArrayStack<Character> stack = new ArrayStack<>();

        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);

            if (isLeft(c))
            {
                stack.push(c);
            }

            else if (isRight(c))
            {
                if (stack.isEmpty())
                {
                    System.out.println("error at index " + i + ": missing left delimiter for '" + c + "'");
                    return;
                }

                char left = stack.pop();

                if (!matches(left, c))
                {
                    System.out.println("error at index " + i + ": '" + c + "' does not match '" + left + "'");
                    return;
                }
            }
        }

        if (!stack.isEmpty())
        {
            System.out.println("error: missing delimiter; stack not empty at end.");
        }

        else
        {
            System.out.println("correct: delimiters are balanced");
        }
    }

    public static void main(String[] args)
    {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs)
        {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
            System.out.println("\n");
        }
    }
}