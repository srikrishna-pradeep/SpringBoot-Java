package APIOperations.Dao;

import APIOperations.model.Files;

import java.util.List;

public interface FileDao {

    List<Files> findAll();

    void insertFile(Files file);

    void deleteFile(int id);

    Files findById(int id);
}
