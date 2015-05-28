package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Coordinate;
import model.Game;

public class DamaService {
	/** Connection variable. */
	public static Connection conn = null;
//	static {
//		
//	}
	
	/**
	 * Closes databse connection. 
	 */
	public void closeDatabaseConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Downloads saved game state from database.
	 * 
	 * @param game
	 *            Game state.
	 */
	public void downloadGameState(Game game) {
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g",
					"H_HG8H7I", "kassai");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		final String SELECTp1 = "SELECT * FROM DAMAGAME where playername = '"
				+ game.getPlayerA().getName() + "'";
		final String SELECTp2 = "SELECT * FROM DAMAGAME where playername = '"
				+ game.getPlayerB().getName() + "'";
		PreparedStatement pstmtSelect;
		ResultSet rs;

//		game.startNewGame();
		game.getPlayerA().getPlacedPieces().clear();
		game.getPlayerB().getPlacedPieces().clear();

		try {
			pstmtSelect = conn.prepareStatement(SELECTp1);
			rs = pstmtSelect.executeQuery();
			while (rs.next()) {
				game.getPlayerA().setName(rs.getString("playername"));
				game.getPlayerA().setActual(
						(rs.getInt("actual") == 0 ? false : true));
				Coordinate tmp = new Coordinate(rs.getInt("xcoordinate"),
						rs.getInt("ycoordinate"));
				game.getPlayerA().getPlacedPieces().add(tmp);
				game.setTableVal(tmp, rs.getInt("COORDINATEVALUE"));
			}

			pstmtSelect = conn.prepareStatement(SELECTp2);
			rs = pstmtSelect.executeQuery();
			while (rs.next()) {
				game.getPlayerB().setName(rs.getString("playername"));
				game.getPlayerB().setActual(
						(rs.getInt("actual") == 0 ? false : true));
				Coordinate tmp = new Coordinate(rs.getInt("xcoordinate"),
						rs.getInt("ycoordinate"));
				game.getPlayerB().getPlacedPieces().add(tmp);
				game.setTableVal(tmp, rs.getInt("COORDINATEVALUE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Uploads game state to database.
	 */
	public void uploadGameState(Game game) {
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g",
					"H_HG8H7I", "kassai");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		final String INSERT = "INSERT INTO DAMAGAME VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstmtInsert;

		
		try {
			pstmtInsert = conn.prepareStatement(INSERT);
			// tábla tartalom törlés
			PreparedStatement delete = conn
					.prepareStatement("DELETE FROM DAMAGAME");
			delete.executeUpdate();

			for (Coordinate c : game.getPlayerA().getPlacedPieces()) {
				pstmtInsert.setString(1, game.getPlayerA().getName());
				int turn = game.getPlayerA().isActual() ? 1 : 0;
				pstmtInsert.setInt(2, turn);
				pstmtInsert.setInt(3, c.getX());
				pstmtInsert.setInt(4, c.getY());
				pstmtInsert.setInt(5, game.getTableVal(c));
				pstmtInsert.executeUpdate();
			}
			int i = 0;
			for (Coordinate c : game.getPlayerB().getPlacedPieces()) {
				pstmtInsert.setString(1, game.getPlayerB().getName());
				int turn = game.getPlayerB().isActual() ? 1 : 0;
				pstmtInsert.setInt(2, turn);
				pstmtInsert.setInt(3, c.getX());
				pstmtInsert.setInt(4, c.getY());
				pstmtInsert.setInt(5, game.getTableVal(c));
				//System.out.println(++i + ". " + game.getTableVal(c));
				pstmtInsert.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
