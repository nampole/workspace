package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Team;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import dao.TeamDao;

public class TeamDaoImpl extends NamedParameterJdbcDaoSupport implements TeamDao {

	public List<Team> getTeamList() throws DataAccessException {
		// SQL 실행 
		return getJdbcTemplate().query("SELECT team_id as id, name FROM team",
				BeanPropertyRowMapper.newInstance(Team.class));
	}

	public Team getTeam(Integer teamId) throws DataAccessException {
		final String sql = "SELECT team_id, name FROM team WHERE team_id = :teamId";
		SqlParameterSource parameterSource = new MapSqlParameterSource("teamId", teamId);
		return getNamedParameterJdbcTemplate().query(sql, parameterSource, new TeamResultSetExtractor());
	}

	protected class TeamResultSetExtractor implements ResultSetExtractor<Team> {

		public Team extractData(ResultSet rs) throws SQLException, DataAccessException {
			// SQL의 결과가 적어도 1건이라도 있는지?
			if (rs.next()) {
				// ResultSet에서 취득한 값을 객체에 넣기
				Team team = new Team();
				team.setId(rs.getInt("team_id"));
				team.setName(rs.getString("name"));
				return team;
			} else {
				return null;
			}
		}
	}
}