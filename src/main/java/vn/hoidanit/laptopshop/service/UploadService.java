package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {
    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    // hàm lưu 1 file vào 1 folder
    public String handelSaveUploadFile(MultipartFile file, String targetFolder) {
        // relative path: đường dẫn tương đối
        // absolute path: đường dẫn tuyệt đối
        String rootPath = this.servletContext.getRealPath("/resources/images");
        // hiểu đơn giản: hàm nảy để lấy ra đường đẫn tuyệt đối (đến thư mục webapp)
        String finalName = "";
        try {
            byte[] bytes = file.getBytes(); // lấy hình ảnh dưới dạng binary
            // tạo thư mục nơi lưu trữ ảnh
            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists())
                dir.mkdirs();
            finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename(); // quy định tên file để lưu vào
                                                                                       // database
            // từ thư mục đã tạo lấy đường dẫn tuyệt đối tạo file để lưu ảnh dưới dạng
            // binary
            File serverFile = new File(dir.getAbsoluteFile() + File.separator + finalName);
            // quá trình ghi file xuống ổ đĩa
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalName;
    }
}
