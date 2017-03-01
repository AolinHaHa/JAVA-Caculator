# JAVA-Caculator
java caculator project
Requirements
Your task is to use sablecc to help you implement a Java application that correctly evaluates arithmetic
expressions. A large part of the application has already been written for you. In fact it correctly handles
expressions involving only +, -, *, and /. Below, you can see a sample input file and the corresponding
output.
Your task is two add two more capabilities to this calculator, exponents and variables.
Exponents (80 points)
Summary: Modify the application so that the character ^ is can be used as an exponentiation operator.
For example 2^3 should evaluate to 8. Note the order of operations. The ^ operator has highest
precedence. So for example, 2 * 3 ^ 2 should be 18 and not 36. Also note that the ^ operator associates
to the right. So 2 ^ 3 ^ 2 should be 512 and not 64. (Note that only one test actually checks for this so
you will only lose 10 points if this is the only error.)
Allowed Modifications: To implement the ^ operator, you must add it to the sablecc file and then you
must modify the Main.evaluate method to take into account the new parse tree nodes that result from
your changes in the sablecc file. You must not make any other changes for this. 
Grading: The input file power.txt is used to test the ^ operator and when you run ant submit, that test
will be saved in the file TEST2.txt. It should look like the screenshot below. There are 8 expressions, you
will receive 10 points per correct expression.
Rounding (20 points)
Summary: Modify the application to allow for the use of brackets to round fractions by dropping the
fractional part (equivalent to casting to int). The brackets should also force grouping like parentheses
do. In other words [3.1+2.5]*2 forces the addition to happen first, then rounding, then multiplication.
Allowed Modifications: To implement the [ ] operator, you must add it to the sablecc file and then you
must modify the Main.evaluate method to take into account the new parse tree nodes that result from
your changes in the sablecc file. You must not make any other changes for this..
Grading: The input file round.txt is used to test the variable portion of this assignment. When you run
ant submit, the results of this test will be saved in the file TEST3.txt. It should look like the screenshot
below. The test has 4 expressions. You will receive 5 points for each correct expression. Note that the
test makes use of the exponent operator, so you must get that working to get full credit for the [ ]
operator. 
