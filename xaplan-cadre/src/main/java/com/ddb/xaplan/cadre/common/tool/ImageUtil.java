package com.ddb.xaplan.cadre.common.tool;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static final boolean isImage(InputStream input) {
		boolean flag = false;
		try {
			BufferedImage bufreader = ImageIO.read(input);
			int width = bufreader.getWidth();
			int height = bufreader.getHeight();
			if (width == 0 || height == 0) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (IOException e) {
			flag = false;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	public static final boolean checkImageSize(InputStream input,int maxwidth,int maxheight) {
		boolean flag = false;
		try {
			BufferedImage bufreader = ImageIO.read(input);
			int width = bufreader.getWidth();
			int height = bufreader.getHeight();
			if (width == 0 || height == 0) {
				flag = false;
			} else {
				flag = true;
			}
			if (maxwidth <width ) {
				return false;
			}
			if(maxheight<height){
				return false;
			}
		} catch (IOException e) {
			flag = false;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

}
