package com.online.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener  extends AnalysisEventListener<MergeExcel> {
    //监听器 读取解析excel内容
    @Override
    public void invoke(MergeExcel data, AnalysisContext analysisContext) {
        System.out.println("****"+data);
    }

    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }


    //读取完成之后的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
