package idv.wilson.demo.azureBlob;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public interface FileRepository {

	public boolean save(String CompanyID, String fileID, InputStream file, long fileSize);

	public void get(String CompanyID, String fileID, OutputStream file);

	public boolean delete(String CompanyID, String fileID);

}
