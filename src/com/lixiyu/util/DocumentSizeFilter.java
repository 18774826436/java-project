package com.lixiyu.util;

import java.awt.Toolkit;

import javax.print.attribute.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocumentSizeFilter extends DocumentFilter {
	private int maxSize;// 获得文本的最大长度
	public DocumentSizeFilter(int maxSize) {
		this.maxSize = maxSize;// 获得用户输入的最大长度
	}
	public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
		if ((fb.getDocument().getLength() + string.length()) <= maxSize) {// 如果插入操作完成后小于最大长度
		super.insertString(fb, offset, string, attr);// 调用父类中的方法
		} else {
			Toolkit.getDefaultToolkit().beep();// 发出提示声音
		}
	}
	public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
		if ((fb.getDocument().getLength() + text.length() - length) <= maxSize) {// 如果替换操作完成后小于最大长度
			super.replace(fb, offset, length, text, attrs);// 调用父类中的方法
		} else {
			Toolkit.getDefaultToolkit().beep();// 发出提示声音
		}
	}
}
