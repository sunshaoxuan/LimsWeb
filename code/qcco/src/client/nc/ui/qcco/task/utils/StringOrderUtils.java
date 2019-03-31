package nc.ui.qcco.task.utils;

import java.util.*;

/**
 * tab标签工具类
 */
public class StringOrderUtils {


    /**
     * @param  arrays{A1,A2,A4,B1,C1}
     * @param rowNumList 每组的数量,比如:rowNumList[0]为A组数量,rowNumList[1]为B组数量,rowNumList.size()为分组数量
     * Out : String "A1-A2,A4,B1,C1"
     *
     * @author Tank
     * @date 2019年3月15日10:01:40
     */
    public static String outOrderString(String[] arrays, List<Integer> rowNumList) throws Exception {
        boolean[][] orderTable = init(rowNumList);
        putArrayInTable(arrays,orderTable,rowNumList);
        return outTableString(orderTable,rowNumList);
    }

    /**
     * @param  arrays String "A1-A2,A4,B1,C1"
     * @param rowNumList 每组的数量,比如:rowNumList[0]为A组数量,rowNumList[1]为B组数量,rowNumList.size()为分组数量
     * Out : array{A1,A2,A4,B1,C1}
     *
     * @author Tank
     * @date 2019年3月15日10:01:40
     */
    public static String[] outDisOrderArray(String arrays,List<Integer> rowNumList) throws Exception {
        boolean[][] orderTable = init(rowNumList);
        putStringInTable(arrays,orderTable,rowNumList);
        return outTableArrays(orderTable,rowNumList);
    }

    /**
     * 创建一个以最大数量为列数,最大组数为行数的二维表
     * @param rowNumList
     */
    private static boolean[][] init(List<Integer> rowNumList) throws Exception {
        if(null == rowNumList){
            throw new Exception("组内数量参数出错!");
        }
        //寻找最大的样品数量
        int max = 0;
        for(int i = 0;i<rowNumList.size();i++){
            if(rowNumList.get(i)!=null){
                if(max < rowNumList.get(i)){
                    max = rowNumList.get(i);
                }
            }else{
                throw new Exception((char)(i+65) +"组内样品数量不能为0");
            }
        }
        if(0 == max){
            throw new Exception("组内样品数量不能为0");
        }
        return new boolean[rowNumList.size()][max+1];
    }
    /**
     * 将二维表输出成String
     */
    private static String[] outTableArrays(boolean[][] orderTable,List<Integer> rowNumList) {
        Set<String> resultSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        if (orderTable != null) {
            for(int i = 0 ; i < rowNumList.size() ; i ++){
                for (int j = 1 ; j <=rowNumList.get(i);j++){
                    if(orderTable[i][j]){
                        sb.append((char)(i+65)).append(j);
                        resultSet.add(sb.toString());
                        sb.delete(0,sb.length());
                    }
                }
            }
        }
        return resultSet.toArray(new String[0]);
    }

    /**
     * 将String展开到二维表中
     */
    private static void putStringInTable(String arrays,boolean[][] orderTable,List<Integer> rowNumList) throws Exception {
        if(orderTable.length == 0){
            return ;
        }
        if (arrays != null && arrays.length() > 0) {
            String[] tabArrays = arrays.replaceAll(" ", "").split(",");
            for (String tabString : tabArrays) {
                if (tabString.indexOf('-') == -1) {
                    //单个存入
                    try {
                        int row = tabString.charAt(0);
                        int col = Integer.parseInt(tabString.substring(1, tabString.length()));
                        orderTable[row - 65][col] = true;
                    } catch (Exception e) {
                        throw new Exception("非法字符:" + tabString);
                    }
                } else {
                    //线性存入
                    String[] splitTabs = tabString.split("-");
                    if (splitTabs.length != 2) {
                        throw new Exception("非法字符:" + tabString);
                    } else {
                        try {
                            int startRow = splitTabs[0].charAt(0);
                            int startCol = Integer.parseInt(splitTabs[0].substring(1, splitTabs[0].length()));
                            int endRow = splitTabs[1].charAt(0);
                            int endCol = Integer.parseInt(splitTabs[1].substring(1, splitTabs[1].length()));

                            //顺序相反先交换
                            if (startRow > endRow || (startRow == endRow && startCol > endCol)) {
                                int temp = endRow;
                                endRow = startRow;
                                startRow = temp;

                                temp = startCol;
                                startCol = endCol;
                                endCol = temp;
                            }
                            //开始存入
                            for (int i = (startRow - 65); (i < 26 && i <= endRow - 65); i++) {
                                for (int j = startCol; (j <= rowNumList.get(i) && (i != endRow-65 || j <= endCol)); j++) {
                                    orderTable[i][j] = true;
                                }
                            }
                        } catch (Exception e) {
                            throw new Exception("非法字符:" + tabString);
                        }
                    }
                }
            }
        }
    }

    /**
     * 将二维表输出成String
     */
    private static String outTableString(boolean[][] orderTable,List<Integer> rowNumList) {
        if (orderTable == null||orderTable.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        //声明一条线
        int lineLength = 0;
        for (int i = 0; i < rowNumList.size(); i++) {
            for (int j = 1; j <= rowNumList.get(i); j++) {
                if (orderTable[i][j]) {
                    if (0 == lineLength) {
                        sb.append((char) (i + 65)).append(j);
                        lineLength++;
                    } else if (1 == lineLength) {
                        sb.append("-");
                        lineLength++;
                        if (rowNumList.get(i) == j && (rowNumList.size()-1) == i) {
                            //最后一列最后一行的时候就直接加完了
                            sb.append((char) (i + 65)).append(j).append(",");
                        }
                    } else if (1 < lineLength) {
                        if (rowNumList.get(i) == j && (rowNumList.size()-1) == i ) {
                            sb.append((char) (i + 65)).append(j).append(",");
                        }
                        lineLength++;
                    }
                } else {
                    if(lineLength == 0){
                        ;
                    }else if(lineLength == 1){
                        lineLength = 0;
                        sb.append(",");
                    }else{
                        lineLength = 0;
                        //lineLength断了,接上一个
                        if(j>1){
                            sb.append((char) (i + 65)).append(j - 1).append(",");
                        }else{
                            sb.append((char) (i - 1 + 65)).append(rowNumList.get(i-1)).append(",");
                        }

                    }

                }
            }
        }
        if(lineLength != 1){
            sb.deleteCharAt(sb.length()-1).toString();
        }
        return sb.toString();
    }

    /**
     * 将tab数组存进表中
     */
    private static void putArrayInTable(String[] arrays,boolean[][] orderTable,List<Integer> rowNumList) throws Exception {
        if(orderTable.length == 0){
            return ;
        }
        if (arrays != null && arrays.length > 0) {
            //数组展开成二维表
            for (String tab : arrays) {
                if (null != tab && tab.replaceAll(" ", "").length() >= 2) {
                    tab = tab.replaceAll(" ", "");
                    try {
                        int row = tab.charAt(0);
                        int col = Integer.parseInt(tab.substring(1, tab.length()));
                        orderTable[row - 65][col] = true;
                    } catch (Exception e) {
                        throw new Exception("字符非法:" + tab);
                    }
                }
            }
        }
    }

}