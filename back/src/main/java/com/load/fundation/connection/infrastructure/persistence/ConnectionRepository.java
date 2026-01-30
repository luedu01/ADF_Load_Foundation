package com.load.fundation.connection.infrastructure.persistence;

import com.load.fundation.connection.domain.model.*;
import com.load.fundation.connection.domain.port.out.ConnectionPersistencePort;
import com.load.fundation.connection.infrastructure.persistence.util.query.SqlQueryConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Slf4j
@Repository
public class ConnectionRepository implements ConnectionPersistencePort {

    private final JdbcTemplate jdbcTemplate;

    public ConnectionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapper reutilizable para Conecciones.
    private RowMapper<Connection> connectionRowMapper() {
        return (rs, rowNum) -> {

/*
            ConnectionType type = new ConnectionType(
                    rs.getInt("Connection_Type_Id"),
                    rs.getString("Connection_Type_Name")
            );

            ConnectionSubtype subtype = new ConnectionSubtype(
                    rs.getInt("Connection_Subtype_Id"),
                    rs.getInt("Connection_Type_Id"),
                    rs.getString("Connection_Subtype_Name")
            );
*/

            // CREDENTIAL
            ConnectionCredential credential = null;
            String credUser = rs.getString("Credential_User_Name");
            if (credUser != null) {
                credential = new ConnectionCredential(
                        rs.getInt("Connection_Id"),
                        credUser,
                        rs.getString("Credential_Password_Hash")
                );
            }
            //TOKEN
            ConnectionToken token = null;
            Integer tokenId = (Integer) rs.getObject("Token_Id", Integer.class);
            if (tokenId != null) {
                token = new ConnectionToken(
                        tokenId,
                        rs.getInt("Connection_Id"),
                        rs.getString("Token_Name"),
                        rs.getString("Token_Hash"),
                        "Y".equalsIgnoreCase(rs.getString("Token_Is_Active"))

                );
            }

            Connection conn = new Connection();
            conn.setId(rs.getInt("Connection_Id"));
            conn.setName(rs.getString("Connection_Name"));
            conn.setDescription(rs.getString("Connection_Description"));
            //conn.setType(type);
            //conn.setSubtype(subtype);
            conn.setServerName(rs.getString("Server_Name"));
            conn.setAdditionalInfo(rs.getString("Additional_Info_Desc"));
            conn.setCredential(credential);
            conn.setToken(token);
            // conn.setGroupId(...);

            // Auditoría:
             conn.setCreatedBy((Integer) rs.getObject("Created_By", Integer.class));
             conn.setUpdatedBy((Integer) rs.getObject("Updated_By", Integer.class));
            // etc.

            return conn;
        };
    }

    //Obtener todas las conexiones disponibles
    //TODO validar la forma de adicionar los parametros de auditoria
    @Override
    public List<Connection> findAll() {
        return jdbcTemplate.query(
                //SELECT grande con todos los joins o el simple de CONNECTION
                SqlQueryConnection.SELECT_ALL_CONNECTIONS,
                connectionRowMapper()
        );
    }

    @Override
    public Connection findById(Integer connectionId) {
        List<Connection> result = jdbcTemplate.query(
                // SELECT  WHERE c.Connection_Id = ?
                SqlQueryConnection.SELECT_CONNECTION_BY_ID,
                ps -> ps.setInt(1, connectionId),
                connectionRowMapper()
        );
        //Ternario para retorno de la 1ra conincidencia
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Connection> findByGroupId(Integer groupId) {
        return jdbcTemplate.query(
                SqlQueryConnection.SELECT_CONNECTION_BY_GROUPID,
                ps -> ps.setInt(1, groupId),
                connectionRowMapper()
        );
    }

    @Override
    public void save(Connection connection) {
        // TODO: implementar insert
    }

    @Override
    public void update(Integer connectionId, Connection connection) {
        // TODO: implementar update
    }
}
