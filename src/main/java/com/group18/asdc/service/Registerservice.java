package com.group18.asdc.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.group18.asdc.entities.Registerbean;

@Repository
public class Registerservice {

	@Autowired
	private DataSource dataSource;

	

	public String registeruser(Registerbean bean) {
		String banner = null;
		Boolean flag = false;
		Connection connection = null;
		try {

			if (!bean.getBannerid().matches("B00(.*)")) {
				System.out.println("The bannerid is not valid");
				return "invalidbannerid";
			} else if (bean.getBannerid().length() != 9) {
				System.out.println("The bannerid is not valid");
				return "invalidbannerid2";
			}

			if (!bean.getEmailid().matches("(.*)@dal.ca")) {
				System.out.println("The emailid is not valid");
				return "invalidemailid";
			}
			if (bean.getPassword().length() <= 7) {
				System.out.println("The password is less than 8 characters");
				return "shortpassword";
			}
			connection = dataSource.getConnection();
			PreparedStatement pst3 = connection.prepareStatement("select * from user where emailid=?");
			pst3.setString(1, bean.getEmailid());
			ResultSet rs3 = pst3.executeQuery();
			while (rs3.next()) {
				String z = rs3.getString("emailid");
				if (z.equalsIgnoreCase(bean.getEmailid())) {
					flag = true;
					System.out.println("user account already exists");
					return "alreadycreatedemail";
				}
				break;
			}

			PreparedStatement pst2 = connection.prepareStatement("select * from user where bannerid=?");
			pst2.setString(1, bean.getBannerid());
			ResultSet rs2 = pst2.executeQuery();
			while (rs2.next()) {
				String s = rs2.getString("bannerid");
				if (s.equals(bean.getBannerid())) {
					flag = true;
					System.out.println("user account already exists");
					return "alreadycreated";
				}

				break;
			}
			if (!flag) {
				PreparedStatement pst = connection.prepareStatement("insert into user values(?,?,?,?,?)");

				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(bean.getPassword());
				//System.out.println("encrypted password is" + hashedPassword);
				pst.setString(1, bean.getBannerid());
				pst.setString(2, bean.getLastname());
				pst.setString(3, bean.getFirstname());
				pst.setString(4, bean.getEmailid());
				pst.setString(5, hashedPassword);

				int rs = pst.executeUpdate();
				if (rs > 0) {
					System.out.println("account created");
					PreparedStatement ps = connection.prepareStatement("select * from user where bannerid=?");
					ps.setString(1, bean.getBannerid());
					ResultSet rss = ps.executeQuery();
					while (rss.next()) {
						banner = rss.getString("bannerid");
					}

				}
				PreparedStatement pst7 = connection
						.prepareStatement("insert into systemrole(roleid,bannerid) values(?,?)");
				pst7.setInt(1, 2);
				pst7.setString(2, banner);
				int rs5 = pst7.executeUpdate();
				if (rs5 > 0) {
					System.out.println("The role is addded as a guest user");
				}
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "success";

	}
}
