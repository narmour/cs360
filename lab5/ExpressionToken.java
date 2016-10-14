class ExpressionToken{
    public String s;
    public int precedence;
    public boolean isDigit;



    public ExpressionToken(String t,int p){
        s = t;
        precedence = p;
    }
}
