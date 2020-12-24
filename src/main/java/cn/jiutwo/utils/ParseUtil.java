package cn.jiutwo.utils;

import org.junit.Test;

/**
 * 根据分隔符，解析字符串，生成相应类型的数组
 * @author Jiutwo
 */
public class ParseUtil {

    /**
     * 将每种题目的id解析成一个数组方便使用, 分隔符为‘#’
     * @return
     */
    public String[] parseToStrArray(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
               num++;
            }
        }

        String[] tmp = new String[num];
        // i和j作为前后指针读取出每一段字符
        for (int i = 0, j = 0, k = 0; i < str.length(); i++) {
            if(str.charAt(i) == '#') {
                // i不会包含在内
                tmp[k] = str.substring(j, i);
                k++;
                i++;
                // 同步i和j
                j = i;
            }
        }
        return tmp;
    }


    /**
     * 根据分隔符‘#’，将字符串转为数字
     * @param str
     * @return
     */
    public int[] parseToIntArray(String str) {

        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                num++;
            }
        }
        int[] tmp = new int[num];
        // i和j作为前后指针读取出每一段字符
        for (int i = 0, j = 0, k = 0; i < str.length(); i++) {
            if(str.charAt(i) == '#') {
                // i不会包含在内
                tmp[k] = Integer.parseInt(str.substring(j, i));
                k++;
                i++;
                // 同步i和j
                j = i;
            }
        }
        return tmp;
    }


    /**
     * 根据分隔符‘#’，将字符串转为数字
     * @param str
     * @param num 需要解析出的字符串个数
     * @return
     */
    public int[] parseToIntArray(String str, int num) {

        int[] tmp = new int[num];
        // i和j作为前后指针读取出每一段字符
        for (int i = 0, j = 0, k = 0; i < str.length(); i++) {
            if(str.charAt(i) == '#') {
                // i不会包含在内
                tmp[k] = Integer.parseInt(str.substring(j, i));
                k++;
                i++;
                // 同步i和j
                j = i;
            }
        }
        return tmp;
    }

    /**
     * 根据分隔符‘#’，将字符串转为浮点型
     * @param str
     * @return
     */
    public float[] parseToFloatArray(String str) {

        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                num++;
            }
        }

        float[] tmp = new float[num];
        // i和j作为前后指针读取出每一段字符
        for (int i = 0, j = 0, k = 0; i < str.length(); i++) {
            if(str.charAt(i) == '#') {
                // i不会包含在内
                tmp[k] = Float.parseFloat(str.substring(j, i));
                k++;
                i++;
                // 同步i和j
                j = i;
            }
        }
        return tmp;
    }


    /**
     * 将字符串数组转为一个字符串，
     * @param str StringBuilder
     * @return
     */
    public StringBuilder parseToString(String[] str) {
        if (str == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]);
            sb.append("#");
        }
        return sb;
    }

    /**
     * 将整型数组转为一个字符串， “#”分隔
     * @param array
     * @return
     */
    public StringBuilder parseToString(int[] array) {
        if(array == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            sb.append("#");
        }
        return sb;
    }

    /**
     * 将浮点型数组转为一个字符串， “#”分隔
     * @param array
     * @return
     */
    public StringBuilder parseToString(float[] array) {
        if(array == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            sb.append("#");
        }
        return sb;
    }




//    @Test
//    public void test() {
//        String[] str = parseToStrArray("abc#ajif#jfie#");
//        for (int i = 0; i < str.length; i++) {
//            System.out.println(str[i]);
//        }
//    }



}
