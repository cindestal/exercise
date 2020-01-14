import java.util.Arrays;

public class Num2Rmb {
    private String[] hanArr =  {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    private String[] unitArr = {"千","百","十"};
    private String[] unitXiao = {"角","分"};

    private String[] divide(double num){
        long zheng = (long) num;
        long xiao = Math.round((num - zheng)*100);
        return new String[]{zheng+"",String.valueOf(xiao)};
    }

    private String toHanZh(String numStr){
        String result = "";
        int numLen = numStr.length();
        for (int i=0;i<numLen;i++){
            int num = numStr.charAt(i) - 48;
            if(i!=numLen-1 && num!=0) {
                result += hanArr[num] + unitArr[i];
            }else if(i==numLen-1 && num==0){
                result += "";
            }
//            else if(i!=numLen-1 && num==0){
//                result += "";
//            }
            else {
                    result += hanArr[num];
                }
            }

        return result;
    }
    private String toHanXiao(String numStr){
        int jiao = numStr.charAt(0)-48;
        int fen = numStr.charAt(1)-48;
        System.out.println("jiao:"+jiao+" fen:"+fen);
        if(jiao == 0 && fen != 0){
         return hanArr[fen] + unitXiao[1];
        }else if (jiao == 0 && fen == 0){
            return "";
        }else if(jiao != 0 && fen != 0){
            return hanArr[jiao]+unitXiao[0]+hanArr[fen]+unitXiao[1];
        }else{
            return hanArr[jiao]+unitXiao[0];
        }
    }

    private String toHanStr(double num){
        long zheng = (long) num;
        long xiao = Math.round((num - zheng)*100);
        System.out.println("zheng:"+zheng+" xiao:"+xiao);
        String result = "";
        if(zheng != 0 && xiao != 0){
            result += toHanZh(String.valueOf(zheng)) + "零"+ toHanXiao(String.valueOf(xiao));
        }
        if(xiao == 0 || zheng == 0){
            result += toHanZh(String.valueOf(zheng)) + toHanXiao(String.valueOf(xiao));
        }
        return result;
    }

    public static void main(String[] args) {
        Num2Rmb num2Rmb = new Num2Rmb();
        System.out.println(Arrays.toString(num2Rmb.divide(23452.1352)));
        System.out.println(num2Rmb.toHanStr(3452.1352));
    }

}
