package idv.wilson.demo.test.mockito.staticmock.staticmock;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaveFile {
    public void save(String fileName) throws IOException{
        File f = new File(fileName);
        if(f.canWrite()){
            System.err.println("File "+fileName+" can write");
        }else{
            System.err.println("File "+fileName+" cannot write");
        }
	}
}