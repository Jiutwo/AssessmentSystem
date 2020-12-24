package cn.jiutwo.utils;

import java.io.*;
import java.util.*;

/**
 * 敏感词过滤工具
 */
public class SensitiveUtil {
    private static  StringBuilder replaceAll;//初始化
    private static final String ENCODING = "UTF-8"; //设置编码
    private static final String REPLACESTR = "*"; //设置代替字符
    private static final int REPLACESIZE = 500;
    private static final String FILENAME = SensitiveUtil.class.getClassLoader().getResource("CensorWords.txt").getPath();//文件路径
    private static InputStream inputStream;
    private static List<String> arrayList;
    public static Set<String> sensitiveWordSet;//包含的敏感词列表，过滤掉重复项
    public static List<String> sensitiveWordList;//包含的敏感词列表，包括重复项，统计次数

    /**
     *   初始化敏感词库
     */
    public static void InitializationWork()
    {
        replaceAll = new StringBuilder(REPLACESIZE);
        for(int x=0;x < REPLACESIZE;x++)
        {
            replaceAll.append(REPLACESTR);
        }
        //加载词库
        arrayList = new ArrayList<String>();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(FILENAME);    //读取文件
            inputStream = new FileInputStream(file);
            read = new InputStreamReader(inputStream,ENCODING);
            bufferedReader = new BufferedReader(read);
            for(String txt = null;(txt = bufferedReader.readLine()) != null;){
                if(!arrayList.contains(txt))
                    arrayList.add(txt);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(null != bufferedReader)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null != read)
                    read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /** 
     * @param str 将要被过滤信息 
     * @return 过滤后的信息 
     */  
    public static String filterInfo(String str)
    {
        InitializationWork();
        sensitiveWordSet = new HashSet<String>();
    	sensitiveWordList= new ArrayList<String>();
        StringBuilder buffer = new StringBuilder(str);  
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size()); //用来记录敏感词的开始，以及敏感词的长度
        String temp;  
        for(int x = 0; x < arrayList.size();x++)  
        {  
            temp = arrayList.get(x);  
            int findIndexSize = 0;  
            for(int start = -1;(start=buffer.indexOf(temp,findIndexSize)) > -1;)  
            {  
            	//System.out.println("###replace="+temp);
                findIndexSize = start+temp.length();//从已找到的后面开始找  
                Integer mapStart = hash.get(start);//起始位置  
                if(mapStart == null || (mapStart != null && findIndexSize > mapStart))//满足1个，即可更新map  
                {  
                    hash.put(start, findIndexSize); 
                    //System.out.println("###敏感词："+buffer.substring(start, findIndexSize));
                }  
            }  
        }  
        Collection<Integer> values = hash.keySet();  
        for(Integer startIndex : values)  
        {  
            Integer endIndex = hash.get(startIndex);  
            //获取敏感词，并加入列表，用来统计数量
            String sensitive = buffer.substring(startIndex, endIndex);
            //System.out.println("###敏感词："+sensitive);
            if (!sensitive.contains("*")) {//添加敏感词到集合
            	sensitiveWordSet.add(sensitive);
            	sensitiveWordList.add(sensitive);
			}
            buffer.replace(startIndex, endIndex, replaceAll.substring(0,endIndex-startIndex));
        }  
        hash.clear();  
        return buffer.toString();  
    }  

}  

