package APIOperations.service;


import APIOperations.Dao.FileDaoImplementation;
import APIOperations.model.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private Logger logger = LogManager.getLogger(FileService.class);

    @Autowired
    private FileDaoImplementation fileDaoImplementation;

    public void saveFile(MultipartFile files) {
        String fileName = StringUtils.cleanPath(files.getOriginalFilename());
        try {
            Files file = new Files(fileName, files.getContentType(), files.getBytes());
            fileDaoImplementation.insertFile(file);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


    public List<Files> getFiles(){
        return fileDaoImplementation.findAll();
    }

    public Files getFile(int id) {
        return fileDaoImplementation.findById(id);
    }

    public void deleteFile(int id){
        fileDaoImplementation.deleteFile(id);
    }

}
