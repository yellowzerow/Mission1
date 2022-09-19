package com.zerobase.yellowzero.controller;

import java.util.ArrayList;
import java.util.List;

import com.zerobase.yellowzero.db.WifiData;

public class WifiService extends ConnService {
	
	//와이파이 api 정보 db에 저장하기
	public void saveInfo(WifiData data) {
		try {
			final String sql = " INSERT INTO WifiInfo "
					+  " VALUES "
					+  " ( "
					+  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
					+  " ) ";
	
			dbConnection();
			prepared = prepared(sql);
			
			prepared.setString(1, data.getX_SWIFI_MGR_NO());
			prepared.setString(2, data.getX_SWIFI_WRDOFC());
			prepared.setString(3, data.getX_SWIFI_MAIN_NM());
			prepared.setString(4, data.getX_SWIFI_ADRES1());
			prepared.setString(5, data.getX_SWIFI_ADRES2());
			prepared.setString(6, data.getX_SWIFI_INSTL_FLOOR());
			prepared.setString(7, data.getX_SWIFI_INSTL_TY());
			prepared.setString(8, data.getX_SWIFI_INSTL_MBY());
			prepared.setString(9, data.getX_SWIFI_SVC_SE());
			prepared.setString(10, data.getX_SWIFI_CMCWR());
			prepared.setString(11, data.getX_SWIFI_CNSTC_YEAR());
			prepared.setString(12, data.getX_SWIFI_INOUT_DOOR());
			prepared.setString(13, data.getX_SWIFI_REMARS3());
			prepared.setDouble(14, data.getLNT());
			prepared.setDouble(15, data.getLAT());
			prepared.setString(16, data.getWORK_DTTM());
			
			prepared.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("saveInfo Fail" + e);
			rollback();
		} finally {
			connectionClose();
		}
	}
	
	//내 위치와 가까운 와이파이 정보 20개 뿌리기
	public List<WifiData> nearByInfo(double x, double y) {
		List<WifiData> list = new ArrayList<>();
		
		try {	
			final String sql = " SELECT *, " 
							  + " round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)-radians(?))+sin(radians(?))*sin(radians(LAT))),4) as DISTANCE " 
							  + " FROM WifiInfo " 
							  + " ORDER BY DISTANCE ASC "
							  + " LIMIT 20 ";
			
			dbConnection();
			prepared = prepared(sql);
			
			prepared.setDouble(1, x);
			prepared.setDouble(2, y);
			prepared.setDouble(3, x);
			
			resultSet = prepared.executeQuery();
			
			while (resultSet.next()) {
				WifiData data = new WifiData();
				data.setDistance(resultSet.getDouble("DISTANCE"));
				data.setX_SWIFI_MGR_NO(resultSet.getString("MGR_NO"));
		        data.setX_SWIFI_WRDOFC(resultSet.getString("WRDOFC"));
		        data.setX_SWIFI_MAIN_NM(resultSet.getString("MAIN_NM"));
		        data.setX_SWIFI_ADRES1(resultSet.getString("ADRES1"));
		        data.setX_SWIFI_ADRES2(resultSet.getString("ADRES2"));
		        data.setX_SWIFI_INSTL_FLOOR(resultSet.getString("INSTL_FLOOR"));
		        data.setX_SWIFI_INSTL_TY(resultSet.getString("INSTL_TY"));
		        data.setX_SWIFI_INSTL_MBY(resultSet.getString("INSTL_MBY"));
		        data.setX_SWIFI_SVC_SE(resultSet.getString("SVC_SE"));
		        data.setX_SWIFI_CMCWR(resultSet.getString("CMCWR"));
		        data.setX_SWIFI_CNSTC_YEAR(resultSet.getString("CNSTC_YEAR"));
		        data.setX_SWIFI_INOUT_DOOR(resultSet.getString("INOUT_DOOR"));
		        data.setX_SWIFI_REMARS3(resultSet.getString("REMARS3"));
		        data.setLAT(resultSet.getDouble("LAT"));
		        data.setLNT(resultSet.getDouble("LNT"));
		        data.setWORK_DTTM(resultSet.getString("WORK_DTTM"));
		        
		        list.add(data);
			}
			
		} catch (Exception e) {
			System.out.println("nearByInfo Fail" + e);
			rollback();
		} finally {
			connectionClose();
		}
		
		return list;
	}
}
