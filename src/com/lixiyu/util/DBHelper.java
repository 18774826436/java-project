package com.lixiyu.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.StringEscapeUtils;

import com.lixiyu.model.MyDate;
import com.lixiyu.model.User;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
public class DBHelper implements DBConfig {
 /*
 * ʹ��MySQL����Դ������ݿ����Ӷ���
 *
 * @return��MySQL���Ӷ���������ʧ�ܷ���null
 */
 public static Connection getConnection() {
	 MysqlDataSource mds = new MysqlDataSource();// ����MySQL����Դ
	 mds.setDatabaseName(databaseName);// �������ݿ�����
	 mds.setUser(username);// �������ݿ��û���
	 mds.setPassword(password);// �������ݿ�����
	 try {
		 return mds.getConnection();// �������
	 } catch (SQLException e) {
		 e.printStackTrace();
	 }
		 return null;// �����ȡʧ�ܾͷ���null
 }
 /*
 * �ж�ָ���û������û��Ƿ����
 *
 * @return��������ڷ���true�������ڻ��߲�ѯʧ�ܷ���false
 */
 public static boolean exists(String username) {
	 QueryRunner runner = new QueryRunner();// ����QueryRunner����
	 String sql = "select id from tb_user where username = '" + username + "';";// �����ѯ���
	 Connection conn = getConnection();// �������
	 ResultSetHandler<List<Object>> rsh = new ColumnListHandler();// ���������������
	 try {
		 List<Object> result = runner.query(conn, sql, rsh);// ��ò�ѯ���
	 if (result.size() > 0) {// ����б��д�������
		 return true;// ����true
	 } else {// ����б���û������
		 return false;// ����false
	 }
	 } catch (SQLException e) {
		 e.printStackTrace();
	 } finally {
		 DbUtils.closeQuietly(conn);// �ر�����
	 }
	 return false;// ��������쳣����false
 }
 /*
 * ��֤�û����������Ƿ���ȷ ʹ��Commons Lang���ת���ַ�������SQLע��
 *
 * @return�������ȷ����true�����󷵻�false
 */
 public static boolean check(String username, char[] password) {
	 username = StringEscapeUtils.escapeSql(username);// ���û�������û���ת��
	 QueryRunner runner = new QueryRunner();// ����QueryRunner����
	 String sql = "select password from tb_user where username = '" + username + "';";// �����ѯ���
	 Connection conn = getConnection();// �������
	 ResultSetHandler<Object> rsh = new ScalarHandler();// ���������������
	 try {
		 String result = (String) runner.query(conn, sql, rsh);// ��ò�ѯ���
	 char[] queryPassword = result.toCharArray();// ����ѯ��������ת�����ַ�����
	 if (Arrays.equals(password, queryPassword)) {// ���������ͬ�򷵻�true
		 Arrays.fill(password, '0');// ��մ��������
		 Arrays.fill(queryPassword, '0');// ��ղ�ѯ������
		 return true;
	 } else {// ������벻ͬ�򷵻�false
		 Arrays.fill(password, '0');// ��մ��������
		 Arrays.fill(queryPassword, '0');// ��ղ�ѯ������
	 return false;
	 }
	 } catch (SQLException e) {
		 e.printStackTrace();
	 } finally {
		 DbUtils.closeQuietly(conn);// �ر�����
	 }
	 return false;// ��������쳣����false
 }
 /*
 * �����û������ע����Ϣ
 *
 * @return���������ɹ�����true������ʧ�ܷ���false
 */
 	public static boolean save(User user) {
		 QueryRunner runner = new QueryRunner();// ����QueryRunner����
		 String sql = "insert into tb_user (username, password, email) values (?, ?, ?);";// �����ѯ���
		 Connection conn = getConnection();// �������
		 Object[] params = { user.getUsername(), user.getPassword(), user.getEmail() };// ��ô��ݵĲ���
		 try {
			 int result = runner.update(conn, sql, params);// �����û�
			 JFrame jf = new JFrame();
		 if (result > 0) {// �������ɹ�����true
			 JOptionPane.showMessageDialog(jf, "����ɹ���", "�ɹ�����Ϣ", JOptionPane.PLAIN_MESSAGE);
			 return true;
		 } else {// �������ʧ�ܷ���false
			 JOptionPane.showMessageDialog(jf, "����ʧ�ܣ�", "��Ϊϵͳ�Ƚ�������������", JOptionPane.ERROR_MESSAGE);
			 return false;
		 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 DbUtils.closeQuietly(conn);// �ر�����
		 }
			 return false;// ��������쳣����false
	}
 	
	public static boolean save2(MyDate date) {
		 QueryRunner runner = new QueryRunner();// ����QueryRunner����
		 String sql = "insert into tb_date (day, mouth, year,contest) values (?, ?, ?,?);";// �����ѯ���
		 Connection conn = getConnection();// �������
		 Object[] params = { date.getDay(), date.getMouth(), date.getYear(),date.getContest() };// ��ô��ݵĲ���
		 try {
			 int result = runner.update(conn, sql, params);// �����û�
		 JFrame jf = new JFrame();
		 if (result > 0) {// �������ɹ�����true
			 JOptionPane.showMessageDialog(jf, "����ɹ���", "�ɹ�����Ϣ", JOptionPane.PLAIN_MESSAGE);
			 return true;
		 } else {// �������ʧ�ܷ���false
			 JOptionPane.showMessageDialog(jf, "����ʧ�ܣ�", "��Ϊϵͳ�Ƚ�������������", JOptionPane.ERROR_MESSAGE);
			 return false;
		 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 DbUtils.closeQuietly(conn);// �ر�����
		 }
			 return false;// ��������쳣����false
	}
 	
	public static MyDate fetch() {
		 Connection conn = getConnection();// �������
		 try {
				Statement stmt = conn.createStatement();
				//stmt.executeUpdate(sql);
				String sql = "select * from tb_date";
				ResultSet rs = stmt.executeQuery(sql);
				MyDate list = new MyDate();
				while(rs.next())
				{
					//int id = rs.getInt(1);
					String year = rs.getString(1);
					String month = rs.getString(2);
					String day = rs.getString(3);
					String contest = rs.getString(4);
					
					list.setYear(year);
					list.setMouth(month);
					list.setDay(day);
					list.setContest(contest);
					//System.out.println("id" + id + '\n' + "name" + name);
				}
				return list;
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// ��ò�ѯ���
		 return null;
	 }
}