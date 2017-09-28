package com.meepo.mybatis.utils;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.neza.encrypt.BASE64;
import com.neza.tool.ImageUtil;

public class ConstantUtil {

	public static String IMG_SERVER = "http://pic.slk368.com/";
	public static String IMG_BIG = "800x800";
	public static String IMG_MIDDLE = "480x480";
	public static String IMG_SMALL = "120x120";

	public static String encrypt(String text) {

		if (text != null && !text.equals("")) {
			text = BASE64.encrypt(text, "UTF-8");
			return text;
		}
		return "0";
	}

	public static String dencrypt(String text) {

		if (text != null && !text.equals("")) {
			text = BASE64.dencrypt(text, "UTF-8");
			return text;
		}
		return "0";
	}

	public static void performEnTask(HttpServletResponse response, String txt) {
		PrintWriter out;
		try {
			response.reset();
			// response.setContentType("text/xml");
			response.setContentType("text/html; charset=utf-8");
			response.addHeader("Access-Control-Allow-Origin", "*");		
			// response.setCharacterEncoding("UFT-8");

			String temp = ConstantUtil.encrypt(txt);

			out = response.getWriter();

			out.print(temp);
			out.flush();
			out.close();

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());

		}

	}

	public static void performTask(HttpServletResponse response, String txt) {
		PrintWriter out;
		try {
			response.reset();
			// response.setContentType("text/xml");
			response.setContentType("text/html; charset=utf-8");
			response.addHeader("Access-Control-Allow-Origin", "*");			
			// response.setCharacterEncoding("UFT-8");
			out = response.getWriter();
			out.print(txt);
			out.flush();
			out.close();

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());

		}

	}

	public static void writeScaleImage(int width, int height, String vname,
			String fromPath) {
		try {

			// Thumbnails.of(fromPath+File.separator+vname)
			// .size(100, 80)
			// .forceSize(width, height)
			// .outputFormat("jpg") //生成图片的格式为png
			// .outputQuality(0.9f) //生成质量为80% //
			// .toFile(fromPath+File.separator+width+"_"+height+File.separator+vname);

			File info = new File(fromPath + File.separator + width + "_"
					+ height);
			if (!info.exists()) {
				info.mkdirs();
			}
			ImageUtil is = new ImageUtil();
			System.out.println("fromPath+File.separator+vname=" + fromPath
					+ File.separator + vname);
			System.out
					.println("fromPath+File.separator+width_height+File.separator+vname="
							+ fromPath
							+ File.separator
							+ width
							+ "_"
							+ height
							+ File.separator + vname);
			is.saveImageAsJpg(fromPath + File.separator + vname, fromPath
					+ File.separator + width + "_" + height + File.separator
					+ vname, width, height);
			System.out.println("生成缩略图完成!");

		} catch (Exception e) {
			System.out.println("生成缩略图失败：" + e.getMessage());
		}

	}

	public static String parseImg(String path) {
		if (path != null && !path.equals("")) {
			int p = path.lastIndexOf("=");
			if (p > 0)
				path = path.substring(p);
			p = path.lastIndexOf("\\");
			if (p > 0)
				path = path.substring(p);
			p = path.lastIndexOf("\\/");
			if (p == -1)
				p = path.indexOf("\\");
			if (p > 0)
				path = path.substring(p);

		}

		return path;
	}

	public static String getBigSrc(String img) {
		String temp = "";
		if (img.indexOf(".") == -1) {
			temp = "0000-00-00" + File.separator + ConstantUtil.IMG_BIG
					+ File.separator + "default.jpg";
			return ConstantUtil.IMG_SERVER + File.separator + temp;
		}
		if (img != null && !img.equals("")) {
			int p = img.indexOf("/");
			if (p == -1)
				p = img.indexOf("\\");
			if (p > 1) {
				temp = img.substring(0, p) + File.separator
						+ ConstantUtil.IMG_BIG + File.separator
						+ img.substring(p);
			}

		} else {
			temp = "0000-00-00" + File.separator + ConstantUtil.IMG_BIG
					+ File.separator + "default.jpg";
		}
		return ConstantUtil.IMG_SERVER + File.separator + temp;
	}

	public static String getSmallSrc(String img) {
		String temp = "";
		if (img.indexOf(".") == -1) {
			temp = "0000-00-00" + File.separator + ConstantUtil.IMG_SMALL
					+ File.separator + "default.jpg";
			return ConstantUtil.IMG_SERVER + File.separator + temp;
		}

		if (img != null && !img.equals("")) {
			int p = img.indexOf("/");
			if (p == -1)
				p = img.indexOf("\\");
			if (p > 1) {
				temp = img.substring(0, p) + File.separator
						+ ConstantUtil.IMG_SMALL + File.separator
						+ img.substring(p + 1);
			}
			temp = ConstantUtil.IMG_SERVER + File.separator + temp;
			return temp;
		} else {
			temp = "0000-00-00" + File.separator + ConstantUtil.IMG_SMALL
					+ File.separator + "default.jpg";
		}
		return ConstantUtil.IMG_SERVER + File.separator + temp;
	}

	public static String getMiddleSrc(String img) {
		String temp = "";
		if (img.indexOf(".") == -1) {
			temp = "0000-00-00" + File.separator + ConstantUtil.IMG_SMALL
					+ File.separator + "default.jpg";
			return ConstantUtil.IMG_SERVER + File.separator + temp;
		}

		if (img != null && !img.equals("")) {
			int p = img.indexOf("/");
			if (p == -1)
				p = img.indexOf("\\");
			if (p > 1) {
				temp = img.substring(0, p) + File.separator
						+ ConstantUtil.IMG_MIDDLE + File.separator
						+ img.substring(p + 1);
			}
			temp = ConstantUtil.IMG_SERVER + File.separator + temp;
			return temp;
		} else {
			temp = "0000-00-00" + File.separator + ConstantUtil.IMG_SMALL
					+ File.separator + "default.jpg";
		}
		return ConstantUtil.IMG_SERVER + File.separator + temp;
	}

	public static String getSrc(String img) {
		String temp = "";
		if (img.indexOf(".") == -1) {
			temp = "0000-00-00" + File.separator + "default.jpg";
			return ConstantUtil.IMG_SERVER + File.separator + temp;
		}
		if (img != null && !img.equals("")) {

			return ConstantUtil.IMG_SERVER + File.separator + img;
		} else {
			temp = "0000-00-00" + File.separator + "default.jpg";
		}
		return ConstantUtil.IMG_SERVER + File.separator + temp;
	}


	
	public static String getUploadImageSrc(String img) {
		String temp = "";
		if (img == null || img.equals("") || img.indexOf(".") == -1) {
			temp = "0000-00-00" + File.separator + "default.jpg";
			return ConstantUtil.IMG_SERVER + File.separator + temp;
		} else {
			return ConstantUtil.IMG_SERVER + File.separator + "upload"
					+ File.separator + img;
		}

	}

	public static BigDecimal min(Set prices) {

		BigDecimal min = BigDecimal.ZERO;
		for (Object obj : prices) {
			if (min.compareTo((BigDecimal) obj) > 0) {
				min = (BigDecimal) obj;
			}
		}
		return min;
	}

	public static void main(String[] args) {
		// String
		// text="eydpZCc6MSwnbmFtZSc6J+adjumdqScsJ25vJzonbGlnZScsJ3N0YXR1cyc6MSwndGVsJzonbnVsbCcsJ3R5cGUnOjF9";
		// text=ConstantUtil.dencrypt(text);
		// Agent agent = Agent.getAgentByJsonText(text);
		// System.out.println("agent.toQr()=" + agent.toQr());
		String temp = "eydyZXN1bHQnOjAsJ21zZyc6J7PJuabJ6NbDss7VucnMbGlnZc6qwOuzoSd9";
		// eydyZXN1bHQnOjAsJ21zZyc6J7PJuabJ6NbDss7VucnMbGlnZc6qwOuzoSd9
		// http://192.168.1.86:8080/exhibition/exhibition/agentlist.do?thisAction=getAgentJSonByNo&no=lige

		// ConstantUtil.zoomImage("d:\\ccc.jpg", "d:\\ddd.jpg", 120, 120);
	}
}
