package project20280.stacksqueues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class intToBinary {
    static String convertToBinary(long dec_num)
    {
        if (dec_num == 0)
        {
            return "0";
        }

        LinkedStack<Character> stack = new LinkedStack<>();
        long n = dec_num;

        while (n > 0)
        {
            int remainder = (int) (n % 2);
            char digit = (char) ('0' + remainder);
            stack.push(digit);
            n /= 2;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
        {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    @Test
    void testConvertToBinary()
    {
        assertEquals("10111", convertToBinary(23));
        assertEquals("111001000000101011000010011101010110110001100010000000000000", convertToBinary(1027010000000000000L));
    }
}

//to extend this to handle different bases a parameter 'int base' would have to be added
//the same modulo and division operations done with 2 would be done with that base.
//bases above 9 would be represented the same way that they are in hex, continuing onto
//the alphabet starting with A = 10.

