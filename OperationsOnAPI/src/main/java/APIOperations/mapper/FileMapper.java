package APIOperations.mapper;

import APIOperations.model.Files;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileMapper implements RowMapper<Files> {
    @Override
    public Files mapRow(ResultSet resultSet, int i) throws SQLException {
        Files file = new Files();
        file.setId(Long.parseLong(resultSet.getString("id")));
        file.setFileName(resultSet.getString("fileName"));
        file.setFileType(resultSet.getString("fileType"));
        file.setData(resultSet.getBytes("data"));
        return file;
    }
}
