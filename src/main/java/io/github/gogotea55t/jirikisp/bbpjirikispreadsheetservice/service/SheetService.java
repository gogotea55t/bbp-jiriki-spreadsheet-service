package io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.ClearValuesResponse;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import io.github.gogotea55t.jiriki.domain.request.ScoreDeleteRequest;
import io.github.gogotea55t.jiriki.domain.request.ScoreRequest;
import io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice.sheets.GoogleSheetsAPIConfig;
import io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice.sheets.GoogleSpreadSheetConfig;

@Service
public class SheetService {
  private GoogleSheetsAPIConfig APIConfig;
  private GoogleSpreadSheetConfig sheetConfig;

  @Autowired
  public SheetService(GoogleSheetsAPIConfig APIConfig, GoogleSpreadSheetConfig sheetConfig) {
    this.APIConfig = APIConfig;
    this.sheetConfig = sheetConfig;
  }

  private String SHEET_ID() {
    return sheetConfig.getId();
  }

  private String SHEET_NAME() {
    return sheetConfig.getName();
  }

  public void updateScore(ScoreRequest request) throws Exception {
    Sheets sheet = APIConfig.getSheetsService();
    int columnNo = findUserColumn(request.getUserId());
    int rowNo = findSongRow(request.getSongId());
    List<List<Object>> value = Arrays.asList(Arrays.asList(request.getScore().setScale(0)));
    ValueRange values = new ValueRange().setValues(value);
    UpdateValuesResponse response =
        sheet
            .spreadsheets()
            .values()
            .update(SHEET_ID(), SHEET_NAME() + "!" + getA1Notation(columnNo, rowNo), values)
            .setValueInputOption("USER_ENTERED")
            .execute();
    System.out.println(response);
  }

  public void deleteScore(ScoreDeleteRequest request) throws Exception {
    Sheets sheet = APIConfig.getSheetsService();
    int columnNo = findUserColumn(request.getUserId());
    int rowNo = findSongRow(request.getSongId());
    ClearValuesRequest clearRequest = new ClearValuesRequest();
    ClearValuesResponse response =
        sheet
            .spreadsheets()
            .values()
            .clear(SHEET_ID(), SHEET_NAME() + "!" + getA1Notation(columnNo, rowNo), clearRequest)
            .execute();
    System.out.println(response);
  }

  private int findUserColumn(String userId) throws Exception {
    ValueRange columns =
        APIConfig.getSheetsService()
            .spreadsheets()
            .values()
            .get(SHEET_ID(), SHEET_NAME() + "!L3:3")
            .execute();
    List<Object> column = columns.getValues().get(0);
    int i = 0;
    int j = 0;
    int k = 0;
    for (Object cell : column) {
      if (cell.toString().equals(userId)) {
        j = i;
        k++;
        if (k == 2) {
          throw new IllegalArgumentException("Too many users registered for one user id.");
        }
      }
      i++;
    }

    if (k == 0) {
      throw new IllegalArgumentException("User not found.");
    }

    return j + 12; // L列から始まっているので
  }

  private int findSongRow(String songId) throws Exception {
    ValueRange rows =
        APIConfig.getSheetsService()
            .spreadsheets()
            .values()
            .get(SHEET_ID(), SHEET_NAME() + "!K5:K")
            .execute();

    int i = 0, j = 0, k = 0;

    for (List<Object> cell : rows.getValues()) {
      if (cell.isEmpty()) {

      } else if (cell.get(0).toString().equals(songId)) {
        j = i;
        k++;
        if (k == 2) {
          throw new IllegalStateException("Too many songs registered for one song id.");
        }
      }
      i++;
    }
    if (k == 0) {
      throw new IllegalArgumentException("Song not found.");
    }

    return j + 5; // 5行目から始まっているので
  }

  private String getA1Notation(int column, int row) {
    int firstIndexAlpha = (int) 'A';
    StringBuilder sb = new StringBuilder();
    if (column < 0) {
      throw new IllegalArgumentException();
    } else {
      int tmp = column;
      while (tmp != 0) {
        sb.append(String.valueOf((char) (firstIndexAlpha + (tmp - 1) % 26)));
        tmp = (tmp - 1) / 26;
      }
    }
    return sb.append(row).toString();
  }
}
