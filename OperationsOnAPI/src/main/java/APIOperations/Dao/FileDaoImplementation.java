package APIOperations.Dao;

import APIOperations.config.JPAConfig;
import APIOperations.mapper.FileMapper;
import APIOperations.model.Files;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class FileDaoImplementation implements FileDao{

    private final DataSource dataSource = JPAConfig.getDataSource();

    private final NamedParameterJdbcTemplate template =new NamedParameterJdbcTemplate(dataSource);

    private final STGroup stGroupFile = new STGroupFile("files/queries.stg",
            '$', '$');


    @Override
    public List<Files> findAll() {
        ST st = stGroupFile.getInstanceOf("select");
        return template.query(st.render(), new FileMapper());
    }


    @Override
    public Files findById(int id) {
        ST st = stGroupFile.getInstanceOf("selectById");
        SqlParameterSource param = new MapSqlParameterSource().
                addValue("id", id);
        return template.queryForObject(st.render(), param, new FileMapper());
    }

    @Override
    public void insertFile(Files file) {
        ST st = stGroupFile.getInstanceOf("insert");
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("fileName", file.getFileName())
                .addValue("fileType", file.getFileType())
                .addValue("data", file.getData());

        template.update(st.render(), param);
    }

    @Override
    public void deleteFile(int id) {
        ST st = stGroupFile.getInstanceOf("delete");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        template.execute(st.render(), map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                return preparedStatement.executeUpdate();
            }
        });
    }

}
