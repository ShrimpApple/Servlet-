package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
	// 파일 업로드
	public static String uploadFile(HttpServletRequest req, String sDirectory)
					throws ServletException, IOException {
		Part part = req.getPart("ofile");
		String partHeader = part.getHeader("content-disposition");
		String[] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"", "");
		if (!originalFileName.isEmpty()) {
			part.write(sDirectory + File.separator + originalFileName);
		}
		
		
		return originalFileName;
	}
	// 파일명 변경
	public static String renameFile(String sDirectory, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf(".")); // 확장자 잘라내기
		String now = new SimpleDateFormat("yyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}
}
