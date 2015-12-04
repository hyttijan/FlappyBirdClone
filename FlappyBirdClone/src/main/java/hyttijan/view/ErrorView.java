/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hyttijan.view;

import javax.swing.JOptionPane;

/**
 *
 * @author hyttijan
 */
public class ErrorView{
    private View view;
    private String message;
    /**
     * ErrorView näyttää virheilmoituksen
     * @param view
     * @param message 
     */
    public ErrorView(View view,String message){
        this.message=message;
        JOptionPane.showMessageDialog(view, message);
    }
}
