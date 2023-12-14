package APIOperations.Dao;

import APIOperations.config.JPAConfig;
import APIOperations.mapper.FileMapper;
import APIOperations.model.Files;
import Config.SpringConfig;
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

    private final STGroup stGroupFile = new STGroupFile(SpringConfig.QUERIES_FILE_PATH,
            SpringConfig.ST_DELIMITER, SpringConfig.ST_DELIMITER);


    @Override
    public List<Files> findAll() {
        ST st = stGroupFile.getInstanceOf(SpringConfig.QUERY_TYPE_SELECT);
        return template.query(st.render(), new FileMapper());
    }


    @Override
    public Files findById(int id) {
        ST st = stGroupFile.getInstanceOf(SpringConfig.QUERY_TYPE_SELECT_BY_ID);
        SqlParameterSource param = new MapSqlParameterSource().
                addValue(SpringConfig.PARAM_ID, id);
        return template.queryForObject(st.render(), param, new FileMapper());
    }

    @Override
    public void insertFile(Files file) {
        ST st = stGroupFile.getInstanceOf(SpringConfig.QUERY_TYPE_INSERT);
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue(SpringConfig.PARAM_FILE_NAME, file.getFileName())
                .addValue(SpringConfig.PARAM_FILE_TYPE, file.getFileType())
                .addValue(SpringConfig.PARAM_DATA, file.getData());

        template.update(st.render(), param);
    }

    @Override
    public void deleteFile(int id) {
        ST st = stGroupFile.getInstanceOf(SpringConfig.QUERY_TYPE_DELETE);
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put(SpringConfig.PARAM_ID, id);
        template.execute(st.render(), parameter, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                return preparedStatement.executeUpdate();
            }
        });
    }

}
