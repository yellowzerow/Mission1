package com.zerobase.yellowzero.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.zerobase.yellowzero.db.HistoryData;

public class HistoryService extends ConnService {

	//history list 뿌려주기
	public List<HistoryData> loadHistory() {
		List<HistoryData> list = new ArrayList<>();
		
		try {
			final String sql = " SELECT * "
							+  " FROM HistoryInfo ";
			
			dbConnection();
			state();
			
			resultSet = state.executeQuery(sql);
			
			while (resultSet.next()) {
				HistoryData data = new HistoryData();
				data.setID(resultSet.getInt("ID"));
				data.setLAT(resultSet.getDouble("LAT"));
				data.setLNT(resultSet.getDouble("LNT"));
				data.setHISTORY_DATE(resultSet.getString("HISTORY_DATE"));
				
				list.add(data);
			}
			
		} catch (Exception e) {
			System.out.println("loadHistory Fail" + e);
			rollback();
		} finally {
			connectionClose();
		}
		
		return list;
	}
	
	//history 정보 db에 저장하기
	public void saveHistory(HistoryData data) {
		try {
			final String sql = " INSERT INTO HistoryInfo (LAT, LNT, HISTORY_DATE) "
							+  " VALUES (?, ?, ?) ";
			
			dbConnection();
			prepared = prepared(sql);
			
			prepared.setDouble(1, data.getLAT());
			prepared.setDouble(2, data.getLNT());
			prepared.setString(3, LocalDateTime.now().toString());
			
			prepared.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("saveHistory Fail" + e);
			rollback();
		} finally {
			connectionClose();
		}
	}
	
	public void deleteHistory(String id) {
		try {
			final String sql = " DELETE FROM HistoryInfo "
							+  " WHERE ID = " + id;
			
			dbConnection();
			prepared = prepared(sql);
			prepared.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteHistory Fail" + e);
		} finally {
			connectionClose();
		}
		
	}
}