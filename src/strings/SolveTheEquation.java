package strings;

public class SolveTheEquation {
    public String solveEquation(String equation) {
        // find the + -, and get the numbers
        int cur = 0;
        int pre = 0;
        int sumConst = 0;
        int sumFactor = 0;
        boolean minus = false;
        boolean stillLeft = true;
        String element;
        char c;
        while(cur <= equation.length()) {
            if(cur == equation.length()) {
                c = '$';
            } else {
                c = equation.charAt(cur);
            }
            // get the note, its postion
            if(c == '+' || c == '-' || c == '$' || c == '=') {
                // the number or Nx
                element = equation.substring(pre, cur);
                if(element.length() != 0) {
                    int num;
                    if(element.charAt(element.length()-1) == 'x') {
                        if (element.length() == 1) {
                            num = 1;
                        } else {
                            num = Integer.parseInt(element.substring(0, element.length() - 1));
                        }
                        sumFactor = minus ^ stillLeft ?
                                sumFactor + num :
                                sumFactor - num;
                    } else {
                        num = Integer.parseInt(element);
                        sumConst = minus ^ stillLeft ?
                                sumConst + num:
                                sumConst - num;
                    }
                }
                // = means minus is meaningless
                if(c == '=') {
                    stillLeft = false;
                    minus = false;
                } else {
                    minus = c == '-';
                }
                pre = cur + 1;
            }
            cur++;
        }
        //System.out.printf("factor:%d, const:%d%n", sumFactor, sumConst);
        // we get the equation: SC+SFx=0 => x=SC/SF
        if(sumConst == 0 && sumFactor == 0)
            return "Infinite solutions";
        if(sumFactor == 0)
            return "No solution";
        return "x=" + (- sumConst / sumFactor);
    }

    public static void main(String[] args) {
        String e = "-2x=-6+x-12";
        SolveTheEquation solveTheEquation = new SolveTheEquation();
        String s = solveTheEquation.solveEquation(e);
        System.out.println(s);
    }
}
