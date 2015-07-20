import java.util.Scanner;
import java.lang.StringBuilder;
import java.lang.StringIndexOutOfBoundsException;

public class M_font{
    public static void main(String args[]){
        Line line = new Line();
        int strLength, allSpace = 0, space = 0, rSpace = 0, i;
        
        System.out.println("１行の文字数を入力してください。(9以上)");
        Scanner scan = new Scanner(System.in);
        int val = scan.nextInt();
        
        String article = "One child, one teacher, one book and one pen can change the world. Education is the only solution. Education first.";
        String[] str = article.split(" ");

        try {
            while (str.length > line.getStartI()) {

                strLength = 0;
                for (i = line.getStartI(); i < str.length; i++) {
                    space = 1;
                    rSpace = 0;

                    // 文字列の長さ取得
                    strLength = strLength + str[i].length();

//                    System.out.println("strLength: " + (strLength+i));

                    // ↓↓標準入力した１行あたりの文字数(val)についての処理↓↓
                    
                    // valが9未満の時
                    if (val < 9) {
                        space = -1;
                        
                        break;
                        
                    // valが今処理している単語と同じ長さの時
                    } else if (str[i].length() == val) {
                        break;
                        
                    // valが単語間に１スペース開けたときの１行の文章と同じ長さの時
                    } else if (strLength + i - line.getStartI() == val) {
                        allSpace = val - strLength;
                        space = allSpace / (i-line.getStartI());
                
//                        System.out.println("allspace(=): " + allSpace);
//                        System.out.println("i(=): " + i + ", space(=): " + space);

                        break;
                    
                    // valが単語間に１スペース開けたときの１行の文章より長い時
                    } else if (strLength + i - line.getStartI() > val) {
                        strLength = strLength - str[i].length();
                        i--;
                        
                        // １行の最初以外の単語の時
                        if (line.getStartI() != i) {
                            allSpace = val - strLength;
                            space = allSpace / (i-line.getStartI());
                            rSpace = allSpace % (i-line.getStartI());
                        }

//                        System.out.println("allspace(>): " + allSpace);
//                        System.out.println("i(>): " + i + ", space(>): " + space + ", allSpace2(>): " + allSpace2);
                    
                        break;
                    
                    // 文章の最後の単語、または、今処理している単語の次の単語がvalと同じ時
                    } else if (str.length - 1 == i || str[i+1].length() == val) {
                        break;
                    }
                }
            
                // 単語＋単語間のスペースをつなげて１行の文章を作成
                StringBuilder sb = new StringBuilder();
                for (int m = line.getStartI(); m <= i; m++) {
                    sb.append(str[m]);
                    for (int n = 0; n < space; n++) {
                        sb.append(" ");
                        if (rSpace != 0 && m >= i - rSpace && n == 0) {
                            sb.append(" ");
                        }
                    }
                }

                // １行の最後についたスペースを削除してlineStrに値を保存
                if (rSpace == 0) {
                    line.setLineStr(sb.substring(0, sb.length()-space));
                } else {
                    line.setLineStr(sb.substring(0, sb.length()-space-1));
                }
            
                // 出力と、１行の終わりの単語（何番目か）を保存
                line.printLine();
                line.setEndI(i);
            }
            
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("9以上の数を入力してください。");
        }
    }
}

