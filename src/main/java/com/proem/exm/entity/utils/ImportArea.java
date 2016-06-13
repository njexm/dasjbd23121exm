package com.proem.exm.entity.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.util.Date;

public class ImportArea {
	@Autowired
	static
	AreaService areaService;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getAllByExcel();
	}

	@SuppressWarnings("deprecation")
	public static  void getAllByExcel(){
        try {
        	HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("D:/Downloads/importArea.xls"));  
        	Area area=new Area();
        	HSSFSheet sheet = wb.getSheetAt(0);   
        	HSSFRow row = null;//对应excel的行  
        	HSSFCell cell=null;//对应excel的列  
        	 int totalRow=sheet.getLastRowNum();
        	
            
            for (int i = 2; i <= totalRow; i++) {
                row = sheet.getRow(i);
                //System.out.println(row.getCell(0).toString());
                if(row != null){
                	//品名
                	HSSFCell id=row.getCell(0);
                	area.setId(getValue(id)==null?"":getValue(id));
                	HSSFCell name=row.getCell(1);
                	area.setAreaName(getValue(name)==null?"":getValue(name));
                	HSSFCell parentId=row.getCell(2);
                	area.setParentId(getValue(parentId)==null?"":getValue(parentId));
                	HSSFCell shortName=row.getCell(3);
                	area.setShortName(getValue(shortName)==null?"":getValue(shortName));
                	HSSFCell levelType=row.getCell(4);
                	area.setLevelType(getValue(levelType)==null?"":getValue(levelType));
                	HSSFCell cityCode=row.getCell(5);
                	area.setCityCode(getValue(cityCode)==null?"":getValue(cityCode));
                	HSSFCell zipCode=row.getCell(6);
                	area.setZipCode(getValue(zipCode)==null?"":getValue(zipCode));
                	HSSFCell quancheng=row.getCell(7);
                	area.setQuanCheng(getValue(quancheng)==null?"":getValue(quancheng));
                	HSSFCell lng=row.getCell(8);
                	area.setLng(getValue(lng)==null?"":getValue(lng));
                	HSSFCell lat=row.getCell(9);
                	area.setLat(getValue(lat)==null?"":getValue(lat));
                	HSSFCell pinyin=row.getCell(10);
                	area.setPinyin(getValue(pinyin)==null?"":getValue(pinyin));
                	area.setCreateTime(new Date());
                	area.setUpdateTime(new Date());
                	}
            	}
                }catch (Exception e) {
                	e.printStackTrace();
                } 
            }
	
	 private void insert(Area area) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("static-access")
     private static String getValue(HSSFCell hssfCell) {
 	if(hssfCell !=null){
             if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
                  // 返回布尔类型的值
                  return String.valueOf(hssfCell.getBooleanCellValue());
             } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
                 // 返回数值类型的值
                  return String.valueOf(hssfCell.getNumericCellValue());
              } else {
                  // 返回字符串类型的值
                 return String.valueOf(hssfCell.getStringCellValue());
             }
         }else{
 	    	return null;
 	    }
 }
}
