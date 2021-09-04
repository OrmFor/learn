package com.design.learn.wx.lbs;


import java.io.*;

import java.util.Arrays;
import java.util.List;

import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.expression.ExpressionEvaluator;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.poi.PoiTransformer;

public class ExcelUtil {

    public static void main(String[] args) throws  Exception {

        InputStream is = new FileInputStream(new File("C:\\Users\\Thinkpad\\Desktop\\uploadModel\\BatchUploadModel.xlsx"));
        OutputStream os = new FileOutputStream(new File("out.xlsx"));
        //Transformer transformer = TransformerFactory.createTransformer(is, os);
        PoiTransformer transformer = PoiTransformer.createTransformer(is, os);

        ExpressionEvaluator expressionEvaluator = new JexlExpressionEvaluator();

        transformer.getTransformationConfig().setExpressionEvaluator(expressionEvaluator);

        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        List<Area> xlsAreaList = areaBuilder.build();
        Area xlsArea = xlsAreaList.get(0);

        List<String> strings = Arrays.asList(new String[]{
                "ud_id","名称","地址","电话","经度","纬度", "轮廓点串","x.adcode","x.agent_seller_id","x.bscard_b","x.bscard_f","x.citycode","x.contacts_person",
                "x.contacts_phone","x.contacts_phone_2","x.contacts_tel","x.door_photo_url","x.is_visible","x.pcode","x.seller_id","x.seller_name","x.shop_id"
        });

        Context context = new Context();

        context.putVar("ud_id", "a");
        context.putVar("list", strings);

        xlsArea.applyAt(new CellRef("Sheet1!A1"), context);

        transformer.write();
    }

}
