package student.com.service;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class ProfileImageService {
	public static String generateStringOfImage(Blob imgSrc) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            // Get binary stream from Blob
            inputStream = imgSrc.getBinaryStream();
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            // Read bytes from binary stream and write to ByteArrayOutputStream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Convert bytes to byte array
            byte[] imageBytes = outputStream.toByteArray();

            // Encode byte array to Base64 string
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            return base64Image;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null; // Return null in case of error
        } finally {
            // Close streams in finally block to ensure they are closed even if an exception occurs
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
}
