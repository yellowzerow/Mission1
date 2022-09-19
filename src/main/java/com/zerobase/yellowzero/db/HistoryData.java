package com.zerobase.yellowzero.db;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class HistoryData {
	private Integer ID; 		// id
    private Double LAT; 		// x좌표
    private Double LNT; 		// y좌표
    private String HISTORY_DATE;  // 작업일자
}
