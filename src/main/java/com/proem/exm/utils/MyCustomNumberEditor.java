package com.proem.exm.utils;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.StringUtils;

public class MyCustomNumberEditor extends CustomNumberEditor {

    public MyCustomNumberEditor(Class<? extends Number> numberClass) throws IllegalArgumentException {
        super(numberClass, true);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
            setValue(0);
        }else { 
            try {
				super.setAsText(text.trim());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				setValue(0);
			}
        }
    }

}