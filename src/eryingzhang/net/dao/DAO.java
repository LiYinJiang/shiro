package eryingzhang.net.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.SimpleHashRequest;

import eryingzhang.net.entity.User;

public class DAO {
	public static final String ALGORITHM = "md5";
	public static final int HASH_ITEARATOR = 2;

	public DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shiro?characterEncoding=UTF-8", "root",
				"admin");
	}

	public String getPassword(String userName) {
		String password = null;
		try {
			Connection connection = getConnection();
			String sqlSelect = "select user.password from user where name= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				password = rs.getString("password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}

	public Set<String> listRoles(String userName) {
		Set<String> roles = new HashSet<String>();
		try {
			Connection connection = getConnection();
			String sqlSelect = "select r.name from user u " + "left join user_role ur on ur.uid=u.id "
					+ "left join role r on r.id = ur.rid " + "where u.name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				roles.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;
	}

	public Set<String> listPermissions(String userName) {
		Set<String> permissions = new HashSet<String>();
		try {
			Connection connection = getConnection();
			String sqlSelect = "select p.name from user u " + "left join user_role ur on ur.uid = uid "
					+ "left join role r on r.id = ur.rid " + "left join role_permission rp on rp.rid = r.id "
					+ "left join permission p on p.id = rp.pid " + "where u.name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				permissions.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permissions;
	}

	public void createUser(String name, String password) {

		try {
			Connection connection = getConnection();
			String sqlSelect = "insert into user (name,password,salt) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);

			String salt = new SecureRandomNumberGenerator().nextBytes().toString();
			password = new SimpleHash(ALGORITHM, password, salt, HASH_ITEARATOR).toString();
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, salt);
			preparedStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public User getUser(String name) {
		User user = new User();
		try {
			Connection connection = getConnection();
			String sqlSelect = "select * from user where name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setSalt(rs.getString("salt"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public static void main(String[] args) {
		System.out.println(new DAO().listRoles("gaki"));
		System.out.println(new DAO().listRoles("ohaha"));
		System.out.println(new DAO().listPermissions("gaki"));
		System.out.println(new DAO().listPermissions("ohaha"));
	}

}
