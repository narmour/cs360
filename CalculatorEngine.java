import java.util.*;
class CalculatorEngine{
    // precedence values
    private static final int OPAREN=8;
    private static final int EXP=7;
    private static final int MULT=6;
    private static final int DIV=5;
    private static final int PLUS=4;
    private static final int MINUS=4;
    private static final int CPAREN=1;

    //stacks and internal strings
    private Stack<ExpressionToken> stack = new Stack<>();
    private Stack<Double> evalStack = new Stack<>();
    private String infixExpr = "";
    private String postExpr = "";
    private String savedExpr = "";
    private String result = "";
    
    // when the = button is pressed, convert display value to postfix then attempt to evaluate it into
    // result. clear everything after.
    public void compute(){

        //convert infixExpr to postExpr
        infixToPostfix();

        //calculate result of postExpr
        result = evalPostFix();

        //clear input strings and both stacks
        infixExpr = "";
        postExpr = "";
        evalStack.clear();
        stack.clear();


    }

    //attempts to evaluate postExpr, returns true if its a valid expr, false if not 
    public String evalPostFix(){
        
        String[] expr = postExpr.split("");

        int i =0;
        //tokenize postExpr
        while(i < postExpr.length()){
            //must be an operator
            if(expr[i].equals("(") || expr[i].equals(")"))
                return "SYNTAX ERROR";
            if(!isNumeric(expr[i]) && !expr[i].equals(" ")){
                // try to pop off stack twice to get operands. if its empty, return syntax error
                double leftOp=0;double rightOp =0;
                if(evalStack.empty())
                    return "SYNTAX ERROR";
                else
                    rightOp = evalStack.pop();
                if(evalStack.empty())
                    return "SYNTAX ERROR";
                else
                    leftOp = evalStack.pop();

                //decode operator and apply proper operaiton
                if(expr[i].equals("+"))
                    evalStack.push(leftOp+rightOp);
                if(expr[i].equals("-"))
                    evalStack.push(leftOp-rightOp);
                if(expr[i].equals("*"))
                    evalStack.push(leftOp*rightOp);
		        if(expr[i].equals("^"))
                    evalStack.push(Math.pow(leftOp,rightOp));
                if(expr[i].equals("/")){
                    if(rightOp ==0)
                        return "DIVISION BY ZERO";
                    evalStack.push(leftOp/rightOp);
                }
                
		
                i++;
            }
            // else if its an operand
            else if(isNumeric(expr[i])){
                //read in the entire number
                String nextOperand = "";
                while(i < postExpr.length() && isNumeric(expr[i]))
                    nextOperand+=expr[i++];
                //System.out.println(nextOperand);
                
                //push to stack
                evalStack.push(Double.parseDouble(nextOperand));
                nextOperand = "";

            }
            // must be a space, just increment past it
            else
                i++;

        }


        // return result
        return Double.toString(evalStack.pop());
    }

    //clear result and infixExpr, display will show nothing after this.
    public void clear(){
        result = "";
        infixExpr = "";

    }
    //display the current infixExpr if there is one, else justs returns the calculated result.
    public String  display(){
        if(!infixExpr.equals(""))
            return infixExpr;
        return result;
    }

    //if theres an expression, save that, else save result
    public void save(){
        if(!infixExpr.equals(""))
            savedExpr = infixExpr;
        else
            savedExpr = result;
    }
    //set display to whatever was saved.
    public void recall(){
        infixExpr = savedExpr;
    }

    //takes input from gui and adds to inputExpr
    public void addInput(String c){
        infixExpr+=c;
    }
    //returns true if infixExpr is a valid expression, false if not
    public void infixToPostfix(){
        String[] expr = infixExpr.split("");
        int i =0;
        // will load digits into this
        String nextOperand = "";


        while(i <expr.length){
            // if next character is an operator, tokenize it
            if(!isNumeric(expr[i])){
                //System.out.println("found operator");
                //create token
                ExpressionToken t = tokenize(expr[i]);
                if(t.s.equals("(")){
                    t.precedence = 0;
                    stack.push(t);
                }
                //pop the stack till we find matching open paren
                else if(t.s.equals(")")){
                    while(!stack.empty() && !stack.peek().s.equals("("))
                        postExpr+=stack.pop().s;
                    //pop the ( off
                    stack.pop();
                }

                //if operator has higher priority than top of stack, push it
                else if(stack.empty() || t.precedence > stack.peek().precedence)
                    stack.push(t);
                //else, pop off top, add to outputstream then push
                else{
                    while(!stack.empty() && t.precedence <= stack.peek().precedence)
                        postExpr+=stack.pop().s;
                    stack.push(t);
                }
                i++;
            }
            // if next char isnt an operator, it must be a digit or .
            // while loop to read the entire operand
            else{
                nextOperand+=expr[i++];
                while(i< expr.length && isNumeric(expr[i])){
                        nextOperand+=expr[i++];
                }
        //        System.out.println("OPERAND: " + nextOperand);
                postExpr +=nextOperand + " "; 
                nextOperand = "";

            }

        }

        //if stack isnt empty, pop till empty and add to output
        while(!stack.empty())
            postExpr+=stack.pop().s;
        System.out.println(postExpr);
    }

    //given a string s, create a token object for the infix to postfix conversion stack.
    public ExpressionToken tokenize(String s){
        if(s.equals("("))
            return new ExpressionToken("(",OPAREN);
        if(s.equals("^"))
            return new ExpressionToken("^",EXP);
        if(s.equals("*"))
            return new ExpressionToken("*",MULT);
        if(s.equals("/"))
            return new ExpressionToken("/",DIV);
        if(s.equals("+"))
            return new ExpressionToken("+",PLUS);
        if(s.equals("-"))
            return new ExpressionToken("-",MINUS);
        if(s.equals(")"))
            return new ExpressionToken(")",CPAREN);
        
        return new ExpressionToken("ERROR",0);

    }
    //returns true if s is a numeric character
    public  boolean isNumeric(String s) {  
        if(s.equals("."))
            return true;
        if(s.equals("-"))
            return false;
        else
            return s.matches("[-+]?\\d*\\.?\\d+");  
    }
}  


