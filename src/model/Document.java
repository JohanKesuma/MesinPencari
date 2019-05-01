/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author puspaingtyas
 */
public class Document implements Comparable<Document> {

    private int id;
    private String content;

    public Document() {
    }

    public Document(int id, String content) {
        this.id = id;
        setContent(content);
    }

    public Document(String content) {
        this.content = content;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content.replaceAll("[()?:!.,;{}]+", "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getListTerm() {
        return null;
    }

    public static String[] toTerms(String content) {
        return content.split(" ");
    }

    public String[] getListofTerm() {
        String value = this.getContent().toLowerCase();
//        value = value.replaceAll("[()?:!.,;{}]+", "");
        return value.split(" ");
    }

    public ArrayList<Posting> getListOfPosting() {
        String[] terms = getListofTerm();

        // menampung hasil
        ArrayList<Posting> result = new ArrayList<>();

        for (int i = 0; i < terms.length; i++) {
            if (i == 0) {

                // untuk kata pertama
                Posting tempPosting = new Posting(terms[i], this);
                result.add(tempPosting);
            } else {

                // urutkan result
                Collections.sort(result);

                Posting tempPosting = new Posting(terms[i], this);

                // cari apakah term sudah ada di dalam arraylist result
                int pos = Collections.binarySearch(result, tempPosting);
                if (pos < 0) { // jika tidak ketemu

                    result.add(tempPosting);

                } else { // ika term sudah ada

                    // tambahkan numberOfTerm
                    int tempNumber = result.get(pos).getNumberOfTerm() + 1;
                    result.get(pos).setNumberOfTerm(tempNumber);
                }
            }
        }
        Collections.sort(result);

        return result;
    }

    @Override
    public int compareTo(Document o) {
        return Integer.compare(this.id, o.id);
    }

    public static Document readFile(int doc, File file) {

        String content = "";
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                content += str;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Document(doc, content);
    }
}
