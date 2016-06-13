/**
 * 
 */
package com.proem.exm.utils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.StringUtils;

/**
 * 多元化数据编辑
 * @author proem33
 *
 */
public class EMultipleDateEditor extends PropertyEditorSupport {

	public final static String DEFAULT_OUTPUT_FORMAT = "dd/mm/yyyy";
	
	public final static String[] DEFAULT_INPUT_FORMATS = {
			"dd/mm/yyyy hh:mm:ss", "dd-mm-yyyy hh:mm:ss", "dd/mm/yy hh:mm:ss",
			"dd-mm-yy hh:mm:ss", "dd/mm/yyyy", "dd-mm-yyyy", "dd/mm/yy",
			"dd-mm-yy" };
	
	private String outputFormat;
	
	private String[] inputFormats;
	
	private boolean allowEmpty;

	public EMultipleDateEditor() {
		outputFormat = EMultipleDateEditor.DEFAULT_OUTPUT_FORMAT;
		inputFormats = EMultipleDateEditor.DEFAULT_INPUT_FORMATS;
		allowEmpty = false;
	}

	
	public EMultipleDateEditor(String outputFormat, String[] inputFormats) {
		this.outputFormat = outputFormat;
		this.inputFormats = inputFormats;
		allowEmpty = false;
	}

	
	public EMultipleDateEditor(String outputFormat, String[] inputFormats,
			boolean allowEmpty) {
		this.outputFormat = outputFormat;
		this.inputFormats = inputFormats;
		this.allowEmpty = allowEmpty;
	}

	
	@Override
	public String getAsText() {
		if (allowEmpty && getValue() == null) {
			return "";
		}

		return DateFormatUtils.format((Date) getValue(), outputFormat);
	}

	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			if (StringUtils.hasText(text)) {
				setValue(DateUtils.parseDate(text, inputFormats));
			} else {
				if (allowEmpty) {
					setValue(null);
				} else {
					throw new IllegalArgumentException(
							"The text specified for parsing is null");
				}
			}
		} catch (ParseException ex) {
			throw new IllegalArgumentException("Could not parse text [" + text
					+ "] into any available date input formats", ex);
		}
	}

	
	public boolean isAllowEmpty() {
		return allowEmpty;
	}

	
	public void setAllowEmpty(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	public String[] getInputFormats() {
		return inputFormats;
	}

	public void setInputFormats(String[] inputFormats) {
		this.inputFormats = inputFormats;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}
}
