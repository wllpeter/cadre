package com.ddb.xaplan.cadre.common.tool;

import org.apache.commons.lang3.StringUtils;

public class ImgSuffixUtil {
	public static String IMG_SUFFIX_NAME = ".bmp.wbmp.jpg.jpeg.gif.png";
	
	/**
	 * 检测文件后缀名是否是图片文件后缀名
	 * @param filename
	 * @return
	 */
	public static boolean isImg(String filename){
		boolean isImg = false;
		if (StringUtils.isNotBlank(filename)) {
			String suffixName = filename.substring(filename.lastIndexOf('.'));
			if (StringUtils.isNotBlank(suffixName)) {
				suffixName = suffixName.toLowerCase();
				isImg = IMG_SUFFIX_NAME.contains(suffixName);
			}			
		}
		return isImg;
	}
}
