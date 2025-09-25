/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code.eris.abshell.ast;

import code.eris.abshell.Shell;

public class StringExpression implements ValueExpression {
    public StringExpression(String value) {
        this.value = value;
    }
    
    @Override
    public Object evaluate(Shell shell) {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    private String value;
}
