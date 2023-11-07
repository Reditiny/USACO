import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class ItsAllAboutTheBase {
    static int k;
    static char[] baseX;
    static char[] baseY;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("whatbase.in"));
        PrintWriter pw = new PrintWriter("whatbase.out");
        k = Integer.parseInt(r.readLine());
        for(int i = 0; i < k; i++){
            StringTokenizer st = new StringTokenizer(r.readLine());
            baseX = st.nextToken().toCharArray();
            baseY = st.nextToken().toCharArray();
            int xBase = 10, yBase = 10;
            while(true){
                int x = getNumOnBase(xBase, baseX);
                int y = getNumOnBase(yBase, baseY);
                if(x == y){
                    pw.println(xBase + " " + yBase);
                    break;
                }else if(x < y){
                    xBase++;
                }else{
                    yBase++;
                }
            }
        }
        pw.close();
    }

    /**
     * 计算数字在指定进制下的值
     */
    static int getNumOnBase(int base, char[] num){
        int ans = 0;
        int basePow = 1;
        for(int i = 0 ;i<3;i++){
            ans += (num[2-i]-'0')*basePow;
            basePow *= base;
        }
        return ans;
    }
}