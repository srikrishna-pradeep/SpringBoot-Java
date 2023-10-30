package SpringPostgresDatabase.Dao;

import SpringPostgresDatabase.config.JPAConfig;
import SpringPostgresDatabase.mapper.StudentMapper;
import SpringPostgresDatabase.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
public class StudentDaoImplementation implements StudentDao{

    private Logger logger = LogManager.getLogger(StudentDaoImplementation.class);

    private DataSource dataSource = JPAConfig.getDataSource();

    private STGroup stGroup = new STGroupFile("files/queries.stg", '$', '$');

    @Override
    public List<Student> findAll() {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        ST st = stGroup.getInstanceOf("select");
        return template.query(st.render(), new StudentMapper());
    }


    @Override
    public void insertStudent(Student student) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        ST st = stGroup.getInstanceOf("insert");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource().
                addValue("id", student.getId())
                .addValue("firstName", student.getFirstName())
                .addValue("lastName", student.getLastName())
                .addValue("department", student.getDepartment());

        template.update(st.render(), param, keyHolder);
    }

    @Override
    public void updateStudent(Student student) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        ST st = stGroup.getInstanceOf("update ");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource().
                addValue("id", student.getId())
                .addValue("firstName", student.getFirstName())
                .addValue("lastName", student.getLastName())
                .addValue("department", student.getDepartment());

        template.update(st.render(), param, keyHolder);
    }

    @Override
    public void executeUpdateStudent(Student student) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        ST st = stGroup.getInstanceOf("update");

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id", student.getId());
        map.put("firstName", student.getFirstName());
        map.put("lastName", student.getLastName());
        map.put("department", student.getDepartment());
        template.execute(st.render(),map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                return preparedStatement.executeUpdate();
            }
        });
    }

    @Override
    public void deleteStudent(Student student) {
        ST st = stGroup.getInstanceOf("delete");
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id", student.getId());
        template.execute(st.render(), map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                return preparedStatement.executeUpdate();
            }
        });
    }
}
