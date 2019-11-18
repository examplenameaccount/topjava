package ru.javawebinar.topjava.util;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public final class UserExtractor implements ResultSetExtractor<Map<User, List<Role>>> {
    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    @Override
    public Map<User, List<Role>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<User, List<Role>> data = new LinkedHashMap<>();
        int userId = 0;
        while (rs.next()) {
            User user = ROW_MAPPER.mapRow(rs, userId++);
            data.putIfAbsent(user, new ArrayList<>());
            Role role = Role.valueOf(rs.getString("role"));
            data.get(user).add(role);
        }
        return data;
    }
}
