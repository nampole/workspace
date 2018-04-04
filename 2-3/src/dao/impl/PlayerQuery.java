package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import model.Player;
import model.Team;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

class PlayerQuery extends MappingSqlQuery<Player> {

    private static String SQL_PLAYER_QUERY = "SELECT player_id, player.name AS playerName, "
            + " team.team_id, team.name AS teamName "
            + " FROM player, team WHERE player.team_id = team.team_id AND player_id = ?";

    public PlayerQuery(DataSource ds) {
        super(ds, SQL_PLAYER_QUERY);
        super.declareParameter(new SqlParameter("player_id", Types.INTEGER));
        compile();
    }

    protected Player mapRow(ResultSet rs, int rowNum) throws SQLException {
        // ResultSet에서 취득한 값을 객체에 넣기
        Player player = new Player();
        player.setId(rs.getInt("player_id"));
        player.setName(rs.getString("playerName"));
        Team team = new Team();
        team.setId(rs.getInt("team_id"));
        team.setName(rs.getString("teamName"));
        player.setTeam(team);

        return player;
    }
}
