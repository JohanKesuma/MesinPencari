/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author johan
 */
public class MesinPencariTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"No", "Id Doc", "Document", "Similarity"};
    private ArrayList<SearchingResult> searchingResults;

    public MesinPencariTableModel() {
        super();
    }

    public MesinPencariTableModel(ArrayList<SearchingResult> searchingResults) {
        super();
        this.searchingResults = searchingResults;
    }
    

    @Override
    public int getRowCount() {
        return searchingResults.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
      return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return rowIndex + 1;
            case 1:
                return searchingResults.get(rowIndex).getDocument().getId();
            case 2:
                return searchingResults.get(rowIndex).getDocument().getContent();
            case 3:
                return searchingResults.get(rowIndex).getSimilarity();
            
        }
        
        return new String();
    }
    
}
