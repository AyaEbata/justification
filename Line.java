class Line {
    private String lineStr;
    private int startI = 0;

    public void setLineStr(String lineStr) {
        this.lineStr = lineStr;
    }

    public void printLine() {
        System.out.println(this.lineStr);
    }
    
    public void setEndI(int endI) {
        endI++;
        this.startI = endI;        
    }
    
    public int getStartI() {
        return this.startI;
    }
}
